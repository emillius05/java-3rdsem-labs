import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Task13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размерность матрицы n (для квадратной n x n): ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        System.out.print("Как заполнить матрицу? (1 - случайно от 0 до 10, 2 - вручную): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            Random random = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = random.nextInt(11); // от 0 до 10
                }
            }
            System.out.println("Сгенерированная матрица:");
            printMatrix(matrix);
        } else {
            System.out.println("Введите элементы матрицы:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
        }

        // Выполнение задачи 13: удаление столбцов, где ВСЕ элементы четные
        int[][] compactedMatrix = removeEvenColumns(matrix);

        System.out.println("\n--- Результат (после удаления столбцов с четными числами) ---");
        if (compactedMatrix.length == 0 || compactedMatrix[0].length == 0) {
            System.out.println("Все столбцы были удалены (содержали только четные числа).");
        } else {
            printMatrix(compactedMatrix);
        }
    }

    // Метод для проверки столбца и удаления подходящих
    public static int[][] removeEvenColumns(int[][] matrix) {
        if (matrix.length == 0) return matrix;
        int rows = matrix.length;
        int cols = matrix[0].length;

        List<Integer> validColumns = new ArrayList<>();

        for (int j = 0; j < cols; j++) {
            boolean allEven = true;
            for (int i = 0; i < rows; i++) {
                if (matrix[i][j] % 2 != 0) { // Если нашли хоть одно нечетное
                    allEven = false;
                    break;
                }
            }
            // Если столбец не заполнен полностью четными числами, оставляем его
            if (!allEven) {
                validColumns.add(j);
            }
        }

        // Создаем новую сжатую матрицу
        int newCols = validColumns.size();
        int[][] result = new int[rows][newCols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < newCols; j++) {
                result[i][j] = matrix[i][validColumns.get(j)];
            }
        }

        return result;
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