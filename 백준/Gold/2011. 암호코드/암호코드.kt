val dp = Array(5002) { 0 }

fun main() = with(System.`in`.bufferedReader()) {
    val num = readLine()

    if(num[0]=='0'){
        print(0)
        return
    }
    dp[1] = 1
    if(num.length>=2){
        if (num[1] == '0' && !(num[0] == '1' || num[0] == '2')) {
            print(0)
            return
        } else if (num.substring(0..1).toInt() <= 26) {
            if (num[1] == '0') dp[2] = 1
            else dp[2] = 2
        } else {
            dp[2] = 1
        }
    }


    for (i in 3..num.length) {
        if (num[i - 1] == '0') {
            if (!(num[i - 2] == '1' || num[i - 2] == '2')) {
                print(0)
                return
            }
            dp[i] = dp[i - 2]
        } else if (num[i - 2] != '0' && num.substring(i - 2..i - 1).toInt() <= 26) {
            dp[i] = dp[i - 1] + dp[i - 2]
        } else {
            dp[i] = dp[i - 1]
        }
        dp[i] %= 1000000
    }

    print(dp[num.length] % 1000000)
}