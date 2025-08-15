package recorridoGrafos

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

data class Edge<T>(val from: T, val to: T, val weight: Double)

fun <T> bellmanFord(
    nodes: List<T>,
    edges: List<Edge<T>>,
    source: T
): Map<T, Double>? {
    val distance = nodes.associateWith { Double.POSITIVE_INFINITY }.toMutableMap()
    distance[source] = 0.0

    // Relaxar todas las aristas (n - 1) veces
    repeat(nodes.size - 1) {
        for ((from, to, weight) in edges) {
            val newDist = distance[from]?.plus(weight) ?: continue
            if (newDist < distance[to] ?: Double.POSITIVE_INFINITY) {
                distance[to] = newDist
            }
        }
    }

    // Verificar ciclos negativos
    for ((from, to, weight) in edges) {
        val newDist = distance[from]?.plus(weight) ?: continue
        if (newDist < distance[to] ?: Double.POSITIVE_INFINITY) {
            return null // Ciclo negativo detectado
        }
    }

    return distance
}
