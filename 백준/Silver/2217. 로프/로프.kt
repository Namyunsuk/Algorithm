val ropes = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var maxValue = Int.MIN_VALUE

    repeat(n) {
        ropes.add(readLine().toInt())
    }

    ropes.sort()

    for (i in ropes.indices) {
        val rope = ropes[i]
        maxValue = maxOf(maxValue, (n - i) * rope, rope)
    }

    print(maxValue)
}