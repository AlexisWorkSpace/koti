package bfs

import java.util.LinkedList
import java.util.Queue

fun <T> bfs(
    start: T,
    graph: Map<T, List<T>>,
    visited: MutableSet<T> = mutableSetOf(),
    action: (T) -> Unit = {}
) {
    val queue: Queue<T> = LinkedList()
    queue.add(start)
    visited.add(start)

    while (queue.isNotEmpty()) {
        val current = queue.poll()
        action(current)
        for (neighbor in graph[current].orEmpty()) {
            if (neighbor !in visited) {
                visited.add(neighbor)
                queue.add(neighbor)
            }
        }
    }
}
