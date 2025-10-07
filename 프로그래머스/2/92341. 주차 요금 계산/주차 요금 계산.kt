import kotlin.math.*

val carNumbers = mutableSetOf<Int>()
val inRecords = HashMap<Int, Int>()
val timeRecords = HashMap<Int, Int>()
val isOut = Array(10002){false}


class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var answer = mutableListOf<Int>()
        val defaultTime = fees[0]
        val defaultFee = fees[1]
        val unitTime = fees[2]
        val unitFee = fees[3]
        
        for(record in records){
            val (t, carNum, type) = record.split(" ")
            val time = toMinute(t)
            val carNumber = carNum.toInt()
            
            if(type=="IN"){
                carNumbers.add(carNumber)
                
                inRecords[carNumber] = time
                isOut[carNumber] = false
            }else{
                val diff = time - inRecords[carNumber]!!
                timeRecords[carNumber] = (timeRecords[carNumber]?:0) + diff
                
                isOut[carNumber] = true
            }
        }
        
        val sortedCarNumbers = carNumbers.toList().sorted()
        
        // 출차 안된 것들 계산
        sortedCarNumbers.forEach{
            if(!isOut[it]){
                val diff = (23*60 + 59) - inRecords[it]!!
                timeRecords[it] = (timeRecords[it]?:0) + diff
                isOut[it] = true
            }
        }
        
        // 요금 계산
        sortedCarNumbers.forEach{
            val diff = timeRecords[it]!!
            if(diff <= defaultTime){
                answer.add(defaultFee)
                return@forEach
            }
            val fee = defaultFee + ceil((diff - defaultTime).toDouble() / unitTime).toInt() * unitFee
            answer.add(fee)
        }
        
        
        return answer.toIntArray()
    }
}

fun toMinute(time:String):Int{
    val (h, m) = time.split(":").map{it.toInt()}
    return h*60 + m
}