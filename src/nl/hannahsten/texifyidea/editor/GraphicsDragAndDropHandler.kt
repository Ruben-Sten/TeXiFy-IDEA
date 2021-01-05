package nl.hannahsten.texifyidea.editor

import com.intellij.openapi.editor.CustomFileDropHandler
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import nl.hannahsten.texifyidea.util.Magic
import nl.hannahsten.texifyidea.util.files.extractFile
import nl.hannahsten.texifyidea.util.files.isLatexFile
import nl.hannahsten.texifyidea.util.files.psiFile
import nl.hannahsten.texifyidea.util.insertAtCaretAndMove
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.Transferable
import java.io.File

/**
 * @author Hannah Schellekens
 */
open class GraphicsDragAndDropHandler : CustomFileDropHandler() {

    /**
     * Whether the file can be dropped using this handler.
     */
    private fun File.isDroppable(): Boolean {
        return extension.toLowerCase() in Magic.File.graphicFileExtensions
    }

    override fun canHandle(transferable: Transferable, editor: Editor?): Boolean {
        // Only target dropped files.
        val project = editor?.project ?: return false
        val file = transferable.extractFile() ?: return false

        // Only handle files droppable by the handler.
        if (file.isDroppable().not()) return false

        // Only allow dropping in LaTeX sources.
        return editor.document.psiFile(project)?.isLatexFile() == true
    }

    override fun handleDrop(transferable: Transferable, editor: Editor?, project: Project?): Boolean {
        if (editor == null) return false

        // TODO: Change to usage of the insertgraphic wizard.
        //          This is just placeholder behaviour.
        val toDrop = transferable.extractFile() ?: return false
        editor.insertAtCaretAndMove(toDrop.absolutePath)

        return true
    }
}