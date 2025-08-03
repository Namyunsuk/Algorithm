import java.util.*;

class Solution {
    
    static List<String> routes = new ArrayList<>();
    static boolean flag = false;
    static boolean[] vis = new boolean[10002];
    
    static String[] answer;
    
    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (o1, o2) -> {
            return o1[1].compareTo(o2[1]);
        });
        
        answer = new String[tickets.length+1];
        
        routes.add("ICN");
        dfs("ICN", tickets);
        
        return answer;
    }
    
    void dfs(String cur, String[][] tickets){
        if(flag) return;
        if(routes.size() == tickets.length+1){
            for(int i=0;i<routes.size();i++){
                answer[i] = routes.get(i);
            }
            flag = true;
            return;
        }
        
        for(int i=0;i<tickets.length;i++){
            if(vis[i]) continue;
            if(!tickets[i][0].equals(cur)) continue;
            routes.add(tickets[i][1]);
            vis[i] = true;
            dfs(tickets[i][1], tickets);
            vis[i] = false;
            routes.remove(routes.size()-1);
        }
    }
}



