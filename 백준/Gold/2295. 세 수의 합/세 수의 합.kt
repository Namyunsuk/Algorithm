val nums = mutableListOf<Int>()
val two = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        nums.add(readLine().toInt())
    }

    nums.sort()

    for (i in 0 until n) {
        for (j in i until n) {
            two.add(nums[i] + nums[j])
        }
    }

    two.sort()

    for (i in nums.size - 1 downTo 0) {
        for (j in 0 until i) {
            val diff = nums[i] - nums[j]
            if (binarySearch(two, diff)) {
                print(nums[i])
                return
            }
        }
    }
}

fun binarySearch(arr: List<Int>, target: Int): Boolean {
    var st = 0
    var en = arr.size - 1

    while (st < en) {
        val mid = (st + en) / 2
        if (target < arr[mid]) en = mid - 1
        else if (arr[mid] < target) st = mid + 1
        else return true
    }
    return false
}