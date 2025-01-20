val nums = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    nums.addAll(readLine().split(" ").map { it.toInt() })

    val m = readLine().toInt()
    val input = readLine().split(" ").map { it.toInt() }

    nums.sort()

    input.forEach {
        println(binarySearch(nums, it))
    }
}

fun binarySearch(arr: List<Int>, target: Int): Int {
    var st = 0
    var en = arr.size - 1

    while (st <= en) {
        val mid = (st + en) / 2
        if (arr[mid] < target) st = mid + 1
        else if (target < arr[mid]) en = mid - 1
        else return 1
    }
    return 0
}