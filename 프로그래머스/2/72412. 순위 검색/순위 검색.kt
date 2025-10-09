import java.util.*

val infoMap = HashMap<String, MutableList<Int>>()

class Solution {
    fun solution(info: Array<String>, query: Array<String>): IntArray {
        var answer = mutableListOf<Int>()
        
        info.forEach{
            dfs(it.split(" "), "", 0)
        }
        
        infoMap.values.map{it.sort()}
        
        query.forEach{ q->
            val sb = StringBuilder()
            val splitted = q.split(" ").filter{it!="and"}
            for(i in 0 until 4){
                sb.append(splitted[i])
            }
            
            val key = sb.toString()
            
            if(infoMap[key] == null){
                answer.add(0)
                return@forEach
            }

            answer.add(cal(infoMap[key]!!, splitted[4].toInt()))
        }
        
        return answer.toIntArray()
    }
    
    fun cal(arr:List<Int>, target:Int):Int{
        var s = 0
        var e = arr.size
        
        while(s < e){
            val mid = (s + e)/2
            
            if(arr[mid] >= target) e = mid
            else s = mid+1
        }
        
        return arr.size - s
    }
    
    fun dfs(info:List<String>, key:String, depth:Int){
        if(depth == 4){
            if(infoMap[key] == null) infoMap[key] = mutableListOf()
            infoMap[key]!!.add(info[4].toInt())
            
            return
        }
        
        dfs(info, key + "-", depth+1)
        dfs(info, key + info[depth], depth+1)
    }
}


