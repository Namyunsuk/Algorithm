data class Date(val month: Int, val day: Int) : Comparable<Date> {
    override fun compareTo(other: Date) =
        compareValuesBy(this, other, { it.month }, { it.day })
}

data class Flower(val birth: Date, val dead: Date)

val flowers = mutableListOf<Flower>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n) {
        val (s1, s2, e1, e2) = readLine().split(" ").map { it.toInt() }
        flowers.add(Flower(Date(s1, s2), Date(e1, e2)))
    }

    flowers.sortWith(compareBy<Flower> {
        it.birth
    }.thenBy {
        it.dead
    })

    var lastDate = Date(3, 1)
    val endDate = Date(11, 30)

    var result = 0
    var maxEndDate = Date(0, 0)
    for (flower in flowers) {
        if (flower.birth > lastDate) {
            lastDate = maxEndDate
            result++
        }

        if (lastDate > endDate) break

        if (flower.birth <= lastDate) {
            maxEndDate = maxOf(maxEndDate, flower.dead)
        }
    }
    if (lastDate != maxEndDate) result++
    lastDate = maxEndDate

    result = if (lastDate > endDate) result else 0
    print(result)
}