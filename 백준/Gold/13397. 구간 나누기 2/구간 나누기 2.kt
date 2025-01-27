val nums = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    nums.addAll(readLine().split(" ").map { it.toInt() })

    var s = 0
    var e = 9999

    while (s < e) {
        val mid = (s + e) / 2
        if (isValid(nums, mid, m)) e = mid
        else s = mid + 1
    }

    print(s)
}

fun isValid(arr: List<Int>, diff: Int, m: Int): Boolean {
    var cnt = 0
    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE

    for (num in arr) {
        min = minOf(min, num)
        max = maxOf(max, num)
        if (max - min > diff) {
            cnt++
            min = num
            max = num
        }
        if (cnt > m) return false
    }
    cnt++

    return cnt <= m
}