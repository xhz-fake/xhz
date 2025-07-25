package Practice;
import java.util.*;
import java.util.TreeMap;

class Solution {
    public String[] findRelativeRanks(int[] score) {
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        score=new int[10010];
        for(int i=0;i<n;i++){
            score[i]=in.nextInt();
        }
        TreeMap<Integer,String>map=new TreeMap<>((a,b)->b-a);
        for(int i=0;i<n;i++){
            map.put(score[i],"");
        }
        int t=0;
        for (int key:map.keySet()){
            if(t==0){
                map.put(key,"Gold Medal");
            }
            else if(t==1){
                map.put(key,"Silver Medal");
            }
            else if(t==2){
                map.put(key,"Bronze Medal");
            }else{
                map.put(key,""+t);
            }
            t++;
        }
        String []ans=new String[n];
        for(int i=0;i<n;i++){
            ans[i]=map.get(score[i]);
        }
        in.close();
        return ans;
    }
}