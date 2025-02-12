import kotlin.math.*

const val UP = 0
const val DOWN = 1
const val RIGHT = 2
const val LEFT = 3

val graph = Array(20) { Array(20) { 0 } }
val tmpGraph = Array(20) { Array(20) { 0 } }

var result = Int.MIN_VALUE
var n = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            graph[i][j] = v
        }
    }

    val cases = ((4.0).pow(5) - 1).toInt()

    for (i in 0 until cases) {
        resetGraph()
        var directions = i.toString(4)
        while (directions.length < 5) {
            directions = "0" + directions
        }

        for (j in 0 until 5) {
            val dir = directions[j].code - '0'.code

            if (dir == UP) {
                moveUp(tmpGraph)
            } else if (dir == DOWN) {
                moveDown(tmpGraph)
            } else if (dir == RIGHT) {
                moveRight(tmpGraph)
            } else if (dir == LEFT) {
                moveLeft(tmpGraph)
            }
        }

        val max = tmpGraph.maxOf { it.max() }
        result = maxOf(result, max)
    }

    print(result)
}

fun moveUp(graph: Array<Array<Int>>) {
    // 합치기
    for (i in 0 until n) {
        for (j in 0 until n) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (d in i + 1 until n) {
                if (graph[d][j] > 0 && graph[d][j] != cur) break
                if (graph[d][j] == cur) {
                    graph[i][j] = 2 * cur
                    graph[d][j] = 0
                    break
                }
            }
        }
    }

    // 올리기
    for (i in 0 until n) {
        for (j in 0 until n) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (u in 0 until i) {
                if (graph[u][j] == 0) {
                    graph[u][j] = cur
                    graph[i][j] = 0
                    break
                }
            }
        }
    }
}

fun moveDown(graph: Array<Array<Int>>) {
    // 합치기
    for (i in n - 1 downTo 0) {
        for (j in 0 until n) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (u in i - 1 downTo 0) {
                if (graph[u][j] > 0 && graph[u][j] != cur) break
                if (graph[u][j] == cur) {
                    graph[i][j] = 2 * cur
                    graph[u][j] = 0
                    break
                }
            }
        }
    }

    // 내리기
    for (i in n - 1 downTo 0) {
        for (j in 0 until n) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (d in n - 1 downTo i + 1) {
                if (graph[d][j] == 0) {
                    graph[d][j] = cur
                    graph[i][j] = 0
                    break
                }
            }
        }
    }
}

fun moveLeft(graph: Array<Array<Int>>) {
    // 합치기
    for (i in 0 until n) {
        for (j in 0 until n) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (r in j + 1 until n) {
                if (graph[i][r] > 0 && graph[i][r] != cur) break
                if (graph[i][r] == cur) {
                    graph[i][j] = 2 * cur
                    graph[i][r] = 0
                    break
                }
            }
        }
    }

    // 좌측 이동
    for (i in 0 until n) {
        for (j in 0 until n) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (l in 0 until j) {
                if (graph[i][l] == 0) {
                    graph[i][l] = cur
                    graph[i][j] = 0
                    break
                }
            }
        }
    }
}

fun moveRight(graph: Array<Array<Int>>) {
    // 합치기
    for (i in 0 until n) {
        for (j in n - 1 downTo 0) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (l in j - 1 downTo 0) {
                if (graph[i][l] > 0 && graph[i][l] != cur) break
                if (graph[i][l] == cur) {
                    graph[i][j] = 2 * cur
                    graph[i][l] = 0
                    break
                }
            }
        }
    }

    // 우측 이동
    for (i in 0 until n) {
        for (j in n - 1 downTo 0) {
            val cur = graph[i][j]
            if (cur == 0) continue
            for (r in n-1 downTo j + 1) {
                if (graph[i][r] == 0) {
                    graph[i][r] = cur
                    graph[i][j] = 0
                    break
                }
            }
        }
    }
}

fun resetGraph() {
    for (i in 0 until n) {
        for (j in 0 until n) {
            tmpGraph[i][j] = graph[i][j]
        }
    }
}
