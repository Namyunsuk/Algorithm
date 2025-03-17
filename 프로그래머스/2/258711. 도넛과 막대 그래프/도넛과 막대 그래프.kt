import java.util.*

val graph = Array(1000001){mutableListOf<Int>()}
val inCount =  Array(1000001){0}
val outCount =  Array(1000001){0}
val vis = Array(1000001){false}
val q = LinkedList<Int>()
var created = 0
var donut = 0
var stick = 0
var eight = 0

class Solution {
    fun solution(edges: Array<IntArray>): IntArray {
        var answer = mutableListOf<Int>()
        
        edges.forEach{
            val a = it[0]
            val b = it[1]
            graph[a].add(b)
            outCount[a]++
            inCount[b]++
        }
        
        for(i in 1..1000000){   // 생성정점 찾기
            if(inCount[i]==0&&outCount[i]>=2) {
                created = i
                vis[i] = true
                break
            }
        }

        graph[created].forEach{ v ->
            vis[v] = true
            if(inCount[v]>=2 && outCount[v]>=2){ // 8자
                eight++
            }else{ // donut or stick
                bfs(v)
            }
        }
        
        answer.add(created)
        answer.add(donut)
        answer.add(stick)
        answer.add(eight)
        return answer.toIntArray()
    }
}


fun bfs(start:Int){
    q.offer(start)
    while(!q.isEmpty()){
        val cur = q.poll()
        if(outCount[cur]==0){
            stick++
            break
        }
        val next = graph[cur].get(0)
        if(inCount[next]>=2 && outCount[next]>=2){
            eight++
            break
        }
        if(vis[next]){
            donut++
            break
        }
        vis[next]=true
        q.offer(next)
    }
}