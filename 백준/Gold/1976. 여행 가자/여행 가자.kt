val p = Array(201) { -1 }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        for (j in 0 until input.size) {
            if (input[j] == 1) union(i + 1, j + 1)
        }
    }

    val plan = readLine().split(" ").map { it.toInt() }
    for (i in 1 until plan.size) {
        if (find(plan[i - 1]) != find(plan[i])) {
            print("NO")
            return
        }
    }
    print("YES")
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