import kotlin.math.*

class Solution {
    fun solution(s: String): Int {
        var answer = Int.MAX_VALUE
        
        for(unit in 1..s.length){
            var i = 0
            var str = ""
            var unitStr = ""
            var cnt = 1
            
            while(i<s.length){
                unitStr = if(i+unit<s.length) s.substring(i,i+unit) else s.substring(i,s.length)
                while(true){
                    if(i+unit>=s.length || i+2*unit > s.length) break
                    var nextStr = s.substring(i+unit, i+2*unit)
                    if(unitStr!=nextStr) break
                    cnt++
                    i+=unit
                }
                if(cnt!=1){
                    str+=cnt.toString()
                }
                cnt=1
                str+=unitStr
                
                i+=unit
                
            }
            answer = min(str.length, answer)
        }
        
        return answer
    }
}