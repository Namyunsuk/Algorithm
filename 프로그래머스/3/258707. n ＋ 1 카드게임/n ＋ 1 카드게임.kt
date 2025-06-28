var hands = mutableListOf<Int>()
var back = mutableListOf<Int>()
var coins = 0

class Solution {
    fun solution(coin: Int, cards: IntArray): Int {
        var answer: Int = 1
        val n = cards.size
        coins = coin
        
        for(i in 0 until cards.size/3){
            hands.add(cards[i])
        }
        
        var i = cards.size/3
        
        while(i < cards.size){
            var flag = false
            hands.sort()
            
            // 카드 뽑기
            val card1 = cards[i++]
            val card2 = cards[i++]
            back.add(card1)
            back.add(card2)
            back.sort()

            
            // 가진 카드에서 해결            
            for(handCard in hands){
                val target = n+1-handCard
                if(hands.binarySearch(target)>=0){
                    hands.remove(handCard)
                    hands.remove(target)
                    answer++
                    flag = true
                    break
                }
            }
            
            if(flag) continue
            
            // 1장은 보유 + 1장은 뽑은 카드1
            for(handCard in hands){
                if(coins<1) break
                val target = n+1-handCard
                if(back.binarySearch(target)>=0){
                    hands.remove(handCard)
                    back.remove(target)
                    answer++
                    flag = true
                    coins--
                    break
                }
            }
            
            if(flag) continue
            
            // 2장 다 Back에서
            for(backCard in back){
                if(coins<2) break
                val target = n+1-backCard
                if(back.binarySearch(target)>=0){
                    back.remove(backCard)
                    back.remove(target)
                    answer++
                    coins-=2
                    flag = true
                    break
                }
            }                
        
            
            if(!flag) break
        }
        
        
        return answer
    }
}