val alphabet = Array(26) { 0 }
val sb = StringBuilder()
var stack = mutableListOf<Char>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val word = readLine()

        for (c in word) {
            alphabet[c.code - 'a'.code] += 1
        }

        dfs(word.length)

        for (i in alphabet.indices) {
            alphabet[i] = 0
        }
    }

    print(sb)
}

fun dfs(len: Int) {
    if (len == stack.size) {
        for (c in stack) {
            sb.append(c)
        }
        sb.appendLine()
        return
    }

    for (i in 0..<26) {
        if (alphabet[i] <= 0) continue
        alphabet[i]--
        stack.add('a' + i)
        dfs(len)
        stack.removeLast()
        alphabet[i]++
    }
}