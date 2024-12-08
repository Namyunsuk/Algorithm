val vis = Array(100001) { 0 }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = Array(n) { 0 }
    val input = readLine().split(" ").map { it.toInt() }
    input.forEachIndexed { i, v ->
        arr[i] = v
    }

    var s = 0
    var ans = 0L
    for (e in 0..<n) {
        vis[arr[e]]++
        while (vis[arr[e]] > 1 && s < e) {
            vis[arr[s]]--
            s++
        }
        if (vis[arr[e]] == 1) {
            if (s < e) {
                ans += (e - s)
            }
        }
    }
    ans += arr.size
    print(ans)
}
