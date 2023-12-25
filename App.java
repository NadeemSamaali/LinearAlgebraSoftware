import java.util.Scanner;
import determinantOperations.determinantFinder;

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

                    System.out.print("\nCMD : ");
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
                        break label;
                   }
                   
                }
            }

            catch(Exception e)
            {

            }

            key = true;

        }while(!key);

        
    }


}
