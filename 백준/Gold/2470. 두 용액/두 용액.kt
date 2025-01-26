import kotlin.math.*

val liquids = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    liquids.addAll(readLine().split(" ").map { it.toInt() })

    liquids.sort()

    var s = 0
    var e = n - 1
    var l = 0
    var r = 0
    var min = Int.MAX_VALUE

    while (s < e) {
        val sum = liquids[s] + liquids[e]
        if (abs(sum) < abs(min)) {
            l = s
            r = e
            min = sum
        }
        if (sum < 0) s++
        else if (sum > 0) e--
        else break
    }

    print("${liquids[l]} ${liquids[r]}")
}