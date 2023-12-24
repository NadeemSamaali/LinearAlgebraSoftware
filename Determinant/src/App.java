import java.text.DecimalFormat;
import java.util.*;
import java.text.*;
import java.io.*;

public class App {

    //Settig up the scanner
    static Scanner input = new Scanner(System.in);

    static DecimalFormat df = new DecimalFormat("0.000");
    static DecimalFormat d0 = new DecimalFormat("0.000000000");



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

    public static void setMatrixFromString(double[][] m,int mSize2, String[] ansArray2)
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
                System.out.print(Double.valueOf(df.format(h[a][b])));
                System.out.print(",");
            }
            System.out.print(Double.valueOf(df.format(h[a][o])));

            if(a==o)
            System.out.print("]");

            else
            System.out.print("];");
        }
        System.out.println("]");
    }


    /**
     * This method is an operator that serves part in the process of the reduction and cofactor
     * expansion of a square matrix in order to reduce into an upper triangular matrix.
     * 
     * The the metod will calculate the determinant
     * 
     * @param p matrix to operate
     * @param j size of the matrix
     */

    
    public static void getDeterminant(double[][] p, int j)
    {


        int amountOfK = 0;
            for(int v = 0; v<=j; v++)
                amountOfK += v;
                        double[] k = new double[amountOfK];

        for(int m = 0; m<amountOfK; m++)
            k[m] = 1.0;

        //System.out.println(amountOfK);

        int num0 = -1;

        for(int d = 0; d<=j; d++)
        {
            
            /*This loop will multiply each row R>R1 by a factor k, where k is the factor needed to tranforms
            *the entry of the first row into the entry located at [0][0] as well was multiplying the remainer
            *of the entires of this row by that same scalar k
            */

            for(int x = 0; x<j-d; x++)
            {
                if(p[x+1][0+d] != 0.0)
                {
                    num0 +=1;
                    //System.out.println(df.format(p[0+d][0+d]/p[x+d+1][0+d]));
                    k[num0] = p[0+d][0+d]/p[x+d+1][0+d];
                    //System.out.println("\nk order " + num0);

                    for(int y = 0; y<=j-d; y++)
                    {
                        p[x+d+1][y+d] = k[num0]*p[x+d+1][y+d];
                    }

                    System.out.println("\n>> Divide the row R" + (x+2+d) + " by a factor of " + 1/k[num0]);
                    printMatrix(p,j);
                }

                


            }

            /*This loop will subscract every row R>R1 by R1 so that all entries of the first row
            * under the entry [0][0] will be equal to 0 as well
            */

            for(int q = 0; q<j-d; q++)
            {
                if(p[q+d+1][0+d] != 0)
                {
                    for(int r = 0; r<=j-d; r++)
                    {
                        p[q+d+1][r+d] = Double.valueOf(d0.format(p[q+d+1][r+d] - p[0+d][r+d]));
                    }

                    System.out.println("\nR" + (q+d+2) + " - R"+ (1+q+d) +" --> R" + (q+d+2));
                    printMatrix(p,j);

                }
            }
        }

        double num1 = 1.0;

       

        //System.out.println("1/k :");
        for(int u = 0; u<amountOfK; u++)
        {
            num1 *=(1/k[u]);
            //System.out.println(1/k[u]);
        }
            

        for(int g = 0; g<=j; g++)
        {
            for(int q = 0; q<=j; q++)
            {
                if(p[g][q] != 0.0)
                {
                    //System.out.println(p[g][q]);
                    num1 = num1*p[g][q];
                    break;
                }    
            }            

        }

        System.out.println("\n>> The determinant of this matrix is : " + df.format(num1));      
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
        setMatrixFromString(matrix,n,entries);
        printMatrix(matrix, n);



        //Reduce the matrix into upper-triangular form
        getDeterminant(matrix, n);




    }

}
