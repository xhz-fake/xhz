package practice;



public class pra241213 {
    public static void main(String[] args) {
        String text = "he";
// text.hashCode();
// MD5加密计算 计算一个int 加密之后的数据 就是一个int
        int n = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            n = 31 * n + c;
            System.out.println(n + " -- " + c + " -- " + (int) c);
        }
        System.out.println("加密的结果：" + n);
        int n1 = 3325;
        System.out.println(n1%31);
    }
}
// 3325
// 倒数第一位的可能性：( 39 70 101 ---- ' F e)
// 倒数第二位：
// 1：倒数第一位为39时： 3325-39 = 3286 3286%31 = 14 14+31=45 45+31=76
//        76+31=107
// 帮我把下面的数字转为 ASCII 对应：
// 45 76 107 ： - L k
// 2:倒数第一位为70时 3325-70 = 3255 3255%31 = 24 24+31=55 55+31=86
//        86+31=117
// 55 86 117 : 7 V u
// 3: 倒数第一位为101时 3325-101 = 3224 3224%31 = 0 结果为0 表示通过加密时明文
//        编码乘31就可以得到 3224/31=104
// 104： e
//
// 密码的结果：
// 最后一位对应的可能性 三种
// 1： '
// -' L' K'
// 2: F
// 7F VF uF
// 3： e
// he

