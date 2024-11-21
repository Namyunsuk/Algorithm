val sb = StringBuilder()
lateinit var arr: Array<Int>


fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { 0 }

    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    arr.sort()

    dfs(0, mutableListOf(), n, m)

    print(sb)
}


fun dfs(index: Int, nums: MutableList<Int>, n: Int, m: Int) {
    if (nums.size == m) {
        nums.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in index..<n) {
        nums.add(arr[i])
        dfs(i , nums, n, m)
        nums.removeLast()
    }
}