val rel = Array(2002) { mutableListOf<Int>() }
val vis = Array(2002) { false }
var check = false

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        rel[a].add(b)
        rel[b].add(a)
    }

    repeat(n - 1) {
        vis[it] = true
        dfs(it, 0)
        vis[it] = false

        if (check) {
            print(1)
            return
        }
    }

    print(0)
}

fun dfs(idx: Int, depth: Int) {
    if (depth == 4) {
        check = true
        return
    }
    for (other in rel[idx]) {
        if (vis[other]) continue
        vis[other] = true
        dfs(other, depth + 1)
        vis[other] = false
    }
}