fun main() {
    var pesaje:Int
    do
        pesaje = readLine()?.toIntOrNull()?.takeIf { it in 1..100 } ?: 0
    while (pesaje == 0)

    if(watermelon(pesaje)) println("YES") else println("NO")
}

fun watermelon(pesaje:Int):Boolean = if (pesaje % 2 == 0) true else false
