import java.util.*

data class Pos(val x: Int, val y: Int)

val board = Array(101) { Array(101) { 0 } }
val snake = LinkedList<Pos>()
val control = Array(10002) { "" }

val dx = listOf(0, 1, 0, -1)
val dy = listOf(1, 0, -1, 0)
var dir = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val k = readLine().toInt()

    repeat(k) {
        val (x, y) = readLine().split(" ").map { it.toInt() }
        board[x][y] = 1
    }

    val l = readLine().toInt()
    repeat(l) {
        val (x, c) = readLine().split(" ")
        control[x.toInt()] = c
    }

    var cnt = 0
    snake.addFirst(Pos(1, 1))

    while (true) {
        cnt++

        val head = snake[0]
        val next = Pos(head.x + dx[dir], head.y + dy[dir])
        if (next.x < 1 || next.x > n || next.y < 1 || next.y > n) break
        if (snake.contains(next)) break

        snake.addFirst(next)
        if (board[next.x][next.y] == 1) board[next.x][next.y] = 0
        else snake.removeLast()

        if (control[cnt] == "L") {
            dir--
            if (dir < 0) dir = 3
        } else if (control[cnt] == "D") {
            dir++
            if (dir > 3) dir = 0
        }
    }

    print(cnt)
}