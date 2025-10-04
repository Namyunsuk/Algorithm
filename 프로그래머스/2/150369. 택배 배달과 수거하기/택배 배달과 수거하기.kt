var deliveryCount = 0
var pickUpCount = 0

class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0
        
        for(i in n-1 downTo 0){
            deliveryCount += deliveries[i]
            pickUpCount += pickups[i]
            
            while(deliveryCount>0  || pickUpCount>0 ){
                deliveryCount-=cap
                pickUpCount-=cap
                answer+=(i+1)*2
            }
        }
        
        return answer
    }
}