val countries = mutableListOf<Country>()
val nums = mutableListOf<Int>()
val index = mutableListOf<Int>()
lateinit var leftSum: Array<Long>
lateinit var rightSum: Array<Long>

data class Country(val index: Int, val cnt: Int)

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    leftSum = Array(n) { 0L }
    rightSum = Array(n) { 0L }
    repeat(n) {
        val (i, num) = readLine().split(" ").map { it.toInt() }
        countries.add(Country(i, num))
    }
    countries.sortWith(compareBy { it.index })

    countries.forEach { country ->
        index.add(country.index)
        nums.add(country.cnt)
    }

    leftSum[0] = nums[0].toLong()
    for (i in 1 until n) {
        leftSum[i] = leftSum[i - 1] + nums[i]
    }

    rightSum[n - 1] = nums[n - 1].toLong()
    for (i in n - 2 downTo 0) {
        rightSum[i] = rightSum[i + 1] + nums[i]
    }

    var accSum = 0L
    for (i in 1 until n) {
        accSum += leftSum[i - 1]
    }

    var minSum = accSum
    var result = n - 1
    for (i in n - 2 downTo 0) {
        accSum += (rightSum[i + 1] - leftSum[i])
        if (accSum <= minSum) {
            minSum = accSum
            result = i
        }
    }
    print(index[result])
}