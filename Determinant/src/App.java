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


    /**
     * This method is an operator that serves part in the process of the reduction and cofactor
     * expansion of a square matrix
     * 
     * @param p matrix to operate
     * @param j size of the matrix
     */

    public static void determinantReducer(double[][] p, int j)
    {
        double[] k = new double[j];

        /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
         *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
         *of the entires of this row by that same scalar k
         */

        for(int x = 0; x<j; x++)
        {
            if(p[x+1][0] != 0)
            {
                k[x] = p[0][0]/p[x+1][0];

                for(int y = 0; y<=j; y++)
                {
                    p[x+1][y] = k[x]*p[x+1][y];
                }

                System.out.println("\n>> Multiply the row R" + (x+2) + " by a factor of " + k[x]);
                printMatrix(p,j);
            }

        }

        /*This loop will subscract every row R>R1 by R1 so that all entries of the first row
         * under the entry [0][0] will be equal to 0 as well
         */

        for(int q = 0; q<j; q++)
        {
            if(p[q+1][0] != 0)
            {
                for(int r = 0; r<=j; r++)
                {
                    p[q+1][r] = p[q+1][r] - p[0][r];
                }

                System.out.println("\nR" + (q+2) + " - R1 --> R" + (q+2));
                printMatrix(p,j);

            }
        } 
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


        //Run 1 isntance of the determinantReducer operation
        determinantReducer(matrix, n);
        
        

    }

}
