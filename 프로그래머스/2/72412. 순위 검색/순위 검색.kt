val infoMap = HashMap<String, MutableList<Int>>()

class Solution {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        var answer = mutableListOf<Int>()
        
        info.forEach{ it->
            makeInfoMap(it.split(" "), 0, "")
        }
        
        infoMap.values.map{it.sort()}
        
        for(q in query){
            val splittedQ = q.split(" ").filter{it != "and"}
            val language = splittedQ[0]
            val position = splittedQ[1]
            val career = splittedQ[2]
            val soulFood = splittedQ[3]
            val score = splittedQ[4].toInt()
            
            val key = language+position+career+soulFood
            if(infoMap[key]==null) answer.add(0)
            else{
              answer.add(infoMap[key]!!.size - lowerIndex(infoMap[key]!!, score))
            } 
        }
        
        return answer.toIntArray()
    }
    
    fun lowerIndex(scores:List<Int>, target:Int):Int{
        var s = 0
        var e = scores.size
        
        while(s<e){
            val mid = (s+e)/2
            if(scores[mid] >= target) e = mid
            else s = mid+1
        }
        
        return s
    }
    
    fun makeInfoMap(info:List<String>, depth:Int, key:String){
        if(depth ==4){
            if(infoMap[key]==null) infoMap[key] = mutableListOf()
            infoMap[key]!!.add(info[4].toInt())
            return
        }
        
        makeInfoMap(info, depth+1, key+"-")
        makeInfoMap(info, depth+1, key+info[depth])
    }
}