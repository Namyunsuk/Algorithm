val counters = mutableListOf<Long>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toLong() }
    repeat(n.toInt()) {
        counters.add(readLine().toLong())
    }

    var s = 0L
    var e = 1e18.toLong()

    while (s < e) {
        val mid = (s + e) / 2L
        if (isValid(mid, counters, m)) e = mid
        else s = mid + 1
    }

    print(s)
}

fun isValid(time: Long, counters: List<Long>, m: Long): Boolean {
    var sum = 0L
    for(counter in counters){
        sum += (time / counter)
        if(sum>=m) return true
    }
    return false
}