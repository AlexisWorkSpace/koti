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

fun <T> dfs(
    start: T,
    graph: Map<T, List<T>>,
    visited: MutableSet<T> = mutableSetOf(),
    action: (T) -> Unit = {}
) {
    if (start in visited) return
    visited.add(start)
    action(start)
    for (neighbor in graph[start].orEmpty()) {
        dfs(neighbor, graph, visited, action)
    }
}

