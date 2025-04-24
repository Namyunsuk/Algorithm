class Equip(val weight: Int, val v: Int)

val equips = mutableListOf<Equip>()
val dp = Array(102) { Array(100002) { 0 } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    equips.add(Equip(0, 0))

    repeat(n) {
        val (w, v) = readLine().split(" ").map { it.toInt() }
        equips.add(Equip(w, v))
    }

    for (i in 1..n) {
        val equip = equips[i]
        for (w in 1..k) {
            if (equip.weight > w) dp[i][w] = dp[i - 1][w]
            else dp[i][w] = maxOf(dp[i - 1][w - equip.weight] + equip.v, dp[i - 1][w])
        }
    }

    print(dp[n][k])
}