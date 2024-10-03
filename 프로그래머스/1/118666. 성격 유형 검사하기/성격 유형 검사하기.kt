val typesScore = mutableMapOf<String, Int>()
val scores = arrayOf(3,2,1,0,1,2,3)

class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""
        
        typesScore["R"] = 0
        typesScore["T"] = 0
        typesScore["C"] = 0
        typesScore["F"] = 0
        typesScore["J"] = 0
        typesScore["M"] = 0
        typesScore["A"] = 0
        typesScore["N"] = 0
        
        for(i in 0 .. survey.size-1){
            var type = ""
            var choice = choices[i]
            if(choice<=3){
                type = survey[i][0].toString()
                typesScore[type] = typesScore[type]?.plus(scores[choice-1])?:scores[choice-1]
            } else if(choice>=5){
                type = survey[i][1].toString()
                typesScore[type] = typesScore[type]?.plus(scores[choice-1])?:scores[choice-1]
            }
        }
        
        if(typesScore["R"]?:0>=typesScore["T"]?:0){
            answer+="R"
        }else{
            answer+="T"
        }
        
        if(typesScore["C"]?:0>=typesScore["F"]?:0){
            answer+="C"
        }else{
            answer+="F"
        }
        
        if(typesScore["J"]?:0>=typesScore["M"]?:0){
            answer+="J"
        }else{
            answer+="M"
        }
        
        if(typesScore["A"]?:0>=typesScore["N"]?:0){
            answer+="A"
        }else{
            answer+="N"
        }
        
        return answer
    }
}