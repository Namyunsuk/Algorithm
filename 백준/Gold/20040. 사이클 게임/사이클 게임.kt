lateinit var p: Array<Int>

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    p = Array(n) { -1 }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        if (find(a) == find(b)) {
            print("${it + 1}")
            return
        }
        union(a, b)
    }

    print(0)
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