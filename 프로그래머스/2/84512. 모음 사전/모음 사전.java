class Solution {
    static String[] alphabets = {"A","E","I","O","U"};
    static int ans = 0;
    static int cnt = 0;
    
    public int solution(String word) {
        int answer = 0;
        
        dfs("", word);
        
        return ans;
    }
    
    static void dfs(String str,String des){
        if(ans!=0) return;
        if(str.equals(des)){
            ans = cnt;
            return;
        }
        if(str.length() == 5) return;
        
        for(int i=0; i<5; i++){
            cnt++;
            dfs(str+alphabets[i], des);
        }
    }
}






