data class Pos(val x:Int, val y:Int)

val n = 52
val graph = Array(n*n){"EMPTY"}
val p = Array(n*n){-1}

class Solution {
    fun solution(commands: Array<String>): Array<String> {
        var answer = mutableListOf<String>()
        
        for(command in commands){
            val c = command.split(" ")
            if(c[0] == "UPDATE"){
                if(c.size==4){
                    update(c[1].toInt(), c[2].toInt(), c[3])
                }else{
                    updateAll(c[1], c[2])
                }
            }else if(c[0] == "MERGE"){
                merge(c[1].toInt(), c[2].toInt(), c[3].toInt(), c[4].toInt())
            }else if(c[0] == "UNMERGE"){
                unmerge(c[1].toInt(), c[2].toInt())
            }else if(c[0] == "PRINT"){
                answer.add(print(c[1].toInt(), c[2].toInt()))
            }
        }
        
        return answer.toTypedArray()
    }
}

fun convert(r:Int, c:Int):Int{
    return n*(r-1) + c
}

fun find(x:Int):Int{
    if(p[x]<0) return x
    p[x] = find(p[x])
    return p[x]
}

fun union(x:Int, y:Int):Boolean{
    val pX = find(x)
    val pY = find(y)
    
    if(pX==pY) return false
    p[pY] = pX
    return true
}

fun update(r:Int, c:Int, value:String) {
    val idx = convert(r,c)
    val parent = find(idx)
    graph[parent] = value
}

fun updateAll(value1:String, value2:String){
    for(i in 1 until n*n){
        if(graph[i]==value1){
            graph[i] = value2            
        }
    }
}

fun merge(r1:Int, c1:Int, r2:Int, c2:Int){
    val idx1 = convert(r1, c1)
    val idx2 = convert(r2, c2)
    val pX = find(idx1)
    val pY = find(idx2)
    
    var value = "EMPTY"
    if(graph[pX]!="EMPTY"&&graph[pY]!="EMPTY"){
        value = graph[pX]
    }else if(graph[pX]!="EMPTY"){
        value = graph[pX]
    }else if(graph[pY]!="EMPTY"){
        value = graph[pY]
    }
    
    if(pX==pY) return
    
    union(pX, pY)
    
    graph[pX] = value
    graph[pY] = "EMPTY"
}

fun unmerge(r:Int, c:Int){
    val idx = convert(r, c)
    val parent = find(idx)
    val value = graph[parent]
    graph[parent] = "EMPTY"
    
    val tmp = mutableListOf<Int>()
    for(i in 1 until n*n){
        if(find(i)==parent){
            tmp.add(i)
        }
    }
    
    tmp.forEach{it->
        graph[it] = "EMPTY"
        p[it] = -1
    }
    
    graph[idx] = value
}

fun print(r:Int, c:Int):String{
    val idx = convert(r,c)
    val parent = find(idx)
    return graph[parent]
}
