import kotlin.math.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val liquids = readLine().split(" ").map { it.toLong()}.toMutableList()

    liquids.sort()

    var l1 = 0
    var l2 = 0
    var l3 = 0
    var min = Long.MAX_VALUE

    for (i in 0 until n) {
        var s = i + 1
        var e = n - 1

        while (s < e) {
            val sum = liquids[i] + liquids[s] + liquids[e]
            if (abs(sum) < abs(min)) {
                min = sum
                l1 = i
                l2 = s
                l3 = e
            }
            if (sum < 0) s++
            else if (sum > 0) e--
            else {
                print("${liquids[l1]} ${liquids[l2]} ${liquids[l3]}")
                return
            }
        }
    }

    print("${liquids[l1]} ${liquids[l2]} ${liquids[l3]}")
}