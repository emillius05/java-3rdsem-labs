import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размерность матрицы n (для матрицы n x n): ");
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
            System.out.println("Введите элементы матрицы (" + n + "x" + n + "):");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }
        }

        // Копируем матрицу для демонстрации классического подхода и Stream API
        int[][] matrixClassic = copyMatrix(matrix);
        int[][] matrixStream = copyMatrix(matrix);

        System.out.println("\n--- Классический подход ---");
        sortColumnsClassic(matrixClassic);
        printMatrix(matrixClassic);

        System.out.println("\n--- Подход с использованием Stream API ---");
        sortColumnsStream(matrixStream);
        printMatrix(matrixStream);
    }

    // 1. Классическая реализация сортировки столбцов по количеству одинаковых элементов
    public static void sortColumnsClassic(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;

        // Массив для хранения количества дубликатов в каждом столбце
        Integer[] colIndexes = new Integer[cols];
        int[] duplicateCounts = new int[cols];

        for (int j = 0; j < cols; j++) {
            colIndexes[j] = j;
            Map<Integer, Integer> freq = new HashMap<>();
            for (int i = 0; i < rows; i++) {
                freq.put(m[i][j], freq.getOrDefault(m[i][j], 0) + 1);
            }
            int duplicates = 0;
            for (int count : freq.values()) {
                if (count > 1) {
                    duplicates += count; // или можно считать общее число элементов, входящих в дубликаты
                }
            }
            duplicateCounts[j] = duplicates;
        }

        // Сортируем индексы столбцов по убыванию количества одинаковых элементов
        Arrays.sort(colIndexes, (c1, c2) -> Integer.compare(duplicateCounts[c2], duplicateCounts[c1]));

        // Переставляем столбцы в матрице
        int[][] temp = copyMatrix(m);
        for (int j = 0; j < cols; j++) {
            int origCol = colIndexes[j];
            for (int i = 0; i < rows; i++) {
                m[i][j] = temp[i][origCol];
            }
        }
    }

    // 2. Реализация с использованием Stream API
    public static void sortColumnsStream(int[][] m) {
        int rows = m.length;
        int cols = m[0].length;

        List<Integer> colIndexes = IntStream.range(0, cols)
                .boxed()
                .sorted(Comparator.comparingInt((Integer c) -> -countDuplicatesStream(m, c, rows)))
                .collect(Collectors.toList());

        int[][] temp = copyMatrix(m);
        for (int j = 0; j < cols; j++) {
            int origCol = colIndexes.get(j);
            for (int i = 0; i < rows; i++) {
                m[i][j] = temp[i][origCol];
            }
        }
    }

    private static int countDuplicatesStream(int[][] m, int col, int rows) {
        Map<Integer, Long> freq = IntStream.range(0, rows)
                .mapToObj(i -> m[i][col])
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        return freq.values().stream().filter(v -> v > 1).mapToInt(Long::intValue).sum();
    }

    private static void printMatrix(int[][] m) {
        for (int[] row : m) {
            for (int val : row) {
                System.out.printf("%4d", val);
            }
            System.out.println();
        }
    }

    private static int[][] copyMatrix(int[][] m) {
        int[][] copy = new int[m.length][m[0].length];
        for (int i = 0; i < m.length; i++) {
            copy[i] = m[i].clone();
        }
        return copy;
    }
}