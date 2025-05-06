import java.util.*;

class Solution {
    static String[] nums;
    static HashSet<Integer> primeNums = new HashSet<>();
    static boolean[] vis = new boolean[9];
    
    public int solution(String numbers) {
        int answer = 0;
        
        nums = numbers.split("");
        
        
        dfs("", nums.length);
        
        
        
        return primeNums.size();
    }
    
    
    static void dfs(String numbers, int n){
        if(isPrime(numbers)){
            primeNums.add((Integer)Integer.parseInt(numbers));
        }
        
        for(int i=0;i<n;i++){
            if(vis[i]) continue;
            vis[i] = true;
            
            dfs(numbers+nums[i], n);
            
            vis[i] = false;
        }
    }
    
    static boolean isPrime(String numbers){
        if(numbers.equals("")) return false;
        int num = Integer.parseInt(numbers);
        if(num<=1) return false;
        if(num==2) return true;
        
        int sqrtNum = (int)Math.sqrt(num);
        
        for(int i=2;i<=sqrtNum;i++){
            if(num%i==0) return false;
        }
        
        return true;
    }
}











