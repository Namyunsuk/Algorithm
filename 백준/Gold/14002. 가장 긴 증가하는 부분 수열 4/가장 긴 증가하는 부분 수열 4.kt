import kotlin.math.*

val dp = Array(1002) { 0 }
val arr = Array(1002) { 0 }
val cache = Array(1002) { 0 }

fun main() {
    val n = readln().toInt()
    val input = readln().split(" ").map { it.toInt() }

    for (i in 1..n) {
        arr[i] = input[i - 1]
        dp[i] = 1
        cache[i] = i
    }

    for (i in 2..n) {
        for (j in 1..i - 1) {
            if (arr[i] > arr[j]) {
                if (dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1
                    cache[i] = j
                }
            }
        }
    }

    var max_v = 0
    var max_index = 0

    for (i in 1..n) {
        if (dp[i] > max_v) {
            max_v = dp[i]
            max_index = i
        }
    }

    var index = max_index

    val ans = mutableListOf<Int>()

    println(dp[max_index])
    while (true) {
        ans.add(arr[index])
        if (cache[index] == index) break
        index = cache[index]
    }

    ans.sort()

    ans.forEach {
        print("${it} ")
    }
}