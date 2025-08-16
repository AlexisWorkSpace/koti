fun main() {
    val cantOfOne:Int = readln()!!.toInt()
    val binString:String = readln()!!

    println(countSubstring(binString, cantOfOne))

}

fun countSubstring(stringToCount:String, charToCount:Int): Int {
    var prefixSum = 0
    var result = 0
    val countMap = mutableMapOf<Int,Int>()
    countMap[0] = 1

    for(char in stringToCount){
        if(char == '1') prefixSum++
        result += countMap.getOrDefault(prefixSum - charToCount, 0)
        countMap[prefixSum] = countMap.getOrDefault(prefixSum, 0) + 1
    }
    return result
}
