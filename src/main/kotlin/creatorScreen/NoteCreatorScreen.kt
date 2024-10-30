package creatorScreen

import items.Item
import items.Note
import menuHandler.MenuHandler

class NoteCreatorScreen: AbstractCreatorScreen() {
    override val creatorNameMassage = AbstractCreatorScreen.CreatorNameMessage(
        "\nВведите название заметки или '${MenuHandler.QUIT_COMMAND}', " +
                "чтобы вернуться к списку заметок",
        "\nЗаметка с таким названием уже существует",
        "\nЗаметка не создана: не задано название заметки"
    )

    override fun returnItem(name: String) : Item?{
        val content = createNoteContent()
        if (!isContentEmpty(content))
                return Note(name, content)
        else {
            super.menuHandler.showMessage(ERROR_EMPTY_NOTE_CONTENT)
            return null
        }
    }

    private fun createNoteContent(): ArrayList<String>{
        super.menuHandler.showMessage(ENTER_NOTE_CONTENT)
        return menuHandler.getUserStrList()
    }

    private fun isContentEmpty(content: ArrayList<String>): Boolean{
        for (str in content){
            var curStr = str
            curStr = curStr.trimStart()
            curStr = curStr.trimEnd()
            if (curStr.isNotEmpty())
                return false
        }
        return true
    }

    fun readNote(note: Note){
        super.menuHandler.showMessage(SHOW_NOTE_CONTENT)
        menuHandler.showMessageList(note.content)

        super.menuHandler.showMessage(QUIT_NOTE_CONTENT)
        menuHandler.getUserStr()
    }

    private companion object{
        const val ENTER_NOTE_CONTENT = "\nВведите содержимое заметки. Чтобы сохранить заметку " +
                "и вернуться к списку заметок, введите '${MenuHandler.QUIT_COMMAND}'"
        const val SHOW_NOTE_CONTENT = "\nСодержимое заметки:"
        const val QUIT_NOTE_CONTENT = "\nВведите любой символ, чтобы вернуться к списку заметок"
        const val ERROR_EMPTY_NOTE_CONTENT = "\nЗаметка не создана: не задано содержимое заметки"
    }
}