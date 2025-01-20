import kotlin.math.*

val pipes = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (k, n) = readLine().split(" ").map { it.toInt() }
    repeat(k) {
        pipes.add(readLine().toInt())
    }

    pipes.sort()
    
    var st = 0L
    var en = Int.MAX_VALUE.toLong()

    while (st < en) {
        val mid = (st + en + 1) / 2
        if (isValid(pipes, mid.toInt(), n)) st = mid
        else en = mid - 1
    }

    print(st)
}

fun isValid(arr: List<Int>, length: Int, n: Int): Boolean {
    var cnt = 0
    arr.forEach {
        cnt += (it / length)
    }

    return cnt >= n
}