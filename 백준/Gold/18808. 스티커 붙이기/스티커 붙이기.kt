import java.util.*

const val BLANK = 0
const val FILL = 1

val noteBook = Array(40) { Array(40) { BLANK } }
lateinit var sticker: Array<Array<Int>>
lateinit var st: StringTokenizer
var r = 0
var c = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toInt() }

    repeat(k) { it ->
        sticker = Array(10) { Array(10) { BLANK } }
        st = StringTokenizer(readLine())
        r = st.nextToken().toInt()
        c = st.nextToken().toInt()
        repeat(r) { i ->
            val input = readLine().split(" ").map { it.toInt() }
            input.forEachIndexed { j, v ->
                sticker[i][j] = v
            }
        }

        for (k in 0 until 4) {
            var flag = false
            for (i in 0 .. n - r) {
                for (j in 0 .. m - c) {
                    if (isValid(i, j)) {
                        addSticker(i, j)
                        flag = true
                        break
                    }
                }
                if (flag) break
            }
            if (flag) break
            rotate()
        }
    }

    var result = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (noteBook[i][j] == FILL) result++
        }
    }

    print(result)
}

fun isValid(x: Int, y: Int): Boolean {
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (sticker[i][j] == FILL && noteBook[x + i][y + j] == FILL) return false
        }
    }

    return true
}

fun addSticker(x: Int, y: Int) {
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (sticker[i][j] == FILL) {
                noteBook[x + i][y + j] = FILL
            }
        }
    }
}


fun rotate() {
    val tmp = Array(10) { Array(10) { BLANK } }

    for (i in 0 until r) {
        for (j in 0 until c) {
            tmp[i][j] = sticker[i][j]
        }
    }

    for (i in 0 until c) {
        for (j in 0 until r) {
            sticker[i][j] = tmp[r - 1 - j][i]
        }
    }

    val t = r
    r = c
    c = t
}
