data class Meet(val start: Int, val end: Int)

val meets = mutableListOf<Meet>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val (s, e) = readLine().split(" ").map { it.toInt() }
        meets.add(Meet(s, e))
    }

    meets.sortWith(compareBy<Meet> { it.end }.thenBy { it.start })

    var start = 0
    var result = 0
    for (meet in meets) {
        if (meet.start < start) continue
        result++
        start = meet.end
    }

    print(result)
}