fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val students = readLine().split(" ").map { it.toInt() }.toMutableList()
    students.sort()

    var result = 0L

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val diff = -(students[i] + students[j])
            val lowIdx = lowerIdx(students, j + 1, diff)
            val upIdx = upperIdx(students, j + 1, diff)
            result += (upIdx - lowIdx)
        }
    }

    print(result)
}

fun lowerIdx(arr: List<Int>, s: Int, target: Int): Int {
    var st = s
    var en = arr.size

    while (st < en) {
        val mid = (st + en) / 2
        if (target <= arr[mid]) en = mid
        else st = mid + 1
    }

    return st
}

fun upperIdx(arr: List<Int>, s: Int, target: Int): Int {
    var st = s
    var en = arr.size

    while (st < en) {
        val mid = (st + en) / 2
        if (target < arr[mid]) en = mid
        else st = mid + 1
    }

    return st
}