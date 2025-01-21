fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (m, n) = readLine().split(" ").map { it.toInt() }
    val snacks = readLine().split(" ").map { it.toInt() }

    var st = 0L
    var en = 1000_000_000L

    while (st < en) {
        val mid = (st + en + 1) / 2
        if (isValid(snacks, mid.toInt(), m)) st = mid
        else en = mid - 1
    }

    print(st)
}

fun isValid(arr: List<Int>, length: Int, m: Int): Boolean {
    var cnt = 0
    arr.forEach {
        cnt += (it / length)
    }

    return cnt >= m
}