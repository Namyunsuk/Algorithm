val friendsGiftCount  = mutableMapOf<String, Int>().withDefault{0}
val friendsGifteeCount  = mutableMapOf<String, Int>().withDefault{0}
val friendsGiftEachCount  = mutableMapOf<Pair<String, String>, Int>().withDefault{0}
val giftCount = mutableMapOf<String, Int>().withDefault{0}


class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        var answer: Int = 0
        friends.forEach{it ->
            giftCount[it] = 0
        }
        
        gifts.forEach{it->
            val gifter = it.split(" ")[0]
            val giftee = it.split(" ")[1]
            friendsGiftCount[gifter]=friendsGiftCount[gifter]?.plus(1)?:1 //선물 준 횟수
            friendsGifteeCount[giftee] = friendsGifteeCount[giftee]?.plus(1)?:1 // 선물 받은 횟수
            friendsGiftEachCount[Pair(gifter, giftee)]= friendsGiftEachCount[Pair(gifter, giftee)]?.plus(1)?:1
        }
        
        for(name in friends){
            for(other in friends){
                if(name == other) continue


                    val myCount = friendsGiftEachCount[Pair(name, other)]?:0
                    val OtherCount = friendsGiftEachCount[Pair(other, name)]?:0
                    if(myCount>OtherCount){ //더 많이 선물 한 경우
                        giftCount[name] = giftCount[name]?.plus(1)?:1
                    }
                    else if(myCount == OtherCount){ // 선물 수가 같은 경우 -> 선물지수
                        val meGifter = friendsGiftCount[name]?:0
                        val meGiftee = friendsGifteeCount[name]?:0
                        val meCount = meGifter - meGiftee
                        
                        val otherGifter = friendsGiftCount[other]?:0
                        val otherGiftee = friendsGifteeCount[other]?:0
                        val otherCount = otherGifter - otherGiftee
                        
                        if(meCount>otherCount){
                            giftCount[name] = giftCount[name]?.plus(1)?:1
                        }
                    }
            }
        }
        answer = giftCount.values.maxOrNull()?:0
        
        
        
        return answer
    }
}