import java.util.Scanner;
import matrixOperations.*;


/**
 * LinearSpace is a software dealing with various matrix and vector operations
 * 
 * Currently supported operations :
 *      - Calculating the determinant of a square matrix (1.0.0)
 *      - Calculating the inverse of a square matrix (1.1.0)
 *      - Calculating the solution of a linar system in matrix form (1.2.0)
 *
 * @author Nadeem Samaali
 * @version 1.2.0 - Added a linear system solver
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

        String[] entries;
        int n;

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
                            System.out.println("\nLinearSpace is a software which deals with a multitude\nof different matrix operations\n\nHere is a list of all the supported commands : \n");
                            System.out.println("#getDeterminant    ~ This command calculates the determinant of any nxn matrix");
                            System.out.println("#getInverse        ~ This command finds the inverse of a square matrix if invertible");
                            System.out.println("#findX             ~ This command finds the solution of a linear system in matrix form");
                            System.out.println("\n#exit              ~ Close the program");

                        break;

                        case "#getDeterminant":
                        //Inputing the value of the size of matrix m
                            System.out.println("\n1) Please insert the square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("U : ");
                            ans = input.nextLine();

                            //Setting the value n as the size of the matrix
                            n = Integer.valueOf(ans)-1;
                            //ans = "";

                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n2) Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn");
                            System.out.print("\nU : ");
                            ans = input.nextLine();
                            entries = ans.split(",");

                            //Outputting the matrix
                            System.out.print("\n-  Here is the inputted matrix : ");
                            mOPS.printEntries(n,entries);
                            System.out.println();

                            double[][] m1 = new double[n+1][n+1];

                            //Set the entires into the designated placements in the matrix Array
                            mOPS.setMatrixFromString(m1,n,entries);
                            mOPS.printMatrix(m1, n);

                            //Reduce the matrix into upper-triangular form
                            detFinder.getDeterminant(m1, n);
                        break;

                        case "#getInverse" :
                            System.out.println("\n1) Please enter the square matrix size (1,2,...,n) : \n");
                            System.out.print("U : ");
                            Scanner in = new Scanner(System.in);
                            ans = in.nextLine();

                            n = Integer.valueOf(ans)-1;

                            double[][] m2 = new double[n+1][n+1];
                            double[][] d = new double[n+1][n+1];

                            System.out.println("\n2) Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
                            System.out.print("U : ");
                            ans = in.nextLine();
                            entries = ans.split(",");

                            mOPS.setMatrixFromString(m2, n, entries);
                            mOPS.setMatrixFromString(d, n, entries);
                            double detM = detFinder.getSilentDeterminant(d,n);

                            if(detM != 0)
                            {
                                inverseFinder.getCofactorMatrix(m2, n);
                                inverseFinder.getAdjacentMatrix(m2,n);
                                inverseFinder.getInverse(m2, n, detM);
                                System.out.print("\nThe inverse of the inputted matrix is : ");
                                mOPS.printMatrix(m2,n);
                            }
                            
                        else
                        {
                            System.out.println("\nERROR : This matrix is not invertible !");
                        }
                        break;

                        case "#findX" :
                            System.out.println("\n1) A - Please insert the square coefficient matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("U : ");
                            ans = input.nextLine();
                            n = Integer.valueOf(ans) - 1;
                    
                            double[][] m3 = new double[n+1][n+1];
                            double[] b = new double[n+1];
                    
                    
                            System.out.println("\n2) A - Insert the values of the entries sparated with a comma\n   respecting this form : a11,...,a1n,...,am1,...,amn\n");
                            System.out.print("U : ");
                            String ans1 = input.nextLine();

                            mOPS.setMatrixFromString(m3, n, ans1.split(","));

                            System.out.println("\n3) B - Insert the values of the solution sparated with a comma\n   respecting this form : b1,b2,...,bn\n");
                            System.out.print("U : ");
                            String ans2 = input.nextLine();



                            findX.setB(b, ans2, n);
                            findX.getX(b, m3, n);
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
                System.out.println("\n>> ERROR : illegal prompt - Please start again");
            }

        }while(!key);

        
    }


}