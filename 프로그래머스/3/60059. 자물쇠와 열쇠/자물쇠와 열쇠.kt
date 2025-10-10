var m = 0
var n = 0

val newLock = Array(70){Array(70){2}}
var targetCnt = 0

class Solution {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        var answer = true
        
        m = key.size
        n = lock.size + 2*(m-1)
        
        for(i in 0 until lock.size){
            for(j in 0 until lock.size){
                if(lock[i][j]==0) targetCnt++
            }
        }
        
        for(i in m-1 until n - (m-1)){
            for(j in m-1 until n - (m-1)){
                newLock[i][j] = lock[i - (m-1)][j - (m-1)]
            }
        }
        
        val end = (lock.size -1) + (m-1)
        
        for(i in 0 .. end){
            for(j in 0 .. end){
                // 회전하며 비교
                for(rot in 0 until 4){
                    rotate(key)
                    if(isValidKey(i,j,key)) return true   
                }
            }
        }
        
        
        return false
    }
    
    fun rotate(key:Array<IntArray>){
        val newKey = Array(m){Array(m){0}}
        
        for(i in 0 until m){
            for(j in 0 until m){
                newKey[i][j] = key[i][j]
            }
        }
        
        for(i in 0 until m){
            for(j in 0 until m){
                key[i][j] = newKey[j][m-1-i]
            }
        }
    }
    
    fun isValidKey(i:Int, j:Int, key:Array<IntArray>):Boolean{
        var isValid = true
        var cnt = 0

        for(s in 0 until m){
            for(e in 0 until m){
                // 열쇠 값
                val keyValue = key[s][e]
                // 자물쇠 값
                val lockValue = newLock[i+s][j+e]

                // 임의로 생성한 칸일 경우
                if(lockValue == 2) continue
                // 홈과 홈이 만나는 경우 비교 종료
                if(keyValue==1 && lockValue==1){
                    isValid = false
                    break
                }

                // 자물쇠가 맞지 않을 경우 비교 종료
                if(keyValue!=1 && lockValue==0){
                    isValid = false
                    break
                }

                if(keyValue==1 && lockValue==0) cnt++
            }
            if(!isValid) break
        }

        if(cnt == targetCnt) return true
        return false
    }
}






