import java.util.*

val graph = Array<MutableList<Int>>(1000002){mutableListOf()}
val nodeInCount = Array<Int>(1000002){0}
val nodeOutCount = Array<Int>(1000002){0}

class Solution {
    fun solution(edges: Array<IntArray>): IntArray {
        var answer = IntArray(4)
        
        edges.forEach{ (start, end)->
            graph[start].add(end)
            nodeInCount[end]++
            nodeOutCount[start]++
        }
        
        for(i in 0..1000001){
            if(nodeInCount[i]==0 && nodeOutCount[i]>=2){
                answer[0] = i
                break
            }
        }
        
        graph[answer[0]].forEach{node->
            answer[bfs(node)]++
        }
        
        return answer
    }
    
    fun bfs(start:Int):Int{
        val q = LinkedList<Int>()
        q.offer(start)
        var n = 0
        
        while(!q.isEmpty()){
            val cur  = q.poll()
            n++
            if(cur == start && n>1){ // 제일 처음에 걸리는 것 방지 위해 n>1조건 삽입
                return 1
            }
            graph[cur].forEach{it->
                if(graph[it].size==2) return 3
                q.offer(it)
            }
        }
        return 2
    }
}


// 나가는 엣지 존재, 들어오는거 X
// 도넛: 나에게 돌아옴 다시
// 막대: 안돌아옴
// 8: 들어오는거 존재하면서 나가는거 2개 존재