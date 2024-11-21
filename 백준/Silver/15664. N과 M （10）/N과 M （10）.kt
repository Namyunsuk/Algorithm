val sb = StringBuilder()
lateinit var arr: Array<Int>
lateinit var vis: Array<Boolean>
val ans = mutableSetOf<List<Int>>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { 0 }
    vis = Array(n) { false }

    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    arr.sort()

    dfs(0, mutableListOf(), n, m)

    ans.forEach { nums ->
        nums.forEach { sb.append("$it ") }
        sb.append("\n")
    }

    print(sb)
}


fun dfs(index: Int, nums: MutableList<Int>, n: Int, m: Int) {
    if (nums.size == m) {
        ans.add(nums.toList())
        return
    }

    for (i in index..<n) {
        if (vis[i]) continue
        vis[i] = true
        nums.add(arr[i])
        dfs(i + 1, nums, n, m)
        nums.removeLast()
        vis[i] = false
    }
}