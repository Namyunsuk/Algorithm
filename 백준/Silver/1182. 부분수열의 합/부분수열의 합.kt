lateinit var arr: Array<Int>
lateinit var vis: Array<Boolean>

var cnt = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, s) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { 0 }
    vis = Array(n) { false }

    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    dfs(0, mutableListOf(), n, s)

    print(cnt)
}

fun dfs(index: Int, nums: MutableList<Int>, n: Int, s: Int) {
    if (nums.sum() == s && nums.size != 0) {
        cnt++
    }

    if (nums.size == n) return

    for (i in index..<n) {
        if (vis[i]) continue
        vis[i] = true
        nums.add(arr[i])
        dfs(i, nums, n, s)
        nums.removeLast()
        vis[i] = false
    }
}