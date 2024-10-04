import java.util.*
import kotlin.math.*

val inRecords = mutableMapOf<Int, MutableList<Int>>()
val outRecords = mutableMapOf<Int, MutableList<Int>>()
val totalTime = mutableMapOf<Int, Int>()
val totalAmount = mutableMapOf<Int, Int>()
var defaultTime = 0
var defaultAmount = 0
var unitTime = 0
var unitAmount = 0
val lastTime = 23*60+59

class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var answer = mutableListOf<Int>()
        
        defaultTime = fees[0]
        defaultAmount = fees[1]
        unitTime = fees[2]
        unitAmount = fees[3]
        
        records.forEach{
            val time = it.split(" ")[0]
            val hour = time.split(":")[0].toInt()
            val minute = time.split(":")[1].toInt()
            val carNumber = it.split(" ")[1].toInt()
            val state = it.split(" ")[2]
            
            totalAmount[carNumber] = 0
            
            
            if(state=="IN"){
                if(inRecords[carNumber]==null){
                    inRecords[carNumber] = mutableListOf<Int>()
                }
                inRecords[carNumber]!!.add(hour*60+minute)
            }else{
                if(outRecords[carNumber]==null){
                    outRecords[carNumber] = mutableListOf<Int>()
                }
                outRecords[carNumber]!!.add(hour*60+minute)
            }
        }
        
        inRecords.keys.forEach{ carNumber->
            inRecords[carNumber]!!.forEach{ inTime->
                if(outRecords[carNumber]?.size?:0==0){ // 출차 기록X
                    totalTime[carNumber] = totalTime[carNumber]?.plus(lastTime - inTime)?:(lastTime - inTime)
                    return@forEach
                }
                val outTime = outRecords[carNumber]!!.get(0)
                totalTime[carNumber] = totalTime[carNumber]?.plus(outTime - inTime)?:(outTime - inTime)
                outRecords[carNumber]!!.removeFirst()
            }
        }
        
        totalTime.keys.forEach{ carNumber->
            totalAmount[carNumber] = calAmount(totalTime[carNumber]!!)
        }
        
        val sortedKeys = totalAmount.keys.toMutableList()
        sortedKeys.sort()
        
        for(i in sortedKeys){
            answer.add(totalAmount[i]!!)
        }
        
        return answer.toIntArray()
    }
    
    fun calAmount(time:Int):Int{
        val differ = time - defaultTime
        if(differ<=0) return defaultAmount
        return ceil(differ.toDouble() / unitTime).toInt() * unitAmount + defaultAmount
    }
}