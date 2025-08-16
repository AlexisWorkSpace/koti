package Functions

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

const val MAX = 100_500
val isPrime = BooleanArray(MAX) { true }

fun criba() {
    isPrime[0] = false
    isPrime[1] = false

    for (p in 2 until MAX) {
        if (isPrime[p]) {
            for (m in 2 * p until MAX step p) {
                isPrime[m] = false
            }
        }
    }
}

