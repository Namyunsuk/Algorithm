val p = Array(1000001) { -1 }
val count = Array(1000001) { 1 }

val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    repeat(n) {
        val input = readLine().split(" ")
        if (input[0] == "I") {
            val a = input[1].toInt()
            val b = input[2].toInt()

            union(a, b)
        } else {
            val c = input[1].toInt()
            val pC = find(c)
            sb.appendLine(count[pC])
        }
    }

    print(sb)
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

    if (count[pX] > count[pY]) {
        p[pY] = pX
        count[pX] += count[pY]
        count[pY] = 0
    } else {
        p[pX] = pY
        count[pY] += count[pX]
        count[pX] = 0
    }
    return true
}