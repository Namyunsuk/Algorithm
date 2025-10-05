val graph = Array(2502){""}
val p = Array(2502){-1}

class Solution {
    fun solution(commands: Array<String>): Array<String> {
        var answer = mutableListOf<String>()
        
        // printGraph()
        // println()
        
        for(command in commands){
            val splitted = command.split(" ")
            val op = splitted[0]
            
            if(op == "UPDATE"){
                if(splitted.size == 4){
                    val r = splitted[1].toInt()
                    val c = splitted[2].toInt()
                    val value = splitted[3]
                    
                    update(r,c,value)
                }else{
                    val value1 = splitted[1]
                    val value2 = splitted[2]
                    
                    updateAll(value1, value2)
                }
            }else if(op == "MERGE"){
                val r1 = splitted[1].toInt()
                val c1 = splitted[2].toInt()
                
                val r2 = splitted[3].toInt()
                val c2 = splitted[4].toInt()
                
                merge(r1, c1, r2, c2)
            } else if(op == "UNMERGE"){
                val r = splitted[1].toInt()
                val c = splitted[2].toInt()
                
                unmerge(r, c)
            }else{
                val r = splitted[1].toInt()
                val c = splitted[2].toInt()
                answer.add(print(r, c))
            }
            // println(splitted)
            // printGraph()
            // println()
        }
        
        
        return answer.toTypedArray()
    }
}

fun printGraph(){
    for(i in 1 ..4){
        for(j in 1..4){
            val x = toIndex(i, j)
            val value = if(graph[x] == "") "EMPTY" else graph[x]
            print("${value} ")
        }
        println()
    }
}

fun print(r:Int, c:Int):String{
    val x = toIndex(r, c)
    val pX = find(x)
    
    val value = graph[pX]
    
    return if(value == "") "EMPTY" else value
}

fun unmerge(r:Int, c:Int){
    val x = toIndex(r, c)
    val pX = find(x)
    val value = graph[pX]
    
    val unmergedList = mutableListOf<Int>()
    
    // 부모가 pX인 것들 전부 초기화 (-1로)
    for(i in 1..2500){
        val X = find(i)
        if(X == pX){
            unmergedList.add(i)
        }
    }
    
    unmergedList.forEach{
        p[it] = -1
    }
    
    graph[pX] = ""
    graph[x] = value
}

fun merge(r1:Int, c1:Int, r2:Int, c2:Int){
    val x1 = toIndex(r1, c1)
    val x2 = toIndex(r2, c2)
    
    val pX1 = find(x1)
    val pX2 = find(x2)
    
    if(pX1 == pX2) return
    
    // pX2만 값을 가진 경우 pX2로 합병
    if(graph[pX1]=="" && graph[pX2]!=""){
        p[pX1] = pX2
    }else{
        graph[pX2] = ""
        p[pX2] = pX1
    }
}

fun updateAll(value1:String, value2:String){
    // 최적화 가능
    for(i in 1..2500){
        val pX = find(i)
        if(graph[pX] == value1){
            graph[pX] = value2
        }
    }
}

fun update(r:Int, c:Int, value:String){
    val x = toIndex(r,c)
    
    val pX = find(x)
    
    graph[pX] = value
}

fun find(x:Int):Int{
    if(p[x] == -1){
        return x
    }
    
    return find(p[x])
}

fun toIndex(r:Int, c:Int):Int{
    return 50*(r-1) + c
}