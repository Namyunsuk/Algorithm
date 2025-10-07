import java.util.*

class Node(val num:Int, var prev:Int, var next:Int, var isDeleted:Boolean = false)

val rollBack = Stack<Int>()

class Solution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        val linkedList = Array(n){
            // 처음 => prev == -1
            if(it == 0) Node(it, -1, it+1)
            // 마지막 => next == -1
            else if(it == n-1) Node(it, it-1, -1)
            else Node(it, it-1, it+1)
        }
        
        var cur = k
        
        for(c in cmd){
            val splitted = c.split(" ")
            val op = splitted[0]
            
            if(op == "U"){
                val x = splitted[1].toInt()
                
                for(i in 0 until x){
                    cur = linkedList[cur].prev
                }
                
            } else if(op == "D"){
                val x = splitted[1].toInt()
                
                for(i in 0 until x){
                    cur = linkedList[cur].next
                }
                
            } else if(op == "C"){
                linkedList[cur].isDeleted = true
                rollBack.push(cur)
                
                // 처음 인 경우
                if(linkedList[cur].prev == -1){
                    // 그 다음 노드
                    val nextNode = linkedList[linkedList[cur].next]
                    nextNode.prev = -1
                    cur = linkedList[cur].next
                }
                // 마지막 인 경우
                else if(linkedList[cur].next == -1){
                    val prevNode = linkedList[linkedList[cur].prev]
                    prevNode.next = -1
                    cur = linkedList[cur].prev
                }else{
                    val prevNode = linkedList[linkedList[cur].prev]
                    val nextNode = linkedList[linkedList[cur].next]
                    
                    prevNode.next = nextNode.num
                    nextNode.prev = prevNode.num
                    
                    cur = nextNode.num
                }
                
            } else{
                val num = rollBack.pop()
                linkedList[num].isDeleted = false
                
                // 처음 인 경우
                if(linkedList[num].prev == -1){
                    // 그 다음 노드
                    val nextNode = linkedList[linkedList[num].next]
                    nextNode.prev = num
                }
                // 마지막 인 경우
                else if(linkedList[num].next == -1){
                    val prevNode = linkedList[linkedList[num].prev]
                    prevNode.next = num
                }else{
                    val prevNode = linkedList[linkedList[num].prev]
                    val nextNode = linkedList[linkedList[num].next]
                    
                    prevNode.next = num
                    nextNode.prev = num
                }
            }
        }
        
        
        val answer = StringBuilder()
        
        for(i in 0 until n){
            if(linkedList[i].isDeleted) answer.append("X")
            else answer.append("O")
        }

        return answer.toString()
    }
}