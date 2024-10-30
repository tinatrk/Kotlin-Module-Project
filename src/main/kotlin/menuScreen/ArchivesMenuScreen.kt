package menuScreen

import creatorScreen.ArchiveCreatorScreen
import items.Archive
import menuHandler.MenuItem

class ArchivesMenuScreen : AbstractMenuScreen(){
    override val screenName: String = "Список архивов"
    override val menuMessage = AbstractMenuScreen.MenuMessage(
        "Выйти из приложения",
        "Создать архив",
        "Открыть архив"
    )
    private var archives: MutableList<Archive> = mutableListOf()
    private var archiveCreatorScreen = ArchiveCreatorScreen()

    init {
        super.screenMenu.add(MenuItem(menuMessage.quitMessage, {}))
        super.screenMenu.add(MenuItem(menuMessage.createMessage, {createItem()}))
    }

    override fun createItem(){
        archiveCreatorScreen.createItem(archives)?.let {
            archives.add(it as Archive)
            val index = screenMenu.size
            super.screenMenu.add(MenuItem("${menuMessage.openMessage} '${it.name}'",
                {openItem(index)}))
        }
    }

    override fun openItem(index: Int) {
        val notesListScreen = NotesMenuScreen(archives[index-LIST_SHIFT])
        notesListScreen.showScreen()
    }
}