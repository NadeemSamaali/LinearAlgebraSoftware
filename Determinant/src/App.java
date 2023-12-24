import java.util.*;

public class App {

    //Settig up the scanner
    static Scanner input = new Scanner(System.in);

    /**
     * 'printMatrix' is a method which allows for printing of any n sized matrix based on the entries of the user
     * 
     * @param sCounter Counter used for selecting the data entered by the user
     * @param mSize Size of the matrix to be printed
     * @param ansArray Array of the entries entred by the user
     */

    public static void printMatrix(int sCounter, int mSize, String[] ansArray)
    {
        //Outputting the matrix as well as the data entries
        System.out.print("[");
        for(int a = 0; a<=mSize; a++)
        {
            System.out.print("[");
            for(int b = 0; b<mSize; b++)
            {
                sCounter += 1;
                System.out.print(ansArray[sCounter]);
                System.out.print(",");
            }
            sCounter += 1;
            System.out.print(ansArray[sCounter]);

            if(sCounter == Math.pow(mSize+1,2)-1)
            System.out.print("]");

            else
            System.out.print("],");
        }
        System.out.println("]");
    }

    public static void main(String[] args) throws Exception {

        //Inputing the value of the size of matrix m
        System.out.println("\n>> Please insert matrix size (1,2,3...,n) :\n");
        String ans;
        ans = input.nextLine();

        //Setting the value n as the size of the matrix
        int n = Integer.valueOf(ans)-1;


        //Creating a new square matrix based on size 'n'
        double[][] m = new double[n][n];
        ans = "";

        //Enetring the entries of the matrix 
        System.out.println("\n>> Insert the values of the entries sparated with a coma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
        ans = input.nextLine();

        //Outputting the matrix as well as the data entries
        System.out.print("\n>> Here is the inputted matrix : ");
        printMatrix(-1, n, ans.split(","));
        System.out.println();
    
        /*
        int matrixSize;
        matrixSize=-1;

        //Filling the matrix with the numbers inputted
        for(int i = 0; i<=n; i++)
        {
            for(int j = 0; j<=n; j++)
            {
                matrixSize += 1;

                m[i][j] = Double.valueOf(ansEntry[matrixSize]);
                System.out.println(m[i][j]);

            }
            
        }
        */

    }

}
