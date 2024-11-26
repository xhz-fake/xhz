package calculate;

public class add {
    public static void main(String[] args) {
        int n = 33;
        long sum = 0;
        long first = 1;
        long second = 1;
        long next;

        for (int i = 0; i < n; i++) {
            if (i < 2) {
                sum += first;
            } else {
                next = first + second;
                sum += next;
                first = second;
                second = next;
            }
        }
        System.out.println(sum);
    }
}
