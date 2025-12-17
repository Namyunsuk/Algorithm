import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private data class Planet(
    val x : Int,
    val y : Int,
    val z : Int,
    val i : Int
)

private lateinit var parent : IntArray

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var stk : StringTokenizer
    val n = br.readLine().toInt()
    parent = IntArray(n){ i -> i }
    val starArray = Array(n){ Planet(0, 0, 0, 0) } // a별 x좌표, a별 y좌표 a별 z좌표, 인덱스
    val edgeList : MutableList<Triple<Int, Int, Int>> = mutableListOf() // a별, b별, a->b cost
    repeat(n){ i ->
        stk = StringTokenizer(br.readLine ())
        val x = stk.nextToken().toInt()
        val y = stk.nextToken().toInt()
        val z = stk.nextToken().toInt()
        starArray[i] = Planet(x, y, z, i)
    }
    val xList = starArray.sortedWith(compareBy{ it.x })
    val yList = starArray.sortedWith(compareBy{ it.y })
    val zList = starArray.sortedWith(compareBy{ it.z })
    for(i in 0 until n - 1){
        edgeList.add(Triple(xList[i].i, xList[i + 1].i , xList[i + 1].x - xList[i].x))
        edgeList.add(Triple(yList[i].i, yList[i + 1].i, yList[i + 1].y - yList[i].y))
        edgeList.add(Triple(zList[i].i, zList[i + 1].i, zList[i + 1].z - zList[i].z))
    }
    edgeList.sortWith( compareBy{ it.third } )
    var totalCost = 0
    edgeList.forEach{
        val a = it.first
        val b = it.second
        val cost = it.third
        if(findParent(a) != findParent(b)){
            union(a, b)
            totalCost += cost
        }
    }
    print(totalCost)
}

private fun findParent(x: Int): Int{
    if(x == parent[x]) return x
    parent[x] = findParent(parent[x])
    return parent[x]
}

private fun union(a : Int, b: Int) {
    val A = findParent(a)
    val B = findParent(b)
    if (A == B) return
    parent[B] = A
}
