fun main() {
    val pesaje:Int = readLine()?.toIntOrNull()?.takeIf { it in 1..100 } ?: 0
    if(watermelon(pesaje)) println("YES") else println("NO")
}

fun watermelon(pesaje:Int):Boolean = if (pesaje % 2 == 0) true else false
