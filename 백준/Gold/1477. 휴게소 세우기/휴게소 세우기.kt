val rests = mutableListOf<Int>()


fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, l) = readLine().split(" ").map { it.toInt() }
    if (n != 0) {
        rests.addAll(readLine().split(" ").map { it.toInt() })
    }
    rests.add(l)
    rests.sort()

    var s = 1
    var e = l - 1

    while (s < e) {
        val mid = (s + e) / 2
        if (isValid(rests, m, mid)) e = mid
        else s = mid + 1
    }

    print(s)
}

fun isValid(arr: List<Int>, m: Int, dist: Int): Boolean {
    var cur = 0
    var cnt = 0
    var idx = 0

    while (idx < arr.size) {
        if (arr[idx] - cur > dist) {
            cur += dist
            if (cnt == m) return false
            cnt++
        } else {
            cur = arr[idx++]
        }
    }

    return true
}