import java.util.*;

public class App {

    //Settig up the scanner
    static Scanner input = new Scanner(System.in);

    /**
     * This method allows for printing the entries of the user
     * 
     * @param mSize1 Size of the matrix to be printed
     * @param ansArray Array of the entries entred by the user
     */

    public static void printEntries(int mSize1, String[] ansArray1)
    {
        int sCounter1 = -1;

        //Outputting the matrix as well as the data entries
        System.out.print("[");
        for(int a = 0; a<=mSize1; a++)
        {
            System.out.print("[");
            for(int b = 0; b<mSize1; b++)
            {
                sCounter1 += 1;
                System.out.print(ansArray1[sCounter1]);
                System.out.print(",");
            }
            sCounter1 += 1;
            System.out.print(ansArray1[sCounter1]);

            if(sCounter1 == Math.pow(mSize1+1,2)-1)
            System.out.print("]");

            else
            System.out.print("];");
        }
        System.out.println("]");
    }

    /***
     * This method will input the values of the entires in their designated
     * location withing the matrix array
     * 
     * @param mSize2 Size of the matrix array
     * @param ansArray2 Array of the entires of the user
     */

    public static void setMatrix(double[][] m,int mSize2, String[] ansArray2)
    {

        int sCounter2 = -1;

        for(int i = 0; i<=mSize2; i++)
        {
            for(int j = 0; j<=mSize2; j++)
            {
                sCounter2 += 1;
                m[i][j] = Double.valueOf(ansArray2[sCounter2]);
            }
            
        }
    }

    /**
     * This method allows for the printing of any nXn matrix | To use for testing purposes
     * 
     * @param h matrix to print
     * @param o size of the metrix
     */

    public static void printMatrix(double[][] h, int o)
    {

        //Outputting the matrix as well as the data entries
        System.out.print("[");
        for(int a = 0; a<=o; a++)
        {
            System.out.print("[");
            for(int b = 0; b<o; b++)
            {
                System.out.print(h[a][b]);
                System.out.print(",");
            }
            System.out.print(h[a][o]);

            if(a==o)
            System.out.print("]");

            else
            System.out.print("];");
        }
        System.out.println("]");
    }




    public static void main(String[] args) throws Exception {

        //Inputing the value of the size of matrix m
        System.out.println("\n>> Please insert square matrix size (1,2,3...,n) :\n");
        String ans;
        ans = input.nextLine();

        //Setting the value n as the size of the matrix
        int n = Integer.valueOf(ans)-1;
        ans = "";

        //Adding the entries of the matrix into an array by splitting
        System.out.println("\n>> Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
        ans = input.nextLine();
        String[] entries = ans.split(",");

        //Outputting the matrix
        System.out.print("\n>> Here is the inputted matrix : ");
        printEntries(n,entries);
        System.out.println();

        double[][] matrix = new double[n+1][n+1];


        //Set the entires into the designated placements in the matrix Array
        setMatrix(matrix,n,entries);

        printMatrix(matrix, n);

        double k;

        for(int x = 0; x<n; x++)
        {
            if(matrix[0][1+x] != 0)
            {
                k = matrix[0][0]/matrix[0][1+x];

                for(int y = 0; y<=n; y++)
                {
                    matrix[x+1][y] = k*matrix[x+1][y];
                }

                for(int z = 0; z<=n; z++)
                {
                    matrix[x+1][z] = matrix[x+1][z]-matrix[0][z];
                }
            }

        }

        printMatrix(matrix,n);

    }

}
