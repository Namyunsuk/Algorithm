const val INF = 1e9.toInt()


fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    repeat(n){
        readLine()
    }

    for(i in 1..n){
        for(j in 1..n){
            print("$i ")
        }
        println()
    }
}
