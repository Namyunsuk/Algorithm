val vis = Array(9) { false }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    dfs(1, mutableListOf(), n, m)
}

fun dfs(index: Int, nums: MutableList<Int>, n: Int, m: Int) {
    if (nums.size == m) {
        nums.forEach { print("$it ") }
        println()
        return
    }

    for (i in index..n) {
        if (vis[i]) continue
        vis[i] = true
        nums.add(i)
        dfs(i + 1, nums, n, m)
        nums.removeLast()
        vis[i] = false
    }
}