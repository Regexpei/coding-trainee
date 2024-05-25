package cn.regexp.coding.trainee.complexity;

public class FiboTest {

    public static void main(String[] args) {

        /*
            斐波那契数列：0 1 1 2 3 5 8 13 ...

            方法一：递归，性能差
                求第 n 个，相当于 第 n - 1个 + 第 n - 2个
                用数学中的函数可以表示为
                {
                    f(n) = f(n - 1) + f(n + 1), n > 2
                    f(n) = n - 1, 0 < n <= 2
                    f(n) = -1, n <= 0
                }
                
            方法二：累加，性能好
                求第 n 个，则先算出第 n - 1 个，依次类推，直到第 1 个和第 2 个

         */

        for (long i = 0; i < 10; i++) {
            System.out.println("第" + i + "个：" + fibo1(i));
            System.out.println("第" + i + "个：" + fibo2(i));
            System.out.println("第" + i + "个：" + fibo3(i));
            System.out.println("第" + i + "个：" + fibo4(i));
        }
    }

    private static long fibo3(long n) {
        if (n <= 0) {
            return -1;
        }

        if (n <= 2) {
            return n - 1;
        }

        long first = 0;
        long second = 1;

        while (n-- > 2) {
            second = first + second;
            first = second - first;
        }

        return second;
    }

    private static long fibo4(long n) {
        if (n <= 0) {
            return -1;
        }

        double c = Math.sqrt(5);
        return (long) ((Math.pow((1 + c) / 2, n - 1) - Math.pow((1 - c) / 2, n - 1)) / c);
    }


    private static long fibo2(long n) {
        if (n <= 0) {
            return -1;
        }

        if (n <= 2) {
            return n - 1;
        }

        long first = 0;
        long second = 1;

        for (long i = 0; i < n - 2; i++) {
            long sum = first + second;
            first = second;
            second = sum;
        }

        return second;
    }

    private static long fibo1(long n) {
        if (n <= 0) {
            return -1;
        }

        if (n <= 2) {
            return n - 1;
        }

        return fibo1(n - 1) + fibo1(n - 2);
    }

}
