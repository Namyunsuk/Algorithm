class Solution {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        dfs(0,0,0,target, numbers);
        
        return answer;
    }
    
    static void dfs(int index, int cnt, int sum, int target, int[] numbers){
        if(cnt == numbers.length){
            if(sum == target) answer++;
            return;
        }
        
        for(int i=index; i<numbers.length; i++){
            dfs(i+1, cnt+1, sum+numbers[i], target, numbers);
            dfs(i+1, cnt+1, sum-numbers[i], target, numbers);
        }
    }
}














