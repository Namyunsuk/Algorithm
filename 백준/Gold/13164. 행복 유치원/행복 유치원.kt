var datas = mutableListOf<Int>()
val differ = mutableListOf<Int>()

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val input = readLine().split(" ").map { it.toInt() }
    datas.addAll(input)

    datas = datas.distinct().toMutableList()

    for (i in 1 until datas.size) {
        differ.add(datas[i] - datas[i - 1])
    }

    differ.sort()

    repeat(k - 1) {
        differ.removeLast()
    }

    print(differ.sum())
}