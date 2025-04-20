import kotlin.math.*

val pipes = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (k, n) = readLine().split(" ").map { it.toInt() }
    for (i in 0 until k) {
        pipes.add(readLine().toInt())
    }


    var s = 1L
    var e = ((2.0).pow(31) - 1).toLong()

    while (s < e) {
        val mid = (s + e + 1) / 2
        if (isValid(mid.toInt(), n)) s = mid
        else e = mid - 1
    }

    print(s)
}

fun isValid(len: Int, n: Int): Boolean {
    var cnt = 0
    pipes.forEach {
        cnt += (it / len)
    }

    return cnt >= n
}
