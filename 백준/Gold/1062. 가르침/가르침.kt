import kotlin.math.*

val default = listOf('a', 'n', 't', 'i', 'c')
var alphabets = mutableListOf<Char>()
val vis = Array(26) { false }
val words = mutableListOf<String>()
var maxCnt = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    repeat(n) {
        val word = readLine()
        words.add(word)
        word.forEach {
            if (!default.contains(it)) {
                alphabets.add(it)
            }
        }
    }
    alphabets = alphabets.toSet().toMutableList()
    if (k < 5) {
        print(0)
        return
    }

    if (k == 26) {
        print(n)
        return
    }

    default.forEach { vis[it.code - 'a'.code] = true }

    dfs(0, 0, min(k - 5, alphabets.size), alphabets.size)

    print(maxCnt)
}

fun dfs(index: Int, selectCnt: Int, maxSelectCnt: Int, alphabetsSize: Int) {
    if (selectCnt == maxSelectCnt) {
        var cnt = 0
        words.forEach { word ->
            if (word.length == 8) {
                cnt++
                return@forEach
            }
            if (word.substring(4..<word.length - 4).all { vis[it.code - 'a'.code] }) {
                cnt++
            }
        }
        maxCnt = max(maxCnt, cnt)
        return
    }

    for (i in index..<alphabetsSize) {
        vis[alphabets[i].code - 'a'.code] = true
        dfs(i + 1, selectCnt + 1, maxSelectCnt, alphabetsSize)
        vis[alphabets[i].code - 'a'.code] = false
    }

}