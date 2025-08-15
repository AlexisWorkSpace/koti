fun main() {
    // ingreso de las n habitaciones y las m cartas
    val (n, m) = readln().split(" ").map { it.toInt() }
    // se ingresa una sola linea por array - por lo que se puede separar por espacios
    val a:List<Int> = readln().split(" ").map { it.toInt() }.take(n)
    val b:List<Int> = readln().split(" ").map { it.toInt() }.take(m)
    // se crea secuencia a[1] + a[2] + ... + a[n]
    val p:List<Int> = a.runningFold(0) { acc, value -> acc + value}


}
