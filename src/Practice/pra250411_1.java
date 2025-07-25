package Practice;

public class pra250411_1 {

    public static void main(String[] args) {
        int count = 0;
        int add ;

        String ss="1235678";
        int sint=Integer.parseInt(ss);
        System.out.println(sint+"牛逼");

        for (int i = 1; i < 10000000; i++) {
            add=0;
            String Snum = Integer.toBinaryString(i);
            char[] Cnum;
            Cnum = Snum.toCharArray();
            for (char num : Cnum) {
                if (num=='1') {
                    add++;
                }
            }
            if (add == 3) {
                count++;
            }
            if(count==23){
                System.out.println(i);
                break;
            }
        }
    }
}


