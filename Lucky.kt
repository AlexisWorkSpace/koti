fun main() {
    val n = readln()
    val luckyDigitCount = n.count { it == '4' || it == '7' }
    val result = if (isLuckyNumber(luckyDigitCount.toString())) "YES" else "NO"
    println(result)
}

fun isLuckyNumber(s: String): Boolean {
    return s.all { it == '4' || it == '7' }
}
