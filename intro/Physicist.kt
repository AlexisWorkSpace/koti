fun main() {
    val n = readln().toInt()
    if (physicist(n)) println("YES") else println("NO")
}

fun physicist(n:Int):Boolean {
    var sumX = 0
    var sumY = 0
    var sumZ = 0

    repeat(n) {
        val (x, y, z) = readLine()!!
            .split(" ")
            .map { it.toInt() }
        sumX += x
        sumY += y
        sumZ += z
    }
    return if (sumX == 0 && sumY == 0 && sumZ == 0) true else false
}
