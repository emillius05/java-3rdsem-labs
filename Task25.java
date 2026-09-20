import java.util.Scanner;

/*
 * Найти и вывести из заданной последовательности натуральных чисел, все р-значные автоморфные числа.
 * Автоморфным называется число, совпадающее с младшими цифрами своего квадрата, например, 5^2=25, 6^2=36, 25^2=625.
 */
public class Task25 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел в последовательности: ");
        int n = scanner.nextInt();

        System.out.print("Введите количество знаков p (разрядность числа): ");
        int p = scanner.nextInt();

        System.out.println("Введите числа:");

        int count = 0;
        for (int i = 0; i < n; i++) {
            long number = scanner.nextLong();
            long absNum = Math.abs(number);

            // Проверяем, является ли число p-значным и автоморфным
            if (String.valueOf(absNum).length() == p) {
                if (isAutomorphic(absNum)) {
                    System.out.println("Автоморфное число: " + number);
                    count++;
                }
            }
        }

        System.out.println("Всего найдено автоморфных p-значных чисел: " + count);
        scanner.close();
    }

    // Вспомогательный метод для проверки, является ли число автоморфным
    private static boolean isAutomorphic(long num) {
        long square = num * num;

        // Находим 10 в степени количества цифр исходного числа
        long mod = 1;
        long temp = num;
        while (temp > 0) {
            mod *= 10;
            temp /= 10;
        }

        // Сравниваем младшие разряды квадрата с исходным числом
        return (square % mod) == num;
    }
}