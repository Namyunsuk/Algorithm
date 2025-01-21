import kotlin.math.*

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val values = readLine().split(" ").map { it.toInt() }

    var s = 0
    var e = n - 1
    var l = -1
    var r = -1
    var min = Int.MAX_VALUE

    while (s < e) {
        val sum = abs(values[s] + values[e])
        if (sum < min) {
            l = s
            r = e
            min = sum
        }
        if(values[s] + values[e]<0) s++
        else if(values[s] + values[e]>0) e--
        else break
    }

    print("${values[l]} ${values[r]}")
}