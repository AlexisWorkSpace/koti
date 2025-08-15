package recorridoGrafos

import java.util.PriorityQueue
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

data class Edge<T>(val to: T, val weight: Double)

fun <T> dijkstra(
    graph: Map<T, List<Edge<T>>>,
    source: T
): Map<T, Double> {
    val distance = mutableMapOf<T, Double>().withDefault { Double.POSITIVE_INFINITY }
    val visited = mutableSetOf<T>()
    val queue = PriorityQueue(compareBy<Pair<T, Double>> { it.second })

    distance[source] = 0.0
    queue.add(source to 0.0)

    while (queue.isNotEmpty()) {
        val (current, dist) = queue.poll()
        if (current in visited) continue
        visited.add(current)

        for ((neighbor, weight) in graph[current].orEmpty()) {
            val newDist = dist + weight
            if (newDist < distance.getValue(neighbor)) {
                distance[neighbor] = newDist
                queue.add(neighbor to newDist)
            }
        }
    }

    return distance
}

fun <T> floydWarshall(
    nodes: List<T>,
    edges: List<Triple<T, T, Double>>
): Map<T, Map<T, Double>> {
    // Inicializar matriz de distancias
    val dist = mutableMapOf<T, MutableMap<T, Double>>()
    for (i in nodes) {
        dist[i] = mutableMapOf()
        for (j in nodes) {
            dist[i]!![j] = if (i == j) 0.0 else Double.POSITIVE_INFINITY
        }
    }

    // Cargar aristas
    for ((from, to, weight) in edges) {
        dist[from]?.set(to, weight)
    }

    // Algoritmo principal
    for (k in nodes) {
        for (i in nodes) {
            for (j in nodes) {
                val ik = dist[i]?.get(k) ?: continue
                val kj = dist[k]?.get(j) ?: continue
                val ij = dist[i]?.get(j) ?: continue
                if (ik + kj < ij) {
                    dist[i]?.set(j, ik + kj)
                }
            }
        }
    }

    return dist
}

