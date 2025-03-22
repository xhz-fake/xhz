package practice;
public class pra250314 {
    public static void main(String[] args) {
        int n= 7000000;
        int num;
        int ans=0;


        for(int i=9000000;i>=n;i--){
            String numStr=String.valueOf(i);
            int len=numStr.length();
            String[] sarr=new String[len+1];
            int[] arr=new int[90000];
            int[] brr=new int[90000];
            brr[0]=0;

            for(int k=0;k<len;k++){
                sarr[0]=String.valueOf(0);
                sarr[k+1]= String.valueOf(numStr.charAt(k));
            }

            for(int j=0; j<20000;j++){
                if(j<=len) {
                    num = Integer.parseInt(sarr[j]);
                    arr[j] = num;
                }
                if(j!=0) {
                    brr[j]+=brr[j-1]+j;
                }
                if(j>len){
                    arr[j]=brr[j-1]-brr[j-len-1];
                }
            }
            for (int j = 0; j < arr.length; j++) {
                if(arr[j]==i){
                    ans=arr[j];
                }
            }

        }
        System.out.println(ans);


    }
}
