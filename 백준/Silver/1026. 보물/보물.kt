val A = mutableListOf<Int>()
val B = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    A.addAll(readLine().split(" ").map { it.toInt() })
    B.addAll(readLine().split(" ").map { it.toInt() })

    A.sort()
    B.sortByDescending { it }

    var result = 0

    for (i in 0 until n) {
        result += A[i] * B[i]
    }

    print(result)
}