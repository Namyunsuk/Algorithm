var answer = mutableListOf(-1)
var diff = 0
var canWin = false

class Solution {
    fun solution(n: Int, info: IntArray): IntArray {
        
        dfs(0, mutableListOf(0,0,0,0,0,0,0,0,0,0,0), n, info)
        
        return answer.toIntArray()
    }
}

fun dfs(cnt:Int, selected:MutableList<Int>, n: Int, info: IntArray){
    if(cnt == n){
        
        var aSum = 0
        var lSum = 0
        for(i in 0..10){
            val score = 10 - i
            val apeach = info[i]
            val lion = selected[i]
            
            if(apeach == 0 && lion == 0) continue
            else if(apeach >= lion) aSum += score
            else lSum += score
        }
        
        if(lSum > aSum){
            // 더 큰 차이인 경우
            if(lSum - aSum > diff){
                answer.clear()
                answer.addAll(selected.toList())
                diff = lSum - aSum
            }
            // 차이가 같을 경우
            else if(lSum - aSum == diff){
                for(i in 10 .. 0){
                    if(selected[i] > answer[i]) {
                        answer.clear()
                        answer.addAll(selected.toList())
                        break
                    }
                }
            }
        }
        return
    }
    // [1,1,2,3,0,2,0,0,0,0,0]
    // [1,1,2,0,1,2,2,0,0,0,0]
    
    val remainArrow = n - cnt
    
    for(score in 0 .. 10){
        val i = 10 - score
        
        // 해당 점수를 어피치보다 많이 쏜 경우 또 쏠필요 X
        if(selected[i] > info[i]) continue
        // 해당 점수를 얻을 화살 수가 여유 있을 경우
        if(remainArrow > info[i]){
            selected[i] += info[i] + 1

            dfs(cnt + info[i] + 1, selected, n, info)

            selected[i] -= info[i] + 1
        }
        
        // 쏠 곳이 없는 경우
        if(i == 10){
            selected[10] += remainArrow
            
            dfs(cnt + remainArrow, selected, n , info)

            selected[10] -= remainArrow
        }
    }   
}

// info => 10, 9, 8 .. 점수 순임
// 점수차이 같을 경우 => 낮은 점수를 더 많이 맞힌 순서
// 이길 방법 없을 경우 -1