fun main(): Unit = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        val nums = mutableListOf<Int>()
        nums.addAll(readLine().split(" ").map { it.toInt() })

        var result = 0L
        var maxValue = nums[n - 1]

        for (i in nums.size - 2 downTo 0) {
            if (nums[i] > maxValue) maxValue = nums[i]
            result += (maxValue - nums[i])
        }
        println(result)
    }
}