import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Task24 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размерность матрицы n (для квадратной n x n): ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        System.out.print("Как заполнить матрицу? (1 - случайно от 0 до 50, 2 - вручную): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = random.nextInt(51);
                }
            }
        } else {
            System.out.println("Введите элементы матрицы:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
        }

        System.out.println("\nИсходная матрица:");
        printMatrix(matrix);

        // Решение задачи 24
        diagonalSortByMax(matrix);

        System.out.println("\nМатрица после перестановки (элементы по убыванию на главной диагонали):");
        printMatrix(matrix);
    }

    public static void diagonalSortByMax(int[][] matrix) {
        int n = matrix.length;
        int totalElements = n * n;
        int[] flat = new int[totalElements];

        // 1. Собираем все элементы в одномерный массив
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                flat[idx++] = matrix[i][j];
            }
        }

        // 2. Сортируем по возрастанию
        Arrays.sort(flat);

        // 3. Заполняем главную диагональ по убыванию (начиная с максимальных)
        int flatIdx = totalElements - 1; // берем с конца (самые большие)

        for (int i = 0; i < n; i++) {
            matrix[i][i] = flat[flatIdx--];
        }

        // Заполняем недиагональные элементы оставшимися значениями
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    matrix[i][j] = flat[flatIdx--];
                }
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }
}