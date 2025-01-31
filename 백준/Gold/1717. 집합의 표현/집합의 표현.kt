lateinit var p: Array<Int>

val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    p = Array(n + 1) { -1 }

    repeat(m) {
        val (op, a, b) = readLine().split(" ").map { it.toInt() }
        if (op == 0) union(a, b)
        else {
            if (find(a) == find(b)) sb.append("YES\n")
            else sb.append("NO\n")
        }
    }

    print(sb)
}

fun find(u: Int): Int {
    if (p[u] < 0) return u
    p[u] = find(p[u])
    return p[u]
}

fun union(u: Int, v: Int): Boolean {
    val pU = find(u)
    val pV = find(v)

    if (pU == pV) return false

    p[pV] = pU
    return true
}