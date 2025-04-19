import java.util.*

class Wallet(val w:Int, val h:Int)

val wallets = mutableListOf<Wallet>()

class Solution {
    fun solution(sizes: Array<IntArray>): Int {
        var answer: Int = 0
        
        sizes.forEach{
            val w = it[0]
            val h = it[1]
            
            if(w>h) wallets.add(Wallet(w, h))
            else wallets.add(Wallet(h, w))
        }
        
        var w = 0
        var h = 0
        
        for(wallet in wallets){
            w = maxOf(w, wallet.w)
            h = maxOf(h, wallet.h)
        }
        
        answer = w*h
        
        return answer
    }
}