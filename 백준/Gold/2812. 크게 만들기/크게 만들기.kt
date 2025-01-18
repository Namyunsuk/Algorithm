import java.util.*

val s1 = Stack<Int>()
val s2 = Stack<Int>()

val sb = StringBuilder()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    var (n, k) = readLine().split(" ").map { it.toInt() }
    val nums = readLine().split("").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList()

    if (n <= k) {
        print(0)
        return
    } else if (n == k - 1) {
        print(nums.max())
        return
    }
    nums.reverse()
    nums.forEach { s1.push(it) }

    s2.push(s1.pop())

    while (!s1.isEmpty()) {
        val num = s1.pop()
        while (k > 0 && !s2.isEmpty() && s2.peek() < num) {
            s2.pop()
            k--
        }
        s2.push(num)
    }

    repeat(k) {
        s2.pop()
    }

    for (i in s2) {
        sb.append(i)
    }

    print(sb)
}
