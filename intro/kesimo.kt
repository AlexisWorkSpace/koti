
fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (n, k) = readln().split(" ").map { it.toLong() }
        val m = (n + k - 1) / k * k
        val result = (m + n - 1) / n
        println(result)
    }
}
