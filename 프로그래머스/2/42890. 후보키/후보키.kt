var answer = mutableListOf<List<Int>>()

class Solution {
    fun solution(relation: Array<Array<String>>): Int {
        dfs(0, relation, relation[0].size, mutableListOf())
        
        println(answer)
        
        return answer.size
    }
    
    fun dfs(index:Int, relation: Array<Array<String>>, columnSize:Int,selected:MutableList<Int>){
        if(selected.isNotEmpty())isValid(relation, selected.toList())
        
        for(i in index until columnSize){
            
            selected.add(i)
            dfs(i+1, relation, columnSize, selected)
            selected.remove(i)
        }
    }
    
    fun isValid(relation: Array<Array<String>>, selected:List<Int>){
        val keys = mutableListOf<String>()
        for(tuple in relation) {
            var keyStr = ""
            for(i in selected){
                keyStr+=tuple[i]
            }
            if(keys.contains(keyStr)) return
            keys.add(keyStr)
        }
        
        val removedKeys = mutableListOf<List<Int>>()
        
        for(candidateKeys in answer) {
            if(candidateKeys.size > selected.size){
                if(candidateKeys.containsAll(selected)){
                    removedKeys.add(candidateKeys)
                }
            }else if(candidateKeys.size < selected.size){
                if(selected.containsAll(candidateKeys)) return
            }
        }
        
        for(key in removedKeys){
            answer.remove(key)
        }
        
        answer.add(selected)
    }
}