val dx = listOf(1, 0, -1, 0)
val dy = listOf(0, -1, 0, 1)

val graph = Array(101) { Array(101) { false } }
var result = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val (x, y, d, g) = readLine().split(" ").map { it.toInt() }
        makeDragonCurve(x,y,d,g)
    }

    count()

    print(result)
}

fun makeDragonCurve(x: Int, y: Int, d: Int, g: Int) {
    val dirs = mutableListOf<Int>()
    dirs.add(d)

    repeat(g) {
        val tmp = mutableListOf<Int>()
        for (i in dirs.size - 1 downTo 0) {
            tmp.add(nextDir(dirs[i]))
        }
        dirs.addAll(tmp)
    }

    var curX = x
    var curY = y

    graph[curY][curX] = true
    for (dir in dirs) {
        curX += dx[dir]
        curY += dy[dir]
        graph[curY][curX] = true
    }
}

fun count() {
    for (i in 0..99) {
        for (j in 0..99) {
            if (graph[i][j] && graph[i][j + 1] && graph[i + 1][j] && graph[i + 1][j + 1]) result++
        }
    }
}


fun nextDir(curDir: Int): Int {
    return when (curDir) {
        0 -> 1
        1 -> 2
        2 -> 3
        else -> 0
    }
}