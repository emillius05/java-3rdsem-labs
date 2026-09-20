import java.util.Scanner;

/*
 * Для каждого числа из заданной последовательности натуральных чисел,
 * найти произведение цифр больших 7.
 */
public class Task13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество чисел в последовательности: ");
        int n = scanner.nextInt();

        System.out.println("Введите числа:");

        for (int i = 0; i < n; i++) {
            int number = scanner.nextInt();
            long product = getProductOfDigitsGreaterThanSeven(Math.abs(number));

            System.out.println("Число: " + number + " -> произведение цифр > 7: " + product);
        }

        scanner.close();
    }

    // Вспомогательный метод для подсчета произведения цифр > 7 (это 8 и 9)
    private static long getProductOfDigitsGreaterThanSeven(int num) {
        long product = 1;
        boolean found = false;

        if (num == 0) {
            return 0;
        }

        while (num > 0) {
            int digit = num % 10;
            if (digit > 7) {
                product *= digit;
                found = true;
            }
            num /= 10;
        }

        // Если цифр больше 7 не нашлось, возвращаем 0
        return found ? product : 0;
    }
}