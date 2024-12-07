import kotlin.math.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var ans = 0

    val nums = mutableListOf<Int>()
    var sum = 0
    for (e in 2..n) {
        if (isPrime(e)) {
            sum += e
            nums.add(e)
            while (sum > n) {
                sum -= nums[0]
                nums.removeFirst()
            }
            if (sum == n) ans++
        }
    }
    print(ans)
}

fun isPrime(num: Int): Boolean {
    val sqrtNum = (sqrt(num.toDouble())).toInt()
    for (i in 2..sqrtNum) {
        if (num % i == 0) return false
    }
    return true
}
