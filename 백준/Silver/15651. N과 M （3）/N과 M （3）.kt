val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    dfs(mutableListOf(), 0, n, m)
    print(sb)
}

fun dfs(nums: MutableList<Int>, size: Int, n: Int, m: Int) {
    if (size == m) {
        nums.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }

    for (i in 1..n) {
        nums.add(i)
        dfs(nums, size + 1, n, m)
        nums.removeLast()
    }
}