package calculate;

public class sorting240912booble {
    public static void main(String[] args) {
        int[] ia = new int[]{2, 1, 3, 6, 4,  5,  8, 0, 7};
        System.out.println("排序前： ");
        for(int i=0;i<ia.length;i++){
            int v=ia[i];
            System.out.print("| "+v);
        }
        System.out.println();
        System.out.println("排序后： ");
        for (int i = 0; i < ia.length; i++){
            for (int j=0; j <ia.length-1-i ; j++) {
                if (ia[j]>ia[j+1]) {
                    int tem=ia[j];
                    ia[j]=ia[j+1];
                    ia[j+1]=tem;
                }
            }
        }
        for (int i = 0; i < ia.length; i++) {
            int v=ia[i];
            System.out.print("| "+v);
        }
    }
}
