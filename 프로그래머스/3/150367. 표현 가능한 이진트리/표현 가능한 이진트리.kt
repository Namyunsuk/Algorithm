import kotlin.math.*

class Solution {
    fun solution(numbers: LongArray): IntArray {
        var answer = mutableListOf<Int>()
        
        numbers.forEach{ num->
            
            var binaryNum = num.toString(2)
            var powN = 0
            while((2.0).pow(powN).toInt() - 1 < binaryNum.length) powN++
            val targetLen = (2.0).pow(powN).toInt() - 1
            while(binaryNum.length != targetLen){
                binaryNum = "0"+binaryNum
            }

            if(isValid(binaryNum)) answer.add(1)
            else answer.add(0)
        }
        
        return answer.toIntArray()
    }
    
    fun isValid(binary:String):Boolean{
        if(binary.length == 1) return true

        
        val parent = binary.length /2
        val left = binary.take(parent)
        val right = binary.takeLast(parent)
        
        if(binary[parent] == '0'){
            if(binary.any{it == '1'}) return false
            return true
        }
        
        return isValid(left) && isValid(right)
    }
}
