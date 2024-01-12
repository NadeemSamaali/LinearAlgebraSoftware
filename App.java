import java.util.Scanner;
import matrixOperations.*;
import vectorOperations.dotProduct;
import vectorOperations.vOPS;


/**
 * LinearSpace is a software dealing with various matrix and vector operations
 * 
 * Currently supported operations :
 *      - Calculating the determinant of a square matrix (1.0.0)
 *      - Calculating the inverse of a square matrix (1.1.0)
 *      - Calculating the solution of a linar system in matrix form (1.2.0)
 *      - Finding the Adjoint and Cofactor matrix (1.3.3)
 * 
 *      - Calculating the dot product of two vectors of n dimension (1.3.0)
 *
 * @author Nadeem Samaali
 * @version 1.3.5 - Bug fixing
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
                            System.out.println("    #getDeterminant     ~ This command calculates the determinant of any nxn matrix");
                            System.out.println("    #getCofactor        ~ This command finds the cofactor matrix of a square matrix");
                            System.out.println("    #getAdjoint         ~ This command finds the adjoint matrix of a square matrix");
                            System.out.println("    #getInverse         ~ This command finds the inverse of a square matrix if invertible");
                            System.out.println("    #findX              ~ This command finds the solution of a linear system in matrix form");
                            System.out.println("\n    #dotProduct         ~ This command calculates the scalar dot product between two n-dimensional vectors");
                            System.out.println("\n    #exit               ~ Close the program");

                        break;

                        case "#getDeterminant":
                        //Inputing the value of the size of matrix m
                            System.out.println("\n1) Please insert the square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("   N = ");
                            ans = input.nextLine();

                            //Setting the value n as the size of the matrix
                            n = Integer.valueOf(ans)-1;

                            double[][] m1 = new double[n+1][n+1];

                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            mOPS.setMatrix(m1,n,input);
                            
                            //Outputting the matrix
                            System.out.print("\n-  Here is the inputted matrix : ");
                            //mOPS.printEntries(n,entries);
                            System.out.println();
                            //Set the entires into the designated placements in the matrix Array
                            mOPS.printMatrix(m1, n);
                            //Reduce the matrix into upper-triangular form and calculating the determinant
                            detFinder.getDeterminant(m1, n);
                            
                                
                        break;

                        case "#getInverse" :
                            System.out.println("\n1) Please enter the square matrix size (1,2,...,n) : \n");
                            System.out.print("   N = ");
                            Scanner in = new Scanner(System.in);
                            ans = in.nextLine();

                            n = Integer.valueOf(ans)-1;

                            double[][] m2 = new double[n+1][n+1];
                            double[][] d = new double[n+1][n+1];

                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            
                            mOPS.setMatrix(m2,n,input);

                            //Setting up the mock matrix 
                            for(int x = 0; x<=n; x++) {
                                for(int y = 0; y<=n; y++) {
                                    d[x][y] = m2[x][y];
                                }
                            }

                       
                            double detM = detFinder.getSilentDeterminant(d,n);

                            if(detM != 0){
                                inverseFinder.getCofactorMatrix(m2, n);
                                inverseFinder.getAdjacentMatrix(m2,n);
                                inverseFinder.getInverse(m2, n, detM);
                                System.out.println("\n>> The inverse of the inputted matrix is : \n");
                                mOPS.printMatrix(m2,n);
                            }
                            
                            else
                            {
                                System.out.println(">>\nERROR : This matrix is not invertible !");
                            }
                        break;
                            
                        case "#getCofactor":
                            //Inputing the value of the size of matrix m
                            System.out.println("\n1) Please insert the square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("   N = ");
                            ans = input.nextLine();

                            //Setting the value n as the size of the matrix
                            n = Integer.valueOf(ans)-1;

                            double[][] m4 = new double[n+1][n+1];

                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            mOPS.setMatrix(m4,n,input);

                            inverseFinder.getCofactorMatrix(m4, n);
                            System.out.print("\n>> The cofactor matrix is : ");
                            mOPS.printMatrix(m4,n);
                        break;

                        case "#getAdjoint":
                            //Inputing the value of the size of matrix m
                            System.out.println("\n1) Please insert the square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("   N = ");
                            ans = input.nextLine();

                            //Setting the value n as the size of the matrix
                            n = Integer.valueOf(ans)-1;

                            double[][] m5 = new double[n+1][n+1];

                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            mOPS.setMatrix(m5,n,input);

                            inverseFinder.getCofactorMatrix(m5,n);
                            inverseFinder.getAdjacentMatrix(m5, n);
                            System.out.print("\n>> The adjoint matrix is : ");
                            mOPS.printMatrix(m5,n);
                        break;

                        case "#findX" :
                            System.out.println("\n1) A - Please insert the square coefficient matrix size (1,2,3...,n) :\n");
                            ans = "";

                            System.out.print("   N = ");
                            ans = input.nextLine();
                            n = Integer.valueOf(ans) - 1;
                    
                            double[][] m3 = new double[n+1][n+1];
                            double[] b = new double[n+1];
                    
                    
                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            mOPS.setMatrix(m3,n,input);

                            System.out.println("\n3) B - Insert the values of the solution sparated with a space\n   respecting this form : b1 b2 ... bn\n");
                            System.out.print("   ");
                            String ans2 = input.nextLine();

                            findX.setB(b, ans2, n);
                            findX.getX(b, m3, n);
                        break;

                        case "#dotProduct":

                            System.out.println("\n1) Insert the components of V1 separated by a space (v11 v12 ... v1n)\n");
                            System.out.print("   ");
                            ans = input.nextLine();
                            String[] E1 = ans.split(" ");
                            double[] V1 = new double[E1.length];
                            vOPS.setVector(V1, E1);

                            System.out.println("\n2) Insert the components of V2 separated by a space (v21 v22 ... v2n)\n");
                            System.out.print("   ");                            
                            ans = input.nextLine();
                            String[] E2 = ans.split(" ");
                            double[] V2 = new double[E2.length];
                            vOPS.setVector(V2, E2);

                            if(V1.length == V2.length)
                            {
                                System.out.println("\n>> V1 * V2 = " + dotProduct.getDotProduct(V1, V2));
                            }

                            else
                                System.out.println("\n>> ERROR : V1 and V2 aren't of the same dimension");

                        break;

                        case "#exit":
                            System.out.println("\n ~ Thank you for using LinearSpace ~\n");
                            key = true;
                            input.close();
                        break label;

                        default:
                            System.out.println("\n>> Please enter a valid command prompt, type '#help' for command list");
                        break;
                   } 
                }
            }

            catch(Exception e)
            {
                System.err.println("\n>> ERROR : illegal prompt - " + e.getMessage());
            }

        }while(!key);

        
    }


}