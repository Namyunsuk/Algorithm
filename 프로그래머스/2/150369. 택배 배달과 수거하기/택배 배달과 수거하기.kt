var deliveryCount = 0
var pickUpCount = 0

class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0

        var delivery = 0 // 남은 택배 상자
        var pickup = 0 // 남은 수거 상자

        // 무조건 왕복해야하기 때문에 가장 멀리 있는 집부터 거꾸로 방문
        for (i in n - 1 downTo 0) {
            // 배달 및 수거를 처리해야 하는 양
            delivery += deliveries[i]
            pickup += pickups[i]
            // 배달 및 수거를 전부 처리할 때까지 왕복
            while (delivery > 0 || pickup > 0) {
                delivery -= cap
                pickup -= cap
                // i+1번째 집과 물류창고를 왕복
                answer += (i + 1) * 2
            }
        }

        return answer
    }
}