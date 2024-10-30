package creatorScreen

import ErrorCode
import items.Item
import menuHandler.MenuHandler

abstract class AbstractCreatorScreen {
    protected val menuHandler = MenuHandler()
    abstract val creatorNameMassage: CreatorNameMessage

    fun <T:Item> createItem(items: MutableList<T>): Item?{
        var userInput: String = ""
        while(true) {
            menuHandler.showMessage(creatorNameMassage.enterItemName)
            userInput = menuHandler.getUserStr()
            val errorCode: Int = menuHandler.checkUserStr(userInput,
                { isUniqName(userInput, items) })
            when (errorCode) {
                ErrorCode.QUIT_COMMAND.ordinal -> return null

                ErrorCode.ITEM_ALREADY_EXIST.ordinal ->
                    menuHandler.showMessage(creatorNameMassage.errorItemAlreadyExist)

                ErrorCode.EMPTY_NAME.ordinal ->
                    menuHandler.showMessage(creatorNameMassage.errorEmptyName)

                ErrorCode.IS_OK.ordinal -> return returnItem(userInput)
            }
        }
    }

    private fun <T:Item> isUniqName(userInput: String, items: MutableList<T>): Boolean{
        for (item in items){
            if (item.name == userInput)
                return false
        }
        return true
    }

    abstract fun returnItem(name: String): Item?

    data class CreatorNameMessage(
        var enterItemName: String,
        var errorItemAlreadyExist: String,
        var errorEmptyName: String
    ) {
    }
}