import java.text.DecimalFormat;
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
 *      - Performing matrix multiplication (2.4.0)
 * 
 *      - Calculating the dot product of two vectors of n dimension (1.3.0)
 *      - Calculating the cross product between two vectors (1.4.0)
 *      - Calculating the volume of a parallelepiped composed of 3 vectors (2.1.0)
 *      - Calculating the area of shapes entrapped between two vectors (2.2.0)
 *      - Calculating the orthogonal projection of a vector onto another (2.3.0)
 *
 * @author Nadeem Samaali
 * @version 2.4.3 | Bug fixing + Method reformat implementations
 */
public class App {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args)
    {
        boolean key = false;

        //Welcome message
        System.out.println("\n============================");
        System.out.println("   Welcome to LinearSpace\n   ~ by Nadeem Samaali ~"); 
        System.out.println("============================\n\n>> Enter '/help' to get started.");

        ArrayList<double[]> v = new ArrayList();
        ArrayList<double[][]> m = new ArrayList();
        ArrayList<String[]> E = new ArrayList();
        DecimalFormat d0 = new DecimalFormat("0.000000000");

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
                        case "/help":
                            System.out.println("\n=== LinearSpace help center ===");
                            System.out.println("\nLinearSpace is a software which deals with a multitude\nof different matrix operations\n\nHere is a list of all the supported commands : \n");
                            System.out.println("    /mMultiply          Performs matrix multiplication between two matrices");
                            System.out.println("    /getDeterminant     Calculates the determinant of any nxn matrix");
                            System.out.println("    /getCofactor        Finds the cofactor matrix of a square matrix");
                            System.out.println("    /getAdjoint         Finds the adjoint matrix of a square matrix");
                            System.out.println("    /getInverse         Finds the inverse of a square matrix if invertible");
                            System.out.println("    /findX              Finds the solution of a linear system in matrix form");
                            System.out.println("\n    /dotProduct         Calculates the scalar dot product between two n-dimensional vectors");
                            System.out.println("    /crossProduct       Calculates the cross product between two 3-dimensional vectors");
                            System.out.println("    /parallelepiped     Calculates the volume of a parallelepiped");
                            System.out.println("    /orthoProj          Finds the orthogonal projection of v1 on v2");                                                                                                                
                            System.out.println("    /getArea            Calculates the area of shapes entrapped between two vectors");                                                                                                                
                            System.out.println("\n    /exit             Closes the program");
                        break;

                        case "/mMultiply" :
                            m.clear();
                            E.clear();
                            //Inserting the size of the matrices
                            for(int i = 0; i<2; i++) {
                                System.out.println("\n>> Insert the dimension of matrix M"+(i+1)+" seperated by a space (e.g for a 2x3 matrix, insert '2 3')\n");
                                System.out.print("   ");                                
                                ans = input.nextLine();
                                E.add(ans.split(" "));
                                if(E.get(i).length != 2)
                                    throw new IllegalArgumentException("The matrices must be 2-dimensional");
                                //m.add(new double[Integer.valueOf(E.get(i)[0])][Integer.valueOf(E.get(i)[1])]);
                            }
                            //Checking if the matrices are multiplyable by their size
                            if(Integer.valueOf(E.get(0)[1]) != Integer.valueOf(E.get(1)[0])) {
                                throw new IllegalArgumentException("The width of M1 must correspond with the height of M2");
                            }
                            //Inserting the entries of the matrix
                            for(int j = 0; j<2; j++) {
                                System.out.println("\n"+(j+1)+") M"+(j+1)+"_" + E.get(j)[0] +"x"+ E.get(j)[1] + " Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                                m.add(mOPS.setMatrix(Integer.valueOf(E.get(j)[0]),Integer.valueOf(E.get(j)[1])));
                            }
                            System.out.println("\n>> The matrix product of M1 and M2 is : \n");
                            mOPS.printMatrix(mOPS.mMultiply(m.get(0), m.get(1)));
                        break;

                        case "/getDeterminant":
                            m.clear();
                            System.out.println("\n1) Insert the square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("   N = ");
                            ans = input.nextLine();
                            //Setting the value n as the size of the matrix
                            n = Integer.valueOf(ans);
                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            m.add(mOPS.setMatrix(n,n));
                            //Outputting the matrix
                            System.out.print("\n>> Here is the inputted matrix : ");
                            System.out.println();
                            mOPS.printMatrix(m.get(0));
                            mOPS.getDeterminant(m.get(0), n-1);     
                        break;

                        case "/getInverse" :
                            m.clear();
                            System.out.println("\n1) Insert the square matrix size (1,2,...,n) : \n");
                            System.out.print("   N = ");
                            ans = input.nextLine();

                            n = Integer.valueOf(ans);

                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            m.add(mOPS.setMatrix(n,n));
                            
                            System.out.println("\n>> The inverse of the inputted matrix is : \n");
                            mOPS.printMatrix(mOPS.getInverse(m.get(0)));
                        break;
                            
                        case "/getCofactor":
                            m.clear();
                            System.out.println("\n1) Insert the square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("   N = ");
                            ans = input.nextLine();
                            //Setting the value n as the size of the matrix
                            n = Integer.valueOf(ans);
                            //m.add(new double[n][n]);
                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            m.add(mOPS.setMatrix(n,n));

                            System.out.println("\n>> The cofactor matrix is : \n");
                            mOPS.printMatrix(mOPS.getCofactorMatrix(m.get(0),n));
                        break;

                        case "/getAdjoint":
                            m.clear();
                            System.out.println("\n1) Insert the square matrix size (1,2,3...,n) :\n");
                            ans = "";
                            System.out.print("   N = ");
                            ans = input.nextLine();

                            //Setting the value n as the size of the matrix
                            n = Integer.valueOf(ans);
                            //m.add(new double[n][n]);
                            //Adding the entries of the matrix into an array by splitting
                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            m.add(mOPS.setMatrix(n,n));

                            System.out.println("\n>> The adjoint matrix is : ");
                            mOPS.printMatrix(mOPS.getAdjointMatrix(m.get(0)));
                        break;

                        case "/findX" :
                            m.clear();
                            v.clear();
                            System.out.println("\n1) A - Please insert the square coefficient matrix size (1,2,3...,n) :\n");
                            ans = "";

                            System.out.print("   N = ");
                            ans = input.nextLine();
                            n = Integer.valueOf(ans);

                            //m.add(new double[n][n]);
                            v.add(new double[n]);

                            System.out.println("\n2) Insert the values of the entries by row, with each value seperated by a space\n   respecting this form :\n\n   a11 a12 ... a1n\n   a21 a22 ... a2n\n   am1 am2 ... amn\n");
                            m.add(mOPS.setMatrix(n,n));

                            System.out.println("\n3) B - Insert the values of the solution sparated with a space\n   respecting this form : b1 b2 ... bn\n");
                            System.out.print("   ");
                            String ans2 = input.nextLine();

                            mOPS.setB(v.get(0), ans2, n-1);
                            mOPS.getX(v.get(0), m.get(0), n-1);
                        break;

                        case "/dotProduct":
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

                        case "/crossProduct" :
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
                   
                        case "/parallelepiped" :
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

                        case "/getArea":
                            System.out.println("\n>> Insert shape name for which the area will be calculate (triangle, parallelogram)\n");
                            System.out.print("   ");
                            ans = input.nextLine();

                            switch(ans) {
                                
                                case "triangle":
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
                                    System.out.println("\n>> The area of the triangle is : " + vOPS.getTriangleArea(v.get(0), v.get(1)));
                                break;

                                case "parallelogram" :
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
                                    System.out.println("\n>> The area of the parallelogram is : " + vOPS.getParallelogramArea(v.get(0), v.get(1)));
                                break;

                                default :
                                    System.out.println("\n>> ERROR : Not a valid shape name");
                                break;
                            }

                        break;

                        case "/orthoProj" :
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
                            if(v.get(0).length == v.get(1).length)
                                System.out.print("\n>> The orthogonal projection of V1 on V2 is : " );
                            vOPS.printVector(vOPS.orthoProjection(v.get(0), v.get(1)));
                        break;

                        case "/exit":
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