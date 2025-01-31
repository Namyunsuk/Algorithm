lateinit var p: Array<Int>
val sb= StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        p = Array(n) { -1 }
        val k = readLine().toInt()
        repeat(k) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            union(a, b)
        }
        val m = readLine().toInt()
        println("Scenario ${it + 1}:")
        repeat(m) {
            val (a, b) = readLine().split(" ").map { it.toInt() }
            if (find(a) == find(b)) sb.append(1).append('\n')
            else sb.append(0).append('\n')
        }
        print(sb)
        println()
        sb.clear()
    }

}

fun find(x: Int): Int {
    if (p[x] < 0) return x
    p[x] = find(p[x])
    return p[x]
}

fun union(x: Int, y: Int): Boolean {
    val pX = find(x)
    val pY = find(y)

    if (pX == pY) return false
    p[pY] = pX
    return true
}