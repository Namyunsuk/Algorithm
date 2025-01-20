val numsCnt = HashMap<Int, Int>()
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val nums = readLine().split(" ").map { it.toInt() }

    nums.forEach {
        numsCnt[it] = numsCnt.getOrDefault(it, 0) + 1
    }

    val m = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }

    input.forEach {
        if (numsCnt[it] == null) sb.append("0 ")
        else sb.append("${numsCnt[it]} ")
    }

    print(sb)
}