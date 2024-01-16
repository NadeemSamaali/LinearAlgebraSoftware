import java.util.ArrayList;
import java.util.Scanner;
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
 *      - Calculating the cross product between two vectors (1.4.0)
 *      - Calculating the volume of a parallelepiped composed of 3 vectors (2.1.0)
 *
 * @author Nadeem Samaali
 * @version 2.1.3 | Bug fixing
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

        ArrayList<double[]> v = new ArrayList();
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
                            System.out.println("    #crossProduct       ~ This command calculates the cross product between two 3-dimensional vectors");
                            System.out.println("    #parallelepiped     ~ This command calculates the volume of a parallelepiped");                                                        
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
                            mOPS.getDeterminant(m1, n);     
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

                            double detM = mOPS.getSilentDeterminant(d,n);

                            if(detM != 0){
                                mOPS.getCofactorMatrix(m2, n);
                                mOPS.getAdjointMatrix(m2,n);
                                mOPS.getInverse(m2, n, detM);
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

                            mOPS.getCofactorMatrix(m4, n);
                            System.out.println("\n>> The cofactor matrix is : \n");
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

                            mOPS.getCofactorMatrix(m5,n);
                            mOPS.getAdjointMatrix(m5, n);
                            System.out.println("\n>> The adjoint matrix is : ");
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

                            mOPS.setB(b, ans2, n);
                            mOPS.getX(b, m3, n);
                        break;

                        case "#dotProduct":
                            v.clear();
                            for(int i = 0; i<2; i++) {
                                System.out.println("\n"+ (i+1) + ") Insert the components of V" + (i+1) + " separated by a space (v"+(i+1)+"1 v"+(i+1)+"2 ... v"+(i+1)+"n) : \n");
                                System.out.print("   ");
                                ans = input.nextLine();
                                String[] e = ans.split(" ");
                                double[] V = new double[e.length];
                                for(int j = 0; j<e.length; j++)
                                    V[j] = Double.valueOf(e[j]);
                                v.add(V);
                            }
                            if(v.get(0).length == v.get(1).length) {
                                System.out.println("\n>> V1 * V2 = " + vOPS.getDotProduct(v.get(0), v.get(1)));
                            }
                            else
                                System.out.println("\n>> ERROR : V1 and V2 aren't of the same dimension");
                        break;

                        case "#crossProduct" :
                            v.clear();
                            for(int i = 0; i<2; i++) {
                                System.out.println("\n"+ (i+1) + ") Insert the components of V" + (i+1) + " separated by a space (v"+(i+1)+"1 v"+(i+1)+"2 v"+(i+1)+"3) : \n");
                                System.out.print("   ");
                                ans = input.nextLine();
                                String[] e = ans.split(" ");
                                double[] V = new double[e.length];
                                for(int j = 0; j<e.length; j++)
                                    V[j] = Double.valueOf(e[j]);
                                v.add(V);
                            }
                            if(v.get(0).length == 3 && v.get(1).length ==3) {
                                System.out.print("\n>> V1 x V2 = ");
                                vOPS.printVector(vOPS.getCrossProduct(v.get(0), v.get(1)));
                            }  
                        break;
                   
                        case "#parallelepiped" :
                            v.clear();
                            for(int i = 0; i<3; i++) {
                                System.out.println("\n"+ (i+1) + ") Insert the components of V" + (i+1) + " separated by a space (v"+(i+1)+"1 v"+(i+1)+"2 v"+(i+1)+"3) : \n");
                                System.out.print("   ");
                                ans = input.nextLine();
                                String[] e = ans.split(" ");
                                double[] V = new double[e.length];
                                for(int j = 0; j<e.length; j++)
                                    V[j] = Double.valueOf(e[j]);
                                v.add(V);
                            }
                            System.out.println("\n>> The volume of the parallelepied is : " + vOPS.parallelepiped(v.get(0),v.get(1),v.get(2)));
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
            catch(Exception e) {
                System.err.println("\n>> ERROR : illegal prompt - " + e.getMessage());
            }
        }while(!key);
    }
}