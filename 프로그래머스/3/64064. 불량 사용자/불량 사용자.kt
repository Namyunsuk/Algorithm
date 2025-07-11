val cases = mutableSetOf<List<String>>()

class Solution {
    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
        var answer = 0
        
        
        dfs(0,user_id, banned_id, mutableListOf())
        
        println(cases)
        
        return cases.size
    }
}

fun dfs(idx:Int, user_id: Array<String>, banned_id: Array<String>, selected:MutableList<String>){
    if(selected.size == banned_id.size){
        cases.add(selected.sorted().toList())
        return
    }
    
    for(i in idx until banned_id.size){
        val bannedId = banned_id[i]
        
        user_id.forEach{ user ->
            if(!isSame(user, bannedId)) return@forEach
            if(selected.contains(user)) return@forEach
            selected.add(user)
            dfs(i+1, user_id, banned_id, selected)
            selected.remove(user)
        }
    }
}

fun isSame(user:String, banned:String):Boolean{
    if(user.length != banned.length) return false
    
    for(i in 0 until user.length){
        if(banned[i]=='*') continue
        if(user[i]!=banned[i]) return false
    }
    
    return true
}