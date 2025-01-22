val a = mutableListOf<Int>()
val b = mutableListOf<Int>()
val c = mutableListOf<Int>()
val d = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val two2 = IntArray(n * n)
    repeat(n) {
        val input = readLine().split(" ").map { it.toInt() }
        a.add(input[0])
        b.add(input[1])
        c.add(input[2])
        d.add(input[3])
    }

    c.sort()
    d.sort()

    var idx = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            two2[idx++] = (c[i] + d[j])
        }
    }

    two2.sort()

    var result = 0L
    for (i in 0 until n) {
        for (j in 0 until n) {
            val diff = -(a[i] + b[j])
            val lowIdx = lowerIdx(two2, diff)
            val upIdx = upperIdx(two2, diff)
            result += (upIdx - lowIdx)
        }
    }

    print(result)
}

fun lowerIdx(arr: IntArray, target: Int): Int {
    var s = 0
    var e = arr.size

    while (s < e) {
        val mid = (s + e) / 2
        if (target <= arr[mid]) e = mid
        else s = mid + 1
    }


    return s
}

fun upperIdx(arr: IntArray, target: Int): Int {
    var s = 0
    var e = arr.size

    while (s < e) {
        val mid = (s + e) / 2
        if (target < arr[mid]) e = mid
        else s = mid + 1
    }

    return s
}