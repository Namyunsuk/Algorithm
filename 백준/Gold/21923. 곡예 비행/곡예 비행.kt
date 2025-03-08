val arr = Array(1002) { Array(1002) { 0L } }
val upSum = Array(1002) { Array(1002) { 0L } }
val downSum = Array(1002) { Array(1002) { 0L } }

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    repeat(n) { i ->
        val input = readLine().split(" ").map { it.toLong() }
        input.forEachIndexed { j, v ->
            arr[i][j] = v
        }
    }

    for (j in 0 until m) {
        for (i in n - 1 downTo 0) {
            if (i == n - 1 && j == 0) {
                upSum[i][j] = arr[i][j]
            } else if (i == n - 1) {
                upSum[i][j] = upSum[i][j - 1] + arr[i][j]
            } else if (j == 0) {
                upSum[i][j] = upSum[i + 1][j] + arr[i][j]
            } else {
                upSum[i][j] = maxOf(upSum[i][j - 1], upSum[i + 1][j]) + arr[i][j]
            }
        }
    }

    for (j in m - 1 downTo 0) {
        for (i in n - 1 downTo 0) {
            if (i == n - 1 && j == m - 1) {
                downSum[i][j] = arr[i][j]
            } else if (i == n - 1) {
                downSum[i][j] = downSum[i][j + 1] + arr[i][j]
            } else if (j == m - 1) {
                downSum[i][j] = downSum[i + 1][j] + arr[i][j]
            } else {
                downSum[i][j] = maxOf(downSum[i][j + 1], downSum[i + 1][j]) + arr[i][j]
            }
        }
    }

    var result = Long.MIN_VALUE
    for (i in 0 until n) {
        for (j in 0 until m) {
            result = maxOf(result, upSum[i][j] + downSum[i][j])
        }
    }

    print(result)
}