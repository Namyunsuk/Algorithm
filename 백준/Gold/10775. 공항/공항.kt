lateinit var p: Array<Int>
var count = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    p = Array(n + 1) { -1 }

    repeat(m) {
        val g = readLine().toInt()
        val pG = find(g)
        if (pG == 0) {
            print(count)
            return
        }

        union(pG - 1, pG)
        count++
    }

    print(count)
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
