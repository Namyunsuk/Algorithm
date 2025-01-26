fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toLong() }

    var s = 0L
    var e = n

    while (s <= e) {
        val mid = (s + e) / 2
        val mul = (mid + 1) * (n - mid + 1)
        if (mul == k) {
            print("YES")
            return
        } else if (mul < k) s = mid + 1
        else e = mid - 1
    }

    print("NO")
}