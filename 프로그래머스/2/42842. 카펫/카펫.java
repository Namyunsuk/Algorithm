class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for(int i=1; i<=yellow;i++){
            if(yellow%i!=0) continue;
            int w = i;
            int h = yellow/w;
            
            int brownCnt = (w+2)*2 + 2*h;
            
            if(brownCnt == brown){
                if(w+2>=h+2){
                    answer[0] = w+2;
                    answer[1] = h+2;
                }else{
                    answer[0] = h+2;
                    answer[1] = w+2;
                }

                break;
            }
        }
        
        return answer;
    }
}