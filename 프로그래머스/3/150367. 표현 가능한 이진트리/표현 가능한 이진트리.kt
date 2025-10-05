// 7:36
import kotlin.math.*

var isValid = true

class Solution {
    fun solution(numbers: LongArray): IntArray {
        var answer = mutableListOf<Int>()
        
        for(number in numbers){
            isValid = true
            val binary = number.toString(2)
            val newBinary = makeValidSize(binary)
            
            if(!isValidRoot(newBinary)){
                answer.add(0)
                continue
            }
            
            isValidTree(newBinary)
            
            if(!isValid){
                answer.add(0)
                continue
            }
            answer.add(1)
            
        }
        
        
        return answer.toIntArray()
    }
}

fun makeValidSize(num:String):String{
    var number = num
    var validSize = 0
    var i = 0
    while(true){
        i+=1
        val len = (2.0).pow(i).toInt() - 1
        if(len >= number.length){
            validSize = len
            break
        }
    }
    
    while(number.length!=validSize){
        number = "0"+number
    }
    
    return number
}

fun isValidRoot(num:String):Boolean{
    val rootIndex = num.length / 2
    
    
    return num[rootIndex] == '1'
}



fun isValidTree(num:String){
    // 유효하지 않은 트리로 판별된 경우 종료
    if(!isValid) return
    
    // 리프노드인 경우 종료
    if(num.length==1) return
    
    // 부모노드가 0일 때, 모든 자식노드 중 1이 있는 경우 유효하지 않음
    val parentIndex = num.length / 2
    if(num[parentIndex] == '0'){
        val left = num[parentIndex-1]
        val right = num[parentIndex+1]
        if(num.any{it=='1'}){
            isValid = false
        }
        return
    }
    
    val leftChild = num.substring(0, parentIndex)
    val rightChild = num.substring(parentIndex+1, num.length)
    
    // 왼쪽 자식 검증
    isValidTree(leftChild)
    
    // 오른쪽 자식 검증
    isValidTree(rightChild)
}

// 1. 문자열의 길이는 홀수(2^n - 1)여야 한다. (앞에 0을 붙여도 됨. 이때, 0은 2개 이하로만 붙일 수 있음) -> o
// 2. 루트노드는 무조건 1이어야 한다. (루트 노드) -> o
// 3. 부모노드가 0일 경우, 모든 자식노드들(하위노드들)이 1이면 안된다. == 자식도 0이어야 한다. -> o
// 4. 재귀적으로 판단해야 할 듯 (트리크기가 1이면 return) -> o





