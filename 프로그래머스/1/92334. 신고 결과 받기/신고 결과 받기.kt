val reports = mutableMapOf<String, MutableList<String>>()
val badCount = mutableMapOf<String, Int>()

class Solution {
    fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
        var answer = mutableListOf<Int>()
        
        id_list.forEach{
            reports[it] = mutableListOf<String>()
        }
        
        for(i in report){
            val user = i.split(" ")[0]
            val bad = i.split(" ")[1]
            
            if(isFirstReport(user, bad)){
                badCount[bad] = badCount[bad]?.plus(1)?:1
                reports[user]!!.add(bad)
            }
        }
        
        for(user in id_list){
            var count = 0
            reports[user]!!.forEach{bad->
                if(badCount[bad]!!>=k) count++
            }
            answer.add(count)
        }
        
        return answer.toIntArray()
    }
    
    fun isFirstReport(user:String, bad: String):Boolean{
        return !reports[user]!!.contains(bad)
    }
}