package creatorScreen

import items.Item
import items.Archive
import menuHandler.MenuHandler

class ArchiveCreatorScreen: AbstractCreatorScreen() {
    override val creatorNameMassage = AbstractCreatorScreen.CreatorNameMessage(
        "\nВведите название архива или '${MenuHandler.QUIT_COMMAND}', " +
                "чтобы вернуться к списку архивов",
        "\nАрхив с таким названием уже существует",
        "\nАрхив не создан: не задано название архива"
    )

    override fun returnItem(name: String) : Item {
        return Archive(name)
    }
}