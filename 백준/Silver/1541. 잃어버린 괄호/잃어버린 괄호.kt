fun main(): Unit = with(System.`in`.bufferedReader()) {
    val str = readLine()

    var result = 0
    var num = 0
    var minusFlag = false
    for (s in str) {
        if (s == '+' || s == '-') {
            if (minusFlag) result -= num
            else result += num
            if (s == '-') minusFlag = true
            num = 0
        } else {
            num = num * 10 + (s.code - '0'.code)
        }
    }

    if (minusFlag) result -= num
    else result += num

    print(result)
}