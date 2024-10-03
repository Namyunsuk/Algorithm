var todayYear = 0
var todayMonth = 0
var todayDay = 0
val termsInfo = mutableMapOf<String, Int>()

class Solution {
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        val temp = mutableListOf<Int>()
        
        todayYear = today.split(".")[0].toInt()
        todayMonth = today.split(".")[1].toInt()
        todayDay = today.split(".")[2].toInt()
        
        terms.forEach{
            val term = it.split(" ")[0]
            val period = it.split(" ")[1]
            termsInfo[term] = period.toInt()
        }
        
        var term_index = 0
        var index = 0
        
        privacies.forEach{
            term_index++
            val term = it.split(" ")[1]
            val date = it.split(" ")[0]
            var term_year = date.split(".")[0].toInt()
            var term_month = date.split(".")[1].toInt()
            var term_day = date.split(".")[2].toInt()
            
            var period = termsInfo[term]?:0
            val period_year = period/12
            val period_month = period - 12*period_year
            
            var cur_year = term_year + period_year
            var cur_month = 0
            var cur_day = term_day
            if(term_month+period_month>12){
                cur_year+=1
                cur_month = term_month+period_month-12
            }else{
                cur_month = term_month+period_month
            }
            
            if(todayYear>cur_year){
                temp.add(term_index)
            }else if(todayYear == cur_year){
                if(todayMonth>cur_month){
                    temp.add(term_index)
                }else if(todayMonth == cur_month){
                    if(todayDay >= cur_day ){
                        temp.add(term_index)
                    }
                }
            }
            
        }
        
        answer = IntArray(temp.size)
        temp.forEachIndexed{index, value->
            answer[index] = value
        }
        
        
        return answer
    }
}