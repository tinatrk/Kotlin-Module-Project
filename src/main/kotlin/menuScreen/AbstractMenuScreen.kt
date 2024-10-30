package menuScreen

import menuHandler.MenuHandler
import menuHandler.MenuItem

abstract class AbstractMenuScreen() {
    abstract val screenName: String
    abstract val menuMessage: MenuMessage
    protected val screenMenu: MutableList<MenuItem> = mutableListOf()
    private val menuHandler: MenuHandler = MenuHandler()


    fun showScreen() {
        var userCommand = -1
        while (userCommand.toString() != MenuHandler.QUIT_COMMAND) {
            menuHandler.showMenu(screenName, screenMenu)
            userCommand = menuHandler.getUserMenuItem(screenMenu.size)
            menuHandler.doUserMenuItem(userCommand, screenMenu)
        }
    }

    abstract fun createItem()

    abstract fun openItem(index: Int)

    protected companion object{
        const val LIST_SHIFT = 2
    }

    class MenuMessage(
        var quitMessage: String,
        var createMessage: String,
        var openMessage: String){
    }
}