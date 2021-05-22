import java.util.Scanner;

public class Main {

    public static int openExpInp(int eylerFunc) {
        Scanner scan = new Scanner(System.in);
        while (true){

            System.out.println("Введите открытую экспоненту");
            if(scan.hasNextInt()){
                int number = scan.nextInt();
                if(number>1&&NOD(number,eylerFunc)==1){
                    return  number;
                }
            }else
            {
                scan.nextLine();
            }
            System.out.println("Неверный ввод");

        }
    }
    public static int NOD(int a, int b) {

        if (a == 0 || b == 0) {
            return 0;
        }

        int biggest = Math.max(a, b);
        int smallest = Math.min(a, b);

        int remainder;
        do {

            remainder = biggest % smallest;

            if (remainder == 0) {
                return smallest;
            }

            biggest = smallest;
            smallest = remainder;

        } while (true);

    }

    static int untilPrime(String msg){
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println(msg);
            if(scan.hasNextInt()){
                int number = scan.nextInt();
                if(isPrime(number)){
                    return  number;
                }
            }else
            {

                scan.nextLine();
            }
            System.out.println("Неверный ввод");
        }
    }

    static boolean isPrime(int n) {

        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    public static int closeExp(int a, int b) {
        int d0 = a; int d1 = b;
        int x0 = 1; int x1 = 0;
        int y0 = 0; int y1 = 1;
        while(d1 > 1) {
            int q = d0 / d1;
            int d2 = d0 % d1;
            int x2 = x0 - q * x1;
            int y2 = y0 - q * y1;
            d0 = d1; d1 = d2;
            x0 = x1; x1 = x2;
            y0 = y1; y1 = y2;
        }
        return y1;
    }

    public static int pow(int base, int power, int  mod) {
        double a1 = base;
        double z1 = power;
        double x = 1;
        while(z1 != 0) {
            while(z1 % 2 == 0) {
                z1 = z1 / 2;
                a1 = ((a1 * a1) % mod);
            }
            z1 = z1 - 1;
            x = ((x * a1) % mod);
        }
        return (int)x;
    }

    public static int pow2(int a, int b, int n)
    {
        // a^b mod n - возведение a в степень b по модулю n
        int tmp = a;
        int sum = tmp;
        for (int i = 1; i < b; i++)
        {
            for (int j = 1; j < a; j++)
            {
                sum += tmp;
                if (sum >= n)
                {
                    sum -= n;
                }
            }
            tmp = sum;
        }
        return tmp;
    }

    public static int hash(char[] str, int multiply) {
        int result = 100;
        for(int i = 0; i < str.length; i++) {
            result = (int)Math.pow(result + (int)str[i] - 64, 2) % multiply;
        }
        return result;
    }

    public static void signatureCreation(String string) {

        int first = untilPrime("Введите первое простое число:");
        int second;
        do {
             second= untilPrime("Введите второе простое число:");
        }while (second==first);

        int multiply = first * second;
        int function = (first - 1) * (second - 1);

        int exp = openExpInp(function);

        int closeExp;
        do {
             closeExp = closeExp(function, exp);
            if (closeExp < 0)
                closeExp += function;
        }while ((exp*closeExp)%function!=1);

        int hash = hash(string.toCharArray(), multiply);
        int signature = pow(hash, closeExp, multiply);

        System.out.println("Секретное число:");
        System.out.println(signature);
        System.out.println("Открытый ключ:");
        System.out.println(exp + " - " + multiply);
    }

    public static void signatureVerification(String string) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите значение цифровой подписи:");
        int signature = scan.nextInt();
        System.out.println("Введите значение открытого ключа:");
        int exp = scan.nextInt();
        int multiply = scan.nextInt();

        int hash = hash(string.toCharArray(), multiply);
        if(hash != (pow(signature, exp, multiply)))
            System.out.println("Подпись недействительная.");
        else
            System.out.println("Подпись действительная.");
    }

    public static void mainMenu() {
        System.out.println("Создать цифровую подпись(1)");
        System.out.println("Проверить цифровую подпись(2)");
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        scan.nextLine();
        String str;
        switch (value) {
            case 1 -> {
                System.out.println("Введите строку для создания цифровой подписи:");
                str = scan.nextLine();
                signatureCreation(str);
            }
            case 2 -> {
                System.out.println("Введите строку для проверки цифровой подписи:");
                str = scan.nextLine();
                signatureVerification(str);
            }
            default -> System.out.println("Проверьте введенные данные.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            mainMenu();
        }
    }

}
