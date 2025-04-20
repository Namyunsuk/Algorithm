import java.util.*

val nums = mutableListOf<Int>()
val distinctNums = mutableListOf<Int>()
val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    nums.addAll(readLine().split(" ").map { it.toInt() })
    distinctNums.addAll(nums.distinct())
    distinctNums.sort()

    nums.forEach{
        sb.append("${binarySearch(it)} ")
    }

    print(sb)
}

fun binarySearch(target: Int): Int {
    var s = 0
    var e = distinctNums.size - 1

    while (s < e) {
        val mid = (s + e) / 2
        if (target < distinctNums[mid]) e = mid - 1
        else if (target > distinctNums[mid]) s = mid + 1
        else return mid
    }

    return s
}