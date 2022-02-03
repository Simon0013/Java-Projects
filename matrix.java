import java.util.Scanner;
import java.util.Random;
public class Main {
   static Scanner sc = new Scanner(System.in);
   static Random random = new Random();
   public static void main(String[] args) {
       int[][] matrix; int n;
       System.out.println("Введите количество строк и столбцов");
       n = sc.nextInt();
       matrix = createMatrix(n);
       calculateVector(matrix, n);
   }
   static int[][] createMatrix(int size) {
        int[][] matrix = new int[size][size];
        for(int i=0; i<size; i++){
           for(int j=0; j<size; j++){
               matrix[i][j] = random.nextInt(15) - 5;
               System.out.print(matrix[i][j] + " ");
           }
           System.out.print("\n");
        }
        return matrix;
   }
   static void calculateVector(int[][] matrix, int size) {
        double[][] vector = new double[size][1];
        System.out.println("Введите число, на которое будут разделены элементы побочной диагонали(k>0)");
        int k = sc.nextInt();
        int j = size - 1;
        if(k == 0){
            System.out.print("В данной ситуации вы были не правы: на ноль ничего не делится");
        } else {
            for(int i = 0; i<size; i++) {
                vector[i][0] = (double)matrix[i][j]/k;
                j--;
            }
        }
       System.out.print("Ответ:\n");
       for(int i=0; i<size; i++) {
           System.out.print(vector[i][0] + "\n");
       }
    }
}