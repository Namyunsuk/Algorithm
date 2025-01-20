var nums = mutableListOf<Int>()
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }
    nums.addAll(input)
    nums.sort()
    nums = nums.distinct().toMutableList()

    input.forEach {
        sb.append("${binarySearch(nums, it)} ")
    }

    print(sb)
}


fun binarySearch(arr: List<Int>, target: Int): Int {
    var st = 0
    var en = arr.size - 1

    while (st < en) {
        val mid = (st + en) / 2
        if (target < arr[mid]) en = mid - 1
        else if (arr[mid] < target) st = mid + 1
        else return mid
    }

    return st
}