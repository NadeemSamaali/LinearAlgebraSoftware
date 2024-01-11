package matrixOperations;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * This class contains all the most recurring methods when it comes to working with matrices
 * within the App file (converting the user entires into a matrix, printing entries, printing a matrix)
 * 
 * @author Nadeem Samaali
 * @version 1.2.0 | Rewrote the matrix printing method to print matrix with aligned rows
 */

public class mOPS {

    static DecimalFormat df = new DecimalFormat("0.000");
    static DecimalFormat d0 = new DecimalFormat("0.000000000");

    /**
     * This method allows for printing the entries of the user
     * 
     * @param N Size of the matrix to be printed
     * @param ansArray Array of the entries entred by the user
     */


     public static void printEntries(int N, String[] ansArray1)
     {
         int sCounter1 = -1;
 
         //Outputting the matrix as well as the data entries
         System.out.print("[");
         for(int a = 0; a<=N; a++)
         {
             System.out.print("[");
             for(int b = 0; b<N; b++)
             {
                 sCounter1 += 1;
                 System.out.print(ansArray1[sCounter1]);
                 System.out.print(",");
             }
             sCounter1 += 1;
             System.out.print(ansArray1[sCounter1]);
 
             if(sCounter1 == Math.pow(N+1,2)-1)
             System.out.print("]");
 
             else
             System.out.print("];");
         }
         System.out.println("]");
     }

    /**
     * This method inquires the user on the entries to add into each row of the N sized
     * 
     * @param M Matrix
     * @param N Matrix size
     * @param in Scanner
     */
    public static void setMatrix(double[][] M, int N, Scanner in) {
        
        String ans = "";
        String[][] E = new String[N+1][N+1];

        for(int i = 0; i<=N; i++) {
            System.out.print("   ");
            ans = in.nextLine(); 
                E[i] = ans.split(" ");           
                    if(E[i].length == M[i].length) {
                        for(int j = 0; j<=N; j++) {
                            M[i][j] = Double.valueOf(E[i][j]);
                        }
                    }
                    else {
                        throw new IllegalArgumentException("Make sure the rows are in bound with the matrix size");
                    }
            }
    } 
 
    
    /***
     * This method will input the values of the entires in their designated
     * location withing the matrix array
     * 
     * @param N Size of the matrix array
     * @param ansArray2 Array of the entires of the user
     */
    public static void setMatrixFromString(double[][] M,int N, String[] ansArray2)
    {
        int sCounter2 = -1;

        for(int i = 0; i<=N; i++)
        {
            for(int j = 0; j<=N; j++)
            {
                sCounter2 += 1;
                M[i][j] = Double.valueOf(ansArray2[sCounter2]);
            }
        }
    }

    /**
     * This method allows for the printing of any nXn matrix | To use for testing purposes
     * 
     * @param M matrix to print
     * @param N size of the metrix
     */

    public static void printMatrix(double[][] M, int N) {
        char[] E;
        int k = 0;

        double[] maxDouble = new double[N+1];
        double[] maxLength = new double[N+1];


        //Counts the amount of characters of the longest double of a column
        for(int i = 0; i<=N; i++) {
            maxDouble[i] = M[0][i];
            maxLength[i] = Double.toString(maxDouble[i]).length();

                for (int y = 0; y <=N; y++) {
                    double currentDouble = M[y][i];
                    double currentLength = Double.toString(currentDouble).length();

                    if (currentLength > maxLength[i]) {
                        maxDouble[i] = currentDouble;
                        maxLength[i] = currentLength;
                    }
                }

        }

        //Prints the matrix alligned with the amounts of spaces necessary to have evenly spaced columns
        for(int s = 0; s<=N; s++) {
            for(int t = 0; t<=N; t++) {
                E = String.valueOf(M[s][t]).toCharArray();
                System.out.print(M[s][t]);
                    for( int a = 0; a<=maxLength[t] - E.length; a++) {
                        System.out.print(" ");
                    }

                for(int b = 0; b<E.length; b++)
                    E[b] = ' ';
            }

            System.out.println();
        }
        
    } 
    /*
    public static void printMatrix(double[][] M, int N)
    {
        //Outputting the matrix as well as the data entries
        System.out.print("[");
        for(int a = 0; a<=N; a++)
        {
            System.out.print("[");
            for(int b = 0; b<N; b++)
            {
                System.out.print(Double.valueOf(df.format(M[a][b])));
                System.out.print(",");
            }
            System.out.print(Double.valueOf(df.format(M[a][N])));

            if(a==N)
            System.out.print("]");

            else
            System.out.print("];");
        }
        System.out.println("]");
    }
    */

    public static void main(String[] args)
    {
        final int N = 2;
        double[][] M = new double[N+1][N+1];
        Scanner in = new Scanner(System.in);


        System.out.println("Insert the values of the matrix : ");
        setMatrix(M, N, in);
        printMatrix(M, N);
    }


}
