package menuHandler

import ErrorCode
import java.util.Scanner

class MenuHandler {
    private val scanner = Scanner(System.`in`)

    fun showMenu(menuName:String, menu: MutableList<MenuItem>){
        showMessage("\n$menuName:")
        menu.forEachIndexed(){index, element -> println("$index. ${element.name}")}
        showMessage(INFO_ENTER_NUMBER)
    }

    fun getUserMenuItem(menuSize: Int): Int{
        val userInput = scanner.nextLine()
         if(userInput.toIntOrNull() != null){
             if (userInput.toInt() in 0 until menuSize){
                 return userInput.toInt()
             }else{
                 showMessage(ERROR_OUT_OF_RANGE)
                 return INCORRECT_COMMAND
             }
         }else {
             showMessage(ERROR_EXPECTED_NUMBER)
             return INCORRECT_COMMAND
         }
    }

    fun doUserMenuItem(userMenuItem: Int, menu: MutableList<MenuItem>){
        if (userMenuItem == INCORRECT_COMMAND) return

        menu[userMenuItem].action.invoke(userMenuItem)
    }

    fun getUserStr(): String{
        var userInput: String = scanner.nextLine()
        userInput = userInput.trimStart()
        userInput = userInput.trimEnd()
        return userInput
    }

    fun getUserStrList(): ArrayList<String>{
        var userInput: String
        val data: ArrayList<String> = arrayListOf()
        while(true){
            userInput = scanner.nextLine()
            if (userInput == QUIT_COMMAND){
                return data
            }

            data.add(userInput)
        }
    }

    fun checkUserStr(userStr: String, isUniqName: (String) -> Boolean): Int{
        if (userStr == QUIT_COMMAND) return ErrorCode.QUIT_COMMAND.ordinal
        if (userStr.isEmpty()) return ErrorCode.EMPTY_NAME.ordinal
        if (!isUniqName.invoke(userStr)) return ErrorCode.ITEM_ALREADY_EXIST.ordinal

        return ErrorCode.IS_OK.ordinal
    }

    fun showMessage(message: String){
        println(message)
    }

    fun showMessageList(messageList: ArrayList<String>){
        messageList.forEach{println(it)}
    }

    companion object{
        const val INCORRECT_COMMAND = -1
        const val ERROR_OUT_OF_RANGE = "Некорректный ввод: такого номера нет в меню"
        const val ERROR_EXPECTED_NUMBER = "Некорректный ввод: необходимо ввести номер пункта меню"
        const val INFO_ENTER_NUMBER = "Чтобы выбрать пункт меню, введите его номер"
        const val QUIT_COMMAND = "0"
    }
}