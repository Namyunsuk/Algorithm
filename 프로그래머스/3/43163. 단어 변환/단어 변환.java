class Solution {
    
    static boolean[] vis = new boolean[51];
    static int answer = 0;
    
    public int solution(String begin, String target, String[] words) {
        
        dfs(0,begin, target, words);
        
        return answer;
    }
    
    static void dfs(int depth, String cur, String target, String[] words){
        if(cur.equals(target)){
            if(answer==0){
                answer = depth;
                return;
            }
            answer = Math.min(answer, depth);
            return;
        }
        
        for(int i=0;i<words.length;i++){
            if(vis[i]) continue;
            if(!isValid(cur, words[i])) continue;
            vis[i] = true;
            dfs(depth+1, words[i], target, words);
            vis[i] = false;
        }
    }
    
    
    static boolean isValid(String cur, String word){
        int cnt = 0;
        
        for(int i=0; i<cur.length(); i++){
            if(cur.charAt(i)!=word.charAt(i)) cnt++;
            if(cnt>=2) return false;
        }
        return true;
    }
}