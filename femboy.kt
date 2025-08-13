fun main() {
    val name: String = readln()
    val diferentChar: Int = name.toSet().size
    if(diferentChar % 2 == 0) println("CHAT WITH HER!") else println("IGNORE HIM!")
}
