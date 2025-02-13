import java.util.*
import kotlin.math.*

data class Pos(val x: Int, val y: Int)

val chickens = mutableListOf<Pos>()
val houses = mutableListOf<Pos>()
val graph = Array(50) { Array(50) { 0 } }
var n = 0
var m = 0
var result = Int.MAX_VALUE

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            graph[i][j] = v
            if (v == 1) houses.add(Pos(i, j))
            else if (v == 2) chickens.add(Pos(i, j))
        }
    }

    dfs(0, m, mutableListOf())

    print(result)
}

fun calculate(selected: List<Pos>) {
    var sum = 0
    houses.forEach { house ->
        sum += find(house, selected)
    }

    result = minOf(result, sum)
}

fun find(house: Pos, selected: List<Pos>): Int {
    var min = Int.MAX_VALUE
    selected.forEach { chicken ->
        min = minOf(min, abs(house.x - chicken.x) + abs(house.y - chicken.y))
    }
    return min
}

fun dfs(idx: Int, m: Int, selected: MutableList<Pos>) {
    if (selected.size == m) {
        calculate(selected)
        return
    }

    for (i in idx until chickens.size) {
        selected.add(chickens[i])
        dfs(i + 1, m, selected)
        selected.remove(chickens[i])
    }
}
