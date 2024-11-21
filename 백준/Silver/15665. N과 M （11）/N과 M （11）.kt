val sb = StringBuilder()
lateinit var arr: Array<Int>
val ans = mutableSetOf<List<Int>>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { 0 }

    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    arr.sort()

    dfs(mutableListOf(), n, m)

    ans.forEach { nums ->
        nums.forEach { sb.append("$it ") }
        sb.append("\n")
    }

    print(sb)
}


fun dfs(nums: MutableList<Int>, n: Int, m: Int) {
    if (nums.size == m) {
        ans.add(nums.toList())
        return
    }

    for (i in 0..<n) {
        nums.add(arr[i])
        dfs(nums, n, m)
        nums.removeLast()
    }
}