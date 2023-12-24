import java.util.*;

public class App {

    //Settig up the scanner
    static Scanner input = new Scanner(System.in);

    /**
     * This method allows for printing of any n sized 
     * square matrix based on the entries of the user
     * 
     * @param mSize1 Size of the matrix to be printed
     * @param ansArray Array of the entries entred by the user
     */

    public static void printMatrix(int mSize1, String[] ansArray1)
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
            System.out.print("],");
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

    public static void setMatrix(int mSize2, String[] ansArray2)
    {

        int sCounter2 = -1;

        double[][] m = new double[mSize2+1][mSize2+1];

        for(int i = 0; i<=mSize2; i++)
        {
            for(int j = 0; j<=mSize2; j++)
            {
                sCounter2 += 1;
                m[i][j] = Double.valueOf(ansArray2[sCounter2]);
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
        String[] e = ans.split(",");

        //Outputting the matrix
        System.out.print("\n>> Here is the inputted matrix : ");
        printMatrix(n,e);
        System.out.println();

        //Set the entires into the designated placements in the matrix Array
        setMatrix(n,e);


    }

}
