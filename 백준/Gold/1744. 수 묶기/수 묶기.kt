val minusNums = mutableListOf<Int>()
val plusNums = mutableListOf<Int>()
var zeroCnt = 0

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val num = readLine().toInt()
        if (num == 0) zeroCnt++
        else if (num > 0) plusNums.add(num)
        else if (num < 0) minusNums.add(num)
    }

    minusNums.sort()
    plusNums.sortByDescending { it }

    var minusResult = 0
    var plusResult = 0

    for (i in 0 until minusNums.size step 2) {
        if (i == minusNums.size - 1) {
            if (zeroCnt == 0) minusResult += minusNums[i]
            break
        }
        val cur = minusNums[i]
        val next = minusNums[i + 1]
        minusResult += cur * next
    }

    var i = 0
    while (i < plusNums.size) {
        if (i == plusNums.size - 1) {
            plusResult += plusNums[i]
            break
        }
        val cur = plusNums[i]
        val next = plusNums[i + 1]
        if (cur == 1 || next == 1) {
            plusResult += cur
            i++
            continue
        }
        plusResult += cur * next
        i += 2
    }

    print(minusResult + plusResult)
}