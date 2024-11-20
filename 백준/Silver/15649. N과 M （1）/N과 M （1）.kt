fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    dfs(mutableListOf<Int>(), n, m)
}

fun dfs(nums: MutableList<Int>, n: Int, m: Int) {
    if (nums.size == m) {
        nums.forEach { print("$it ") }
        println()
    }
    for (i in 1..n) {
        if (nums.contains(i)) continue
        nums.add(i)
        dfs(nums, n, m)
        nums.removeLast()
    }
}