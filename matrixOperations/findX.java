package matrixOperations;
import java.text.DecimalFormat;
import java.util.Scanner;

/***
 * This class contains all the methods necessary for finding the vector X of the system AX = B by
 * performing the matrix multiplication between the inverse of A (A^-1) with the vector B
 * 
 * @author Nadeem Samaali
 * @version 1.0.2 | Will now catch if B is out of bound with M
*/

public class findX 
{

    static DecimalFormat df = new DecimalFormat("0.000");
    static Scanner input = new Scanner(System.in);

    /**
     * This method will convert the string input into the nx1 B vector
     * 
     * @param B     solution vector
     * @param ans   string input from user
     * @param N     length of vector
     */

    public static void setB(double[] B, String ans, int N)
    {
        String[] entries = ans.split(" ");

        if(entries.length != N+1)
            throw new IllegalArgumentException("The vector B is out of bound with the coefficient matrix");

        for(int i = 0; i<=N; i++)
            B[i] = Double.valueOf(entries[i]);
    }

    /**
     * This method will perform the matrice multiplication between the inverse of the unputed matrix
     * with the B vector 
     * 
     * @param B     solution vector
     * @param M     inputted matrix
     * @param N     size of the square matrix + length of the solution vector
     */
    public static void getX(double[] B, double[][] M, int N)
    {
        double[] X = new double[N+1];
        double[][] C = new double[N+1][N+1];

        for(int a = 0; a<=N; a++)
        {
            for(int b = 0; b<=N; b++)
                C[a][b] = M[a][b];
        }
        
        double det = detFinder.getSilentDeterminant(C,N);

        if(det != 0)
        {
            //Find the inverse matrix
            inverseFinder.getCofactorMatrix(M, N);
            inverseFinder.getAdjacentMatrix(M, N);
            inverseFinder.getInverse(M,N,det);

            //Matrice multiplication
            for(int i = 0; i<=N; i++)
            {
                for(int j = 0; j<=N; j++)
                {
                    X[i] += B[j]*M[i][j];
                }
            }

            System.out.print("\n>> X - The value of the variable vector is : ");

            //Print the X vector
            System.out.print("[");
            for(int k = 0; k<N; k++)
            {
                System.out.print(df.format(X[k])+",");
            }
            System.out.print(df.format(X[N]) + "]\n");
        }

        else
        System.out.println("\n>> ERROR : The matrix is not invertible");
    }

    public static void main(String[] args)
    {
        String ans;

        System.out.println("\n>> Please insert the square matrix size (1,2,3...,n) :\n");
        ans = "";
        System.out.print("U : ");
        ans = input.nextLine();
        int n = Integer.valueOf(ans) - 1;

        double[][] m = new double[n+1][n+1];
        double[] b = new double[n+1];



        System.out.println("\n>> A - Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
        System.out.print("U : ");
        String ans1 = input.nextLine();

        mOPS.setMatrixFromString(m, n, ans1.split(","));

        System.out.println("\n>> B - Insert the values of the solution sparated with a comma\n   respecting this form : b1,b2,...,bn\n");
        System.out.print("U : ");
        String ans2 = input.nextLine();



        setB(b, ans2, n);
        getX(b, m, n);

    }
}
