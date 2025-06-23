import java.util.*
import kotlin.math.*

var maxSignUp = 0
var maxSalesSum = 0

class Solution {
    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        
        val m = emoticons.size
        
        val cases = (4.0).pow(m).toInt() - 1
        
        for(i in 0 until cases){
            var case = i.toString(4)
            while(case.length < m){
                case = "0" + case
            }
            
            val saleRates = mutableListOf<Int>()
            
            for(c in case){
                val rate = (c.code - '0'.code + 1) * 10
                saleRates.add(rate)
            }
            
            calculate(saleRates, users, emoticons)
        }
        
        answer.add(maxSignUp)
        answer.add(maxSalesSum)
        
        return answer.toIntArray()
    }
    
    fun calculate(saleRates:List<Int>,users:Array<IntArray>, emoticons: IntArray) {
        val m = emoticons.size
        var plusSignUpCounts = 0
        var emoticonSalesPrice = 0
        
        for(i in 0 until users.size){
            var sum = 0
            val user = users[i]
            for(j in 0 until m){
                val saleRate = saleRates[j]
                if(user[0] > saleRate) continue
                
                val emoticon = emoticons[j]
                sum+=((emoticon * (100 - saleRate)) / 100)
            }
            
            if(sum >= user[1]) plusSignUpCounts++
            else emoticonSalesPrice+=sum
        }
        
        if(maxSignUp < plusSignUpCounts){
            maxSignUp = plusSignUpCounts
            maxSalesSum = emoticonSalesPrice
        } else if(maxSignUp == plusSignUpCounts && maxSalesSum < emoticonSalesPrice){
            maxSalesSum = emoticonSalesPrice
        }   
    }
}