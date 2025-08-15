fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val a:List<Long> = readln().split(" ").map { it.toLong() }.take(n)
    val b:List<Long> = readln().split(" ").map { it.toLong() }.take(m)

    // p[i] = total acumulado de habitaciones hasta el dormitorio i
    val p:List<Long> = a.runningFold(0L) { acc, value -> acc + value }

    for (x in b) {
        val idx:Int = binarySearch(p, condition = { it >= x })
        val localRoom:Long = x - p[idx - 1]
        println("$idx $localRoom")
    }
}

fun <T : Comparable<T>> binarySearch(
    list: List<T>,
    condition: (T) -> Boolean,
    exactMatch: Boolean = false
): Int {
    var l = 0
    var r = list.size - 1
    var result = -1

    while (l <= r) {
        val m = (l + r) / 2
        val value = list[m]
        if (condition(value)) {
            result = m
            if (exactMatch) return m
            r = m - 1
        } else {
            l = m + 1
        }
    }
    return result
}
