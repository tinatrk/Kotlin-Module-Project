package items

class Archive(name: String) : Item(name){
    val notes: MutableList<Note> = mutableListOf()
}