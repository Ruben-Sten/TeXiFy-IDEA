package nl.hannahsten.texifyidea.inspections.bibtex

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import nl.hannahsten.texifyidea.index.LatexIncludesIndex
import nl.hannahsten.texifyidea.insight.InsightGroup
import nl.hannahsten.texifyidea.inspections.TexifyInspectionBase
import nl.hannahsten.texifyidea.psi.LatexCommands
import nl.hannahsten.texifyidea.util.files.document
import nl.hannahsten.texifyidea.util.includedFileNames
import nl.hannahsten.texifyidea.util.replaceString
import nl.hannahsten.texifyidea.util.requiredParameter
import nl.hannahsten.texifyidea.util.trimRange

/**
 * @author Sten Wessel
 */
open class BibtexDuplicateBibliographyInspection : TexifyInspectionBase() {

    // Manual override to match short name in plugin.xml
    override fun getShortName() = InsightGroup.BIBTEX.prefix + inspectionId

    override val inspectionGroup = InsightGroup.LATEX

    override val inspectionId = "DuplicateBibliography"

    override fun getDisplayName() = "Same bibliography is included multiple times"

    override fun inspectFile(file: PsiFile, manager: InspectionManager, isOntheFly: Boolean): List<ProblemDescriptor> {
        val descriptors = descriptorList()

        // Map each bibliography file to all the commands which include it
        val groupedIncludes = mutableMapOf<String, MutableList<LatexCommands>>()

        LatexIncludesIndex.getItemsInFileSet(file).asSequence()
                .filter { it.name == "\\bibliography" || it.name == "\\addbibresource" }
                .forEach {
                    for (fileName in it.includedFileNames() ?: return@forEach) {
                        groupedIncludes.getOrPut(fileName) { mutableListOf() }.add(it)
                    }
                }

        groupedIncludes.asSequence()
                .filter { (_, commands) -> commands.size > 1 }
                .forEach { (fileName, commands) ->
                    for (command in commands.distinct()) {
                        if (command.containingFile != file) continue

                        val parameterIndex = command.requiredParameter(0)?.indexOf(fileName) ?: break
                        descriptors.add(manager.createProblemDescriptor(
                                command,
                                TextRange(parameterIndex, parameterIndex + fileName.length).shiftRight(command.commandToken.textLength + 1),
                                "Bibliography file '$fileName' is included multiple times", // todo not for chapterbib
                                ProblemHighlightType.GENERIC_ERROR,
                                isOntheFly,
                                RemoveOtherCommandsFix(fileName, commands)
                        ))
                    }
                }

        return descriptors
    }

    /**
     * @author Sten Wessel
     */
    class RemoveOtherCommandsFix(private val bibName: String, private val commandsToFix: List<LatexCommands>) :
            LocalQuickFix {

        override fun getFamilyName(): String {
            return "Remove other includes of $bibName"
        }

        override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
            val currentCommand = descriptor.psiElement as LatexCommands
            val documentManager = PsiDocumentManager.getInstance(project)

            // For all commands to be fixed, remove the matching bibName
            // Handle commands by descending offset, to make sure the replaceString calls work correctly
            for (command in commandsToFix.sortedByDescending { it.textOffset }) {
                val document = command.containingFile.document() ?: continue
                val param = command.parameterList.first()

                // If we handle the current command, find the first occurrence of bibName and leave it intact
                val firstBibIndex = if (command == currentCommand) {
                    param.text.trimRange(1, 1).splitToSequence(',').indexOfFirst { it.trim() == bibName }
                }
                else -1

                val replacement = param.text.trimRange(1, 1).splitToSequence(',')
                        // Parameter should stay if it is at firstBibIndex or some other bibliography file
                        .filterIndexed { i, it -> i <= firstBibIndex || it.trim() != bibName }
                        .joinToString(",", prefix = "{", postfix = "}")

                // When no arguments are left, just delete the command
                if (replacement.trimRange(1, 1).isBlank()) {
                    command.delete()
                }
                else {
                    document.replaceString(param.textRange, replacement)
                }
                documentManager.doPostponedOperationsAndUnblockDocument(document)
                documentManager.commitDocument(document)
            }
        }
    }
}
