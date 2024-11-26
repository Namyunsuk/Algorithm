data class Pos(val x: Int, val y: Int)

val board = Array(9) { Array(9) { 0 } }
var blanks = mutableListOf<Pos>()
var flag = false

fun main(): Unit = with(System.`in`.bufferedReader()) {
    repeat(9) { i ->
        val input = readLine().split(" ").map { it.toInt() }
        input.forEachIndexed { j, v ->
            board[i][j] = v
            if (v == 0) blanks.add(Pos(i, j))
        }
    }

    dfs(mutableListOf(), blanks.size)
}

fun dfs(nums: MutableList<Int>, desCnt: Int) {
    if (flag) return
    if (nums.size == desCnt) {
//        blanks.forEachIndexed { i, v ->
//            board[v.x][v.y] = nums[i]
//        }
        board.forEach { row ->
            row.forEach {
                print("$it ")
            }
            println()
        }
        flag = true
        return
    }

    for (i in 1..9) {
        val blank = blanks[nums.size]
        if (!isValidNum(blank.x, blank.y, i)) continue
        nums.add(i)
        board[blank.x][blank.y] = i
        dfs(nums, desCnt)
        board[blank.x][blank.y] = 0
        nums.removeLast()
    }
}

fun isValidNum(x: Int, y: Int, num: Int): Boolean {
    for (j in 0..<9) { // 가로
        if (j == y) continue
        if (board[x][j] == num) return false
    }

    for (i in 0..<9) {
        if (i == x) continue
        if (board[i][y] == num) return false
    }

    val tmpX = when (x) {
        in 0..2 -> 0
        in 3..5 -> 3
        else -> 6
    }

    val tmpY = when (y) {
        in 0..2 -> 0
        in 3..5 -> 3
        else -> 6
    }

    for (i in tmpX..<tmpX + 3) {
        for (j in tmpY..<tmpY + 3) {
            if (i == x && j == y) continue
            if (board[i][j] == num) return false
        }
    }

    return true
}