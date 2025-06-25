class Solution {
    fun solution(s: String): IntArray {
        var answer = mutableListOf<Int>()
        
        val tmp = s.split("},{")
        
        val splittedTuple = mutableListOf<List<Int>>()
        for(i in 0 until tmp.size){
            val splitStr = tmp[i].split("{{","}}",",").filter{it.isNotBlank()}.map{it.toInt()}
                splittedTuple.add(splitStr)
        }
        
        splittedTuple.sortBy{it.size}
        
        answer.add(splittedTuple[0][0])
        
        for(i in 1 until splittedTuple.size){
            answer.addAll(splittedTuple[i] - answer)
        }
        
        return answer.toIntArray()
    }
}