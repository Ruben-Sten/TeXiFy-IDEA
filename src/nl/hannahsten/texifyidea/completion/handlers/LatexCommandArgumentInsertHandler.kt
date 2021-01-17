package nl.hannahsten.texifyidea.completion.handlers

import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.template.TemplateManager
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.codeInsight.template.impl.TextExpression
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import nl.hannahsten.texifyidea.lang.*
import nl.hannahsten.texifyidea.psi.LatexCommands
import nl.hannahsten.texifyidea.util.endOffset
import nl.hannahsten.texifyidea.util.files.psiFile
import nl.hannahsten.texifyidea.util.parentOfType

/**
 * @author Hannah Schellekens
 */
class LatexCommandArgumentInsertHandler(val arguments: List<Argument>? = null) : InsertHandler<LookupElement> {

    override fun handleInsert(insertionContext: InsertionContext, lookupElement: LookupElement) {
        removeFromFirstWhiteSpace(insertionContext)

        when (val `object` = lookupElement.getObject()) {
            is LatexCommands -> {
                insertCommands(insertionContext, lookupElement)
            }
            is LatexMathCommand -> {
                insertMathCommand(`object`, insertionContext, lookupElement)
            }
            is LatexRegularCommand, is LatexCommand -> {
                insertNoMathCommand(`object` as LatexCommand, insertionContext, lookupElement)
            }
        }
    }

    private fun insertCommands(context: InsertionContext, lookupElement: LookupElement) {
        insert(context, lookupElement)
    }

    private fun insertMathCommand(mathCommand: LatexMathCommand, context: InsertionContext, lookupElement: LookupElement) {
        if (mathCommand.autoInsertRequired()) {
            insert(context, lookupElement)
        }
    }

    private fun insertNoMathCommand(noMathCommand: LatexCommand, context: InsertionContext, lookupElement: LookupElement) {
        if (noMathCommand.autoInsertRequired()) {
            insert(context, lookupElement)
        }
    }

    private fun insert(context: InsertionContext, lookupElement: LookupElement) {
        val editor = context.editor
        val document = editor.document
        val caret = editor.caretModel
        val offset = caret.offset
        // When not followed by { or [ (whichever the first parameter starts with) insert the parameters.
        if (arguments != null && (
            offset >= document.textLength - 1 || document.getText(TextRange.from(offset, 1)) !in setOf("{", "[")
            )
        ) {
            insertParametersLiveTemplate(editor)
        }
        else {
            skipParameters(editor, lookupElement)
        }
    }

    private fun insertParametersLiveTemplate(editor: Editor) {
        // arguments is not null, we checked when calling this function.
        val template = TemplateImpl(
            "",
            arguments!!.mapIndexed { index: Int, argument: Argument ->
                if (argument is RequiredArgument) "{\$__Variable$index\$}" else "[\$__Variable$index\$]"
            }.joinToString(""),
            ""
        )
        repeat(arguments.size) { template.addVariable(TextExpression(""), true) }
        TemplateManager.getInstance(editor.project).startTemplate(editor, template)
    }

    private fun skipParameters(editor: Editor, lookupElement: LookupElement) {
        val document = editor.document
        val file = document.psiFile(editor.project ?: return)
        val caret = editor.caretModel
        val extraSpaces = lookupElement.lookupString.takeLastWhile { it == ' ' }.length
        val psiElement = file?.findElementAt(caret.offset + extraSpaces) ?: return
        if (psiElement.text in setOf("{", "[")) {
            caret.moveToOffset((psiElement.parentOfType(LatexCommands::class)?.endOffset() ?: return) - extraSpaces)
        }
    }

    /**
     * Remove whitespaces and everything after that that was inserted by the lookup text.
     */
    private fun removeFromFirstWhiteSpace(context: InsertionContext) {
        val editor = context.editor
        val document = editor.document
        val offset = editor.caretModel.offset
        // context.startOffset is the offset of the start of the just inserted text.
        val insertedText = document.text.substring(context.startOffset, offset)
        val indexFirstSpace = insertedText.indexOfFirst { it == ' ' }
        document.deleteString(context.startOffset + indexFirstSpace, offset)
    }
}