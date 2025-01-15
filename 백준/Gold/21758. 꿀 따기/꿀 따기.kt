val honeys = mutableListOf<Int>()
lateinit var leftSum: Array<Int>
lateinit var rightSum: Array<Int>

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }
    honeys.addAll(input)

    leftSum = Array(n) { 0 }
    rightSum = Array(n) { 0 }

    leftSum[0] = honeys[0]
    rightSum[n - 1] = honeys[n - 1]

    for (i in 1 until n) {
        leftSum[i] = leftSum[i - 1] + honeys[i]
    }

    for (i in n - 2 downTo 0) {
        rightSum[i] = rightSum[i + 1] + honeys[i]
    }

    var answer = Int.MIN_VALUE

    for (i in 1 until n - 1) {
        // 벌통 왼쪽
        answer = maxOf(answer, rightSum[0] - honeys[n - 1] - honeys[i] + rightSum[0] - rightSum[i])

        // 벌통 오른쪽
        answer = maxOf(answer, leftSum[n - 1] - honeys[0] - honeys[i] + leftSum[n - 1] - leftSum[i])

        // 벌통 중간 어딘가
        answer = maxOf(answer, leftSum[i] - honeys[0] + rightSum[i] - honeys[n - 1])
    }

    print(answer)
}