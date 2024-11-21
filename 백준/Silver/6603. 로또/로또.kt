val sb = StringBuilder()
lateinit var arr: Array<Int>
lateinit var vis: Array<Boolean>
var n = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    while (true) {
        val input = readLine().split(" ").map { it.toInt() }
        if (input[0] == 0) break
        sb
        arr = Array(input.size - 1) { 0 }
        vis = Array(input.size - 1) { false }
        n = arr.size
        input.forEachIndexed { i, v ->
            if (i == 0) return@forEachIndexed
            arr[i - 1] = v
        }
        dfs(0, mutableListOf(), n)
        sb.append("\n")
    }
    print(sb)
}

fun dfs(index: Int, nums: MutableList<Int>, n: Int) {
    if (nums.size == 6) {
        nums.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in index..<n) {
        if (vis[i]) continue
        vis[i] = true
        nums.add(arr[i])
        dfs(i + 1, nums, n)
        nums.removeLast()
        vis[i] = false
    }
}