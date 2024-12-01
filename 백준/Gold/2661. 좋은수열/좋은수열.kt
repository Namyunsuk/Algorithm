import kotlin.system.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    dfs("", n)
}

fun dfs(num: String, n: Int) {
    if (num.length == n) {
        print(num)
        exitProcess(0)
    }

    for (i in 1..3) {
        if (isGood(num + i)) {
            dfs(num + i, n)
        }
    }
}

fun isGood(num: String): Boolean {
    val length = num.length
    for (i in 1..length / 2) {
        val left = num.substring(length - 2 * i, length - i)
        val right = num.substring(length - i, length)
        if (left == right) return false
    }

    return true
}


