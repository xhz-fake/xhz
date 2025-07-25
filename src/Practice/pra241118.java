package Practice;
import java.util.ArrayList;

public class pra241118 {
    public static void main(String[] args) {
/*        int N = (int) (1e7);
        int M=(int)(1e3);
        int arr[] = new int[N];
        int arr1[] = new int[9];
        int arr2[]=new int[M];
        int n, index,ans;
        int temp;
        for (int i = 197; i <= N; i++) {
            n = (int) Math.ceil(Math.log10(i));
            index = n - 1;
//          n=String.valueOf(n).length();
            temp = i;
            while (temp > 0) {
                arr1[index] = temp % 10;
                temp /= 10;
                index--;
                if(index<0){
                    break;
                }
            }
            for (int j = 0; j < n; j++) {
                arr[j] = arr1[j];
            }
            for (int k = n; k < N; k++) {
                for (int j = k - n; j < k; j++) {
                    arr[k] += arr[j];
                }
            }
            for (int j = 0; j < M; j++)
                for (int k = 0; k < M; k++) {
                    if (arr[k] == temp) {
                        arr2[j] = arr[k];
                    }
                }
        }
        int start=0;
        int t;
        int end=arr2.length-1;
        while(start<end){
            t=arr2[start];
            arr2[start]=arr2[end];
            arr2[end]=t;
            end--;
            start++;
        }
        System.out.println(arr2[0]);
    }
*/
        long an=0;
        for (int i = 197; i < 1e7; i++) {
            if(pd(i)){
                an=i;
            }
        }
        System.out.println(an);
    }
    static boolean pd(int i){
      ArrayList<Integer> ans=new ArrayList<>();
      String s=""+i;
      int anss=0;
      for (int j = 0; j < s.length(); j++) {
          ans.add(s.charAt(j)-'0');
          anss+=s.charAt(j)-'0';
      }
      ans.add(anss);
      while(true){
          anss=(anss*2)-ans.get(0);
          ans.remove(0);
          ans.add(anss);
          if(anss==i){
              return true;
          }else if(anss>i) {
              return false;
          }
      }

    }
}
