package menuScreen

import creatorScreen.NoteCreatorScreen
import items.Archive
import items.Note
import menuHandler.MenuItem

class NotesMenuScreen(private val archive: Archive) : AbstractMenuScreen() {
    override val screenName = "Список заметок архива '${archive.name}'"
    override val menuMessage = AbstractMenuScreen.MenuMessage(
        "Вернуться к списку архивов",
        "Создать заметку",
        "Открыть заметку"
    )
    private var noteCreatorScreen = NoteCreatorScreen()

    init {
        super.screenMenu.add(MenuItem(menuMessage.quitMessage, {}))
        super.screenMenu.add(MenuItem(menuMessage.createMessage, {createItem()}))
        archive.notes.forEachIndexed(){index, item -> super.screenMenu.add(
            MenuItem("${menuMessage.openMessage} '${item.name}'",
                { openItem(index+ LIST_SHIFT) }))}
    }

    override fun createItem(){
        noteCreatorScreen.createItem(archive.notes)?.let {
            archive.notes.add(it as Note)
            val index = screenMenu.size
            super.screenMenu.add(MenuItem("${menuMessage.openMessage} '${it.name}'",
                {openItem(index)}))
        }
    }

    override fun openItem(index: Int) {
        noteCreatorScreen.readNote(archive.notes[index-LIST_SHIFT])
    }
}