import kotlin.math.*

lateinit var players: Array<Array<Int>>
val vis = Array(11) { false }
var maxValue = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    repeat(n) {
        players = Array(11) { Array(11) { 0 } }
        maxValue = 0

        repeat(11) { i ->
            val input = readLine().split(" ").map { it.toInt() }
            input.forEachIndexed { j, v ->
                players[i][j] = v
            }
        }

        dfs(0, 0)
        println(maxValue)
    }
}

fun dfs(sum: Int, cnt: Int) {
    if (cnt == 11) {
        maxValue = max(maxValue, sum)
        return
    }

    for (i in 0..<11) {
        if (players[i][cnt] == 0) continue
        if (vis[i]) continue
        vis[i] = true
        dfs(sum + players[i][cnt], cnt + 1)
        vis[i] = false
    }
}
