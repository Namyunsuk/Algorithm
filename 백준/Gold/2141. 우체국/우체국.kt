data class Country(val index: Int, val cnt: Int)

val countries = mutableListOf<Country>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var totalPeople = 0L
    repeat(n) {
        val (i, num) = readLine().split(" ").map { it.toInt() }
        totalPeople += num
        countries.add(Country(i, num))
    }
    countries.sortWith(compareBy { it.index })

    val mid = (totalPeople+1) / 2
    var acc = 0L

    for (country in countries) {
        acc += country.cnt
        if(acc>=mid){
            print(country.index)
            break
        }
    }
}