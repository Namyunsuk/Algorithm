val sb = StringBuilder()
lateinit var arr: Array<String>
lateinit var vis: Array<Boolean>

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (l, c) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ")
    arr = Array(c) { "" }
    vis = Array(c) { false }
    input.forEachIndexed { i, v ->
        arr[i] = v
    }
    arr.sort()

    dfs(0, mutableListOf(), l, c)

    print(sb)
}

fun dfs(index: Int, strings: MutableList<String>, l: Int, c: Int) {
    if (strings.size == l) {
        if (isValid(strings)) {
            strings.forEach { sb.append(it) }
            sb.append("\n")
        }
        return
    }

    for (i in index..<c) {
        if (vis[i]) continue
        vis[i] = true
        strings.add(arr[i])
        dfs(i + 1, strings, l, c)
        strings.removeLast()
        vis[i] = false
    }
}

fun isValid(strings: MutableList<String>): Boolean {
    val vowelCnt = strings.count {
        it == "a" || it == "e" || it == "i" || it == "o" || it == "u"
    }
    val anotherCnt = strings.size - vowelCnt
    return (vowelCnt >= 1) && (anotherCnt >= 2)
}