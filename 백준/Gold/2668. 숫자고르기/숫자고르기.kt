val vis = Array(102) { false }
val arr = mutableListOf<Int>()
var cnt = 0
var ans = mutableListOf<Int>()

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    arr.add(0)
    repeat(n) {
        arr.add(readLine().toInt())
    }

    for (num in 1..n) {
        vis[num] = true
        dfs(arr[num], num)
        vis[num] = false
    }

    ans.sortBy { it }
    println(cnt)
    ans.forEach{println(it)}
}

fun dfs(start: Int, des: Int) {
    if (start == des) {
        ans.add(des)
        cnt++
        return
    }
    if (vis[start]) return
    vis[start] = true
    dfs(arr[start], des)
    vis[start] = false
}