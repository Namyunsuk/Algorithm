var round = 1
val hands = mutableListOf<Int>()
val draw = mutableListOf<Int>()

class Solution {
    fun solution(coin: Int, cards: IntArray): Int {
        val n = cards.size
        var coins = coin
        for(i in 0 until n/3){
            hands.add(cards[i])
        }
        var idx = n/3
        while(true){
            if(idx>=n) break
            
            draw.add(cards[idx++])
            draw.add(cards[idx++])
            
            if(canGo(n,hands)){
                round++
                continue
            }
            
            if(coins>=1){
                var flag = false
                for(card in hands){
                    if(draw.contains(n+1-card)){
                        hands.remove(card)
                        draw.remove(n+1-card)
                        coins--
                        round++
                        flag=true
                        break
                    }
                }
                if(flag) continue
            }
            
            if(coins>=2 && canGo(n,draw)){
                coins-=2
                round++
            }else{
                break
            }
        }
        
        return round
    }
}


fun canGo(n:Int, hands:MutableList<Int>):Boolean{
    for(card in hands){
        if(hands.contains(n+1-card)){
            hands.remove(card)
            hands.remove(n+1-card)
            return true
        }
    }
    return false
}












