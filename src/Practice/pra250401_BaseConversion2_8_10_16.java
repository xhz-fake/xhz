package Practice;

public class pra250401_BaseConversion2_8_10_16 {
    public static void main(String[] args) {
        int count=0;
        int cnt;
        int sum;
        for(int num=1;num<Integer.MAX_VALUE;num++){
            cnt=0;

/////////////////////////////////////////////////////////////////////
            sum=0;
            String snum=Integer.toString(num);///////////

            for(int i=0;i<snum.length();i++) {
                char t= snum.charAt(i);
                sum+=t-'0';
            }
            if(num%sum==0) {
                cnt++;
            }else {
                continue;
            }
///////////////////////////////////////////////////////////////////////////////
            sum=0;
            String binary=Integer.toBinaryString(num);//////////
            for(int i=0;i<binary.length();i++) {
               char t=binary.charAt(i);
                sum+=t-'0';
            }
            if(num%sum==0) {
                cnt++;
            }else {
                continue;
            }
////////////////////////////////////////////////////////////////////////////////
            sum=0;
            String octal=Integer.toOctalString(num);/////////
            for(int i=0;i<octal.length();i++) {
                char t=octal.charAt(i);
                sum+=t-'0';
            }
            if(num%sum==0) {
                cnt++;
            }else {
                continue;
            }
///////////////////////////////////////////////////////////////////////////////////
            sum=0;
            String hex=Integer.toHexString(num);/////////////////////
            for(int i=0;i<hex.length();i++) {
                char t=hex.charAt(i);
                if(t>='0'&&t<='9'){
                    sum+=t-'0';
                }else{
                    sum+=t-'a'+10;
                }
            }
            if(num%sum==0) {
                cnt++;
            }else {
                continue;
            }
//////////////////////////////////////////////////////////////////////////////////
            if(cnt==4) {
                count++;
            }
            if(count==2023){
                System.out.println(num);
                break;

            }
        }

    }


}
