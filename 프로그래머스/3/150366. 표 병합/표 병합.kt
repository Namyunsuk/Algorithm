val p = Array(2502){-1}
val arr = Array(2502){""}


class Solution {
    fun solution(commands: Array<String>): Array<String> {
        var answer = mutableListOf<String>()
        
        for(command in commands){
            val splitted = command.split(" ")
            val op = splitted[0]
            
            print("${op} ")
            if(op == "UPDATE" && splitted.size == 4){
                val r = splitted[1].toInt()
                val c = splitted[2].toInt()
                val value = splitted[3]
                
                println("$r $c $value")
                
                updateCell(r,c,value)
                
            }else if(op == "UPDATE" && splitted.size == 3){
                val value1 = splitted[1]
                val value2 = splitted[2]
                
                println("$value1 $value2")
                
                updateValue(value1, value2)
                
            }else if(op == "MERGE"){
                val r1 = splitted[1].toInt()
                val c1 = splitted[2].toInt()
                
                val r2 = splitted[3].toInt()
                val c2 = splitted[4].toInt()
                
                println("$r1 $c1 $r2 $c2")
                
                merge(r1,c1,r2,c2)
            }else if(op == "UNMERGE"){
                val r = splitted[1].toInt()
                val c = splitted[2].toInt()
                
                println("$r $c")
                
                unMerge(r,c)
            }else if(op=="PRINT"){
                val r = splitted[1].toInt()
                val c = splitted[2].toInt()
                
                println("$r $c")
                
                answer.add(print(r,c))
            }
            printArr()
            println()
        }
        
        return answer.toTypedArray()
    }
}

fun printArr(){
    for(i in 1..2){
        for(j in 1..2){
            val idx = posToIdx(i,j)
            val pX = find(idx)
            if(arr[pX] == "") print("EMPTY ")
            else print("${arr[pX]} ")
        }
        println()
    }
}

fun updateCell(r:Int, c:Int, value:String){
    val idx = posToIdx(r,c)
    val pX = find(idx)
    arr[pX] = value
}

fun updateValue(value1:String, value2:String){
    for(i in 1 .. 2501){
        if(arr[i] == value1) arr[i] = value2
    }
}

fun merge(r1:Int, c1:Int, r2:Int, c2:Int){
    val idx1 = posToIdx(r1, c1)
    val idx2 = posToIdx(r2, c2)
    
    union(idx1, idx2)
}

fun unMerge(r:Int, c:Int){
    val idx = posToIdx(r,c)
    val pX = find(idx)
    val value = arr[pX]
    
    val tmp = mutableListOf<Int>()
    
    for(i in 1..2501){
        if(find(i) == pX) tmp.add(i)
    }
    
    tmp.forEach{
        p[it] = -1
        arr[it] = ""
    }
    
    arr[idx] = value
    
}

fun print(r:Int, c:Int):String{
    val idx = posToIdx(r,c)
    val pX = find(idx)
    if(arr[pX] == "") return "EMPTY"
    return arr[pX]
}

fun find(x:Int):Int{
    if(p[x] < 0) return x
    p[x] = find(p[x])
    
    return p[x]
}

fun union(x:Int, y:Int):Boolean{
    val pX = find(x)
    val pY = find(y)
    
    if(pX == pY) return false
    if(arr[pX] == "" && arr[pY] == ""){
        p[pY] = pX
    } else if(arr[pX] == "" && arr[pY] != "") {
        p[pX] = pY
    } else if(arr[pX] != "" && arr[pY] == "") {
        p[pY] = pX
    } else {
        p[pY] = pX
    }
    
    return true
}

fun posToIdx(r:Int, c:Int):Int{
    return 50 * (r-1) + c
}






