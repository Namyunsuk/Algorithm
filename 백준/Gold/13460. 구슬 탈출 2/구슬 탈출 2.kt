import java.util.*
import kotlin.math.*

data class Pos(val x: Int, val y: Int)

const val WALL = "#"
const val EXIT = "O"

val graph = Array(11) { Array(11) { "." } }
var result = -1

lateinit var red: Pos
lateinit var blue: Pos
lateinit var exit: Pos

val dx = listOf(-1, 1, 0, 0)
val dy = listOf(0, 0, -1, 1)

val q = LinkedList<Triple<Pos, Pos, Int>>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    repeat(n) { i ->
        val input = readLine().split("").filter { it.isNotBlank() }
        input.forEachIndexed { j, v ->
            graph[i][j] = v
            if (v == "R") {
                red = Pos(i, j)
            }

            if (v == "B") {
                blue = Pos(i, j)
            }

            if (v == EXIT) {
                exit = Pos(i, j)
            }
        }
    }

    q.offer(Triple(red, blue, 0))

    while (!q.isEmpty()) {
        var flag = false
        val cur = q.poll()
        val curRed = cur.first
        val curBlue = cur.second
        val cnt = cur.third

        for (i in 0 until 4) {
            var newRed = move(curRed, dx[i], dy[i])
            var newBlue = move(curBlue, dx[i], dy[i])


            if (cnt > 9) continue
            if (newBlue == exit) continue
            if (newRed == exit) {
                result = cnt + 1
                flag = true
                break
            }

            if (newRed == newBlue) {
                val dr = abs((curRed.x - newRed.x) + (curRed.y - newRed.y))
                val db = abs((curBlue.x - newBlue.x) + (curBlue.y - newBlue.y))

                if (dr < db) {
                    newBlue = Pos(newBlue.x - dx[i], newBlue.y - dy[i])
                } else {
                    newRed = Pos(newRed.x - dx[i], newRed.y - dy[i])
                }
            }

            q.offer(Triple(newRed, newBlue, cnt + 1))
        }

        if (flag) break
    }




    print(result)
}

fun move(cur: Pos, dx: Int, dy: Int): Pos {
    var nx = cur.x
    var ny = cur.y

    while (true) {
        nx += dx
        ny += dy
        if (graph[nx][ny] == WALL) {
            nx -= dx
            ny -= dy
            break
        }
        if (graph[nx][ny] == EXIT) break
    }

    return Pos(nx, ny)
}