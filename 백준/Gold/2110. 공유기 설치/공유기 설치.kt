val houses = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, c) = readLine().split(" ").map { it.toInt() }
    repeat(n) {
        houses.add(readLine().toInt())
    }

    houses.sort()

    var s = 0
    var e = 1e9.toInt()

    while (s < e) {
        val mid = (s + e + 1) / 2
        if (isValid(houses, mid, c)) s = mid
        else e = mid - 1
    }

    print(s)
}

fun isValid(arr: List<Int>, length: Int, c: Int): Boolean {
    var cur = arr[0]
    var cnt = 1

    for (i in 1 until arr.size) {
        if (arr[i] - cur >= length) {
            cnt++
            cur = arr[i]
        }
        if (cnt == c) return true
    }
    return false
}
