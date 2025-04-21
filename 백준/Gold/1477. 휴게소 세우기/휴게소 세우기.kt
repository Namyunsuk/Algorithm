val rests = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m, l) = readLine().split(" ").map { it.toInt() }
    if(n>0)rests.addAll(readLine().split(" ").map { it.toInt() })

    rests.add(0)
    rests.sort()
    rests.add(l)


    var s = 1
    var e = l

    while (s < e) {
        val mid = (s + e) / 2
        if (isValid(m, mid)) e = mid
        else s = mid + 1
    }
    print(s)
}

fun isValid(m: Int, minSpace: Int): Boolean {
    var cnt = 0

    for (i in 0 until rests.size - 1) {
        val space = rests[i + 1] - rests[i]
        if (space <= minSpace) continue
        val needRests = if (space % minSpace == 0) (space / minSpace) - 1 else space / minSpace
        cnt += needRests
        if (cnt > m) return false
    }

    return true
}
