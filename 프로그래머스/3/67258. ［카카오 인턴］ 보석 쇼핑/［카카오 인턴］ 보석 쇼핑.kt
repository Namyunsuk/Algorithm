var gemsCount = HashMap<String, Int>()
lateinit var distinctGems:List<String>
val buying = mutableSetOf<String>()
var minLength = Int.MAX_VALUE

class Solution {
    fun solution(gems: Array<String>): IntArray {
        var answer = mutableListOf<Int>()
        
        for(gem in gems.distinct()){
            gemsCount[gem] = 0
        }
        
        val distinctGemsSize = gems.distinct().size
        
        var s = 0
        
        increase(gems[s])
        
        var minS = 0
        var minE = 0
        
        if(buying.size == distinctGemsSize){
            answer.add(1)
            answer.add(1)
            return answer.toIntArray()
        } 
        
        for(e in 1 until gems.size){
            increase(gems[e])
            while(buying.size == distinctGemsSize){
                val len = e-s+1
                if(len<minLength){
                    minS = s
                    minE = e
                    minLength = len
                }
                
                if(minLength == distinctGemsSize){
                    answer.add(minS+1)
                    answer.add(minE+1)
        
                    return answer.toIntArray()
                }
                
                decrease(gems[s++])
                
                if(s>=e) break
            }
        }
        
        answer.add(minS+1)
        answer.add(minE+1)
        
        return answer.toIntArray()
    }
}

fun increase(gem:String){
    gemsCount[gem] = gemsCount[gem]!! + 1
    buying.add(gem)
}

fun decrease(gem:String){
    gemsCount[gem] = gemsCount[gem]!! - 1
    if(gemsCount[gem]!! == 0) buying.remove(gem)
}