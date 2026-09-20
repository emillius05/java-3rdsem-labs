import java.util.Scanner;

/*
 * Найти все р-значные числа из заданной последовательности натуральных чисел,
 * в записи которых встречаются не более (k < p) различных цифр, и подсчитать их количество.
 */
public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел в последовательности: ");
        int n = scanner.nextInt();

        System.out.print("Введите количество знаков p: ");
        int p = scanner.nextInt();

        System.out.print("Введите максимальное количество различных цифр k (k < p): ");
        int k = scanner.nextInt();

        int count = 0;
        System.out.println("Введите числа:");

        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();

            // Проверяем, является ли число p-значным
            if (String.valueOf(Math.abs(number)).length() == p) {
                if (hasFewUniqueDigits(Math.abs(number), k)) {
                    System.out.println("Подходящее число: " + number);
                    count++;
                }
            }
        }

        System.out.println("Количество таких чисел: " + count);
        scanner.close();
    }

    // Вспомогательный метод для подсчета уникальных цифр
    private static boolean hasFewUniqueDigits(int num, int maxUnique) {
        boolean[] digits = new boolean[10];
        int uniqueCount = 0;

        while (num > 0) {
            int digit = num % 10;
            if (!digits[digit]) {
                digits[digit] = true;
                uniqueCount++;
            }
            num /= 10;
        }

        return uniqueCount <= maxUnique;
    }
}