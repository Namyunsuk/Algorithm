import kotlin.math.*

data class Egg(var s: Int = 0, var w: Int = 0)

lateinit var arr: Array<Egg>
lateinit var vis: Array<Boolean>
var maxCnt = 0
const val NO_HAND = -1
const val NO_EGG = -2

fun main(): Unit = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    arr = Array(n) { Egg() }
    vis = Array(n) { false }
    repeat(n) { i ->
        val (s, w) = readLine().split(" ").map { it.toInt() }
        arr[i].s = s
        arr[i].w = w
    }

    dfs(0, n, 0)

    print(maxCnt)
}


fun dfs(hand: Int, n: Int, destroyCnt: Int) {
    if (hand == NO_HAND || hand == NO_EGG || destroyCnt + 1 == n) {
        maxCnt = max(maxCnt, destroyCnt)
        return
    }

    for (i in 0..<n) {
        if (i == hand) continue
        if (arr[i].s <= 0) continue
        arr[hand].s -= arr[i].w
        arr[i].s -= arr[hand].w

        if (arr[hand].s <= 0) {
            val nextEgg = findNextEgg(hand, n)
            if (arr[i].s <= 0) dfs(nextEgg, n, destroyCnt + 2)
            else dfs(nextEgg, n, destroyCnt + 1)
        } else {
            val nextEgg = findNextEgg(hand, n)
            if (arr[i].s <= 0) dfs(nextEgg, n, destroyCnt + 1)
            else dfs(nextEgg, n, destroyCnt)
        }

        arr[hand].s += arr[i].w
        arr[i].s += arr[hand].w
    }
}

fun findNextEgg(hand: Int, n: Int): Int {
    var next = NO_EGG
    if (hand == n - 1) return NO_HAND
    for (i in hand + 1..<n) {
        if (arr[i].s > 0) {
            next = i
            break
        }
    }
    return next
}