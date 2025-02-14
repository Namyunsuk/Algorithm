import java.util.*
import kotlin.math.*

data class Pos(val z: Int, val x: Int, val y: Int, val dis: Int = 0)

val dx = listOf(-1, 1, 0, 0, 0, 0)

val dy = listOf(0, 0, -1, 1, 0, 0)
val dz = listOf(0, 0, 0, 0, -1, 1)
val n = 5

val originGraph = Array(n) { Array(n) { Array(n) { 0 } } }
val graph = Array(n) { Array(n) { Array(n) { 0 } } }
val rotatedGraph = Array(n) { Array(n) { Array(n) { 0 } } }

val surfaceOrders = mutableListOf<List<Int>>()
val vis = Array(5) { false }

var result = Int.MAX_VALUE

fun main(): Unit = with(System.`in`.bufferedReader()) {
    repeat(5) { z ->
        repeat(5) { x ->
            val input = readLine().split(" ").map { it.toInt() }
            input.forEachIndexed { y, v ->
                originGraph[z][x][y] = v
            }
        }
    }

    dfs(mutableListOf())


    val cases = (4.0).pow(5).toInt()

    for (orders in surfaceOrders) {
        switch(orders)

        for (c in 0 until cases) {
            var flag = true
            var directions = (c).toString(4)
            while (directions.length < 5) {
                directions = "0" + directions
            }

            for (k in 0 until n) {
                for (i in 0 until n) {
                    for (j in 0 until n) {
                        rotatedGraph[k][i][j] = graph[k][i][j]
                    }
                }
            }

            for (i in 0 until 5) {
                val dir = directions[i].code - '0'.code
                repeat(dir) {
                    rotate(rotatedGraph[i])
                }
                if (rotatedGraph[0][0][0] != 1) {
                    flag = false
                    break
                }
            }
            if (flag) {
                result = minOf(result, escape(rotatedGraph, Pos(0, 0, 0)))
            }
        }
    }

    print(if (result == Int.MAX_VALUE) -1 else result)
}

fun dfs(orders: MutableList<Int>) {
    if (orders.size == 5) {
        surfaceOrders.add(orders.toList())
        return
    }

    for (i in 0 until 5) {
        if (vis[i]) continue
        vis[i] = true
        orders.add(i)
        dfs(orders)
        orders.remove(i)
        vis[i] = false
    }
}

fun switch(orders: List<Int>) {
    for (z in 0 until n) {
        val floor = orders[z]
        for (i in 0 until n) {
            for (j in 0 until n) {
                graph[z][i][j] = originGraph[floor][i][j]
            }
        }
    }
}

fun escape(graph: Array<Array<Array<Int>>>, pos: Pos): Int {
    val vis = Array(n) { Array(n) { Array(n) { false } } }
    val q = LinkedList<Pos>()
    q.offer(pos)

    while (!q.isEmpty()) {
        val cur = q.poll()
        for (i in 0 until 6) {
            val nz = cur.z + dz[i]
            val nx = cur.x + dx[i]
            val ny = cur.y + dy[i]

            if (nz < 0 || nz >= n || nx < 0 || nx >= n || ny < 0 || ny >= n) continue
            if (graph[nz][nx][ny] == 0) continue
            if (vis[nz][nx][ny]) continue
            if (nz == n - 1 && nx == n - 1 && ny == n - 1) {
                return cur.dis + 1
            }
            q.offer(Pos(nz, nx, ny, cur.dis + 1))
            vis[nz][nx][ny] = true
        }
    }

    return Int.MAX_VALUE
}

fun rotate(surface: Array<Array<Int>>) {
    val tmp = Array(n) { Array(n) { 0 } }

    for (i in 0 until n) {
        for (j in 0 until n) {
            tmp[i][j] = surface[i][j]
        }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            surface[i][j] = tmp[n - 1 - j][i]
        }
    }
}