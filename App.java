import java.util.Scanner;
import matrixOperations.determinantFinder;

/**
 * LinearSpace is a software dealing with various matrix and vector operations
 * 
 * Currently supported operations :
 *      - Calculatling the determinant of a square matrix (1.0.0)
 *
 * @author Nadeem Samaali
 * @version 1.0.0 - First stable release
 */


public class App {

    static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        boolean key = false;

        //Welcome message
        System.out.println("\n============================");
        System.out.println("   Welcome to LinearSpace\n   ~ by Nadeem Samaali ~"); 
        System.out.println("============================\n\n>> Enter '#help' to get started.");

        do
        {
            label:

            try
            {
                while(true)
                {
                    System.out.print("\nU : ");
                    //Setting ans to be the inputed value by the user
                    String ans = input.nextLine();

                    switch(ans)
                    {
                        case "#help":
                            System.out.println("\n=== LinearSpace help center ===");
                            System.out.println("\nLinearSpace is a software which deals with a multitude\nof different matrix operations\n\nHere is a list of all the supported commands : ");
                            System.out.println("\n#findDeterminant ~ This command calculates the determinant of any nxn matrix");
                        break;

                        case "#findDeterminant":
                        //Inputing the value of the size of matrix m
                            System.out.println("\n>> Please insert square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("U : ");
                            ans = input.nextLine();

                            //Setting the value n as the size of the matrix
                            int n = Integer.valueOf(ans)-1;
                            //ans = "";

                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n>> Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn");
                            System.out.print("\nU : ");
                            ans = input.nextLine();
                            String[] entries = ans.split(",");

                            //Outputting the matrix
                            System.out.print("\n>> Here is the inputted matrix : ");
                            determinantFinder.printEntries(n,entries);
                            System.out.println();

                            double[][] matrix = new double[n+1][n+1];

                            //Set the entires into the designated placements in the matrix Array
                            determinantFinder.setMatrixFromString(matrix,n,entries);
                            determinantFinder.printMatrix(matrix, n);

                            //Reduce the matrix into upper-triangular form
                            determinantFinder.getDeterminant(matrix, n);
                        break;

                        case "#exit":
                        System.out.println("\n ~ Thank you for using LinearSpace ~\n");
                        key = false;
                        break label;

                        default:
                        System.out.println("\n>> Please enter a valid command prompt, type '#help' for command list");
                        break;
                   } 
                }
            }

            catch(Exception e)
            {
                System.out.println("\n>> ERROR : illegal prompt - Please try again");
            }

        }while(!key);

        
    }


}
