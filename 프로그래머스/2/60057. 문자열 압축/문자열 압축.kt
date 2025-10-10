class Solution {
    fun solution(s: String): Int {
        var answer = Int.MAX_VALUE
        
        val n = s.length
        
        if(n==1) return 1
        
        for(unit in 1 .. (n/2)){
            val len = solve(unit, s)
            if(len < answer) answer = len
        }
        
        return answer
    }
    
    fun solve(unit:Int, str:String):Int{
        var newStr = ""
        var cnt = 1
        var before = ""
        
        for(i in 0 until str.length step unit){
            if(i + unit > str.length) break
            val cur = str.substring(i, i+unit)
            if(i==0){
                before = cur
                continue
            }
            
            if(before == cur) cnt++
            else{
                if(cnt==1){
                    newStr+=before
                }else{
                    newStr+=(cnt.toString()) + before
                }
                
                before = cur
                cnt = 1
            }
        }
        
        if(cnt==1){
            newStr+=before
        }else{
            newStr+=(cnt.toString()) + before
        }
        
        // 자투리 계산
        val r = str.length % unit
        if(r!=0){
            val restStr = str.substring(str.length - r ,str.length)   
            newStr+=restStr
        }
        
        return newStr.length
    }
}









