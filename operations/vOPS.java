package operations;
import java.text.DecimalFormat;
import java.util.ArrayList;
/**
 * Class containing the basic methods necessary for setting up and printing vectors
 * 
 * @author Nadeem Samaali
 * @version 2.3.2 | Implementaion of the new getDeterminant method
 */
public class vOPS 
{
    static DecimalFormat df = new DecimalFormat("0.000");
    static DecimalFormat d0 = new DecimalFormat("0.000000000");
    /**
     * Method initializing the vector from the string list of components inputted by user
     * @param V Vector
     * @param E String array of the component entries
     */
    public static void setVector(double[] V, String[] E)
    {
        for(int i = 0; i<V.length; i++)
        {
            V[i] = Double.valueOf(E[i]);
        }
    }
    /**
     * Method printing a vector
     * @param V Vector
     */
    public static void printVector(double[] V)
    {
        System.out.print("[");
        for(int i = 0; i<V.length-1; i++)
            System.out.print(df.format(V[i])+",");
        System.out.print(df.format(V[V.length-1]) + "]\n");  
    }
    /**
     * Method calculating the dot product between two vectors
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return the scalar dot product
     */
    public static double getDotProduct(double[] v1, double[] v2)
    {
        double dotProduct = 0.0;
        for(int i = 0; i<v1.length; i++)
            dotProduct += v1[i]*v2[i];

        return dotProduct;
    }
    /**
     * This method calculated the cross product between two vetors 
     * 
     * @param v1 First vector
     * @param v2 Second vector
     * @return v3 -> The cross product between v1 and v2
     */
    public static double[] getCrossProduct(double[] v1, double[] v2) {

        double[][] M = {{1,-1,1},{0,0,0},{0,0,0}};
        ArrayList<Double> C = new ArrayList<>();
        double[][] C0 = new double[2][2];
        double[] v3 = new double[3];

        if(v1.length !=3 || v2.length != 3)
            throw new IllegalArgumentException("Make sure both vectors are 3-dimensional");
        
        M[1] = v1;
        M[2] = v2;

        for(int l = 0; l<3; l++) {
            for(int i = 0; i<3; i++) {
                for(int j = 0; j<3; j++) {
                    if(i != 0 && j != l)
                    {
                        C.add(M[i][j]);
                    }
                }
            }
            int c = 0;
            for(int a = 0; a<2; a++) {
                for(int b = 0; b<2; b++) {
                    C0[a][b] = C.get(c);
                    c++;
                }
            }
            v3[l] = M[0][l]*mOPS.getDeterminant(C0);
            C.clear();
        }
        return v3;
    }
    /**
     * This method will calculate the volume of a parallelepiped by doing the triple product of the three vectors
     * making up the parallelipied
     * @param v1
     * @param v2
     * @param v3
     * @return The area (triple product of the vectors)
     */
    public static double parallelepiped(double[] v1, double[] v2, double[] v3) {
        if(v1.length != 3 || v2.length != 3 || v3.length != 3)
            throw new IllegalArgumentException("Make sure all vectors are 3-dimensional");
        double area = Double.valueOf(df.format(getDotProduct(v1, getCrossProduct(v2, v3))));
        return area;
    }
    /**
     * Method calculates the area of a triangle enclosed between two vectors 
     * @param v1
     * @param v2
     * @return area
     */
    public static double getTriangleArea(double[] v1, double[] v2) {
        if(v1.length != 3 || v2.length != 3) {
            throw new IllegalArgumentException("Make sure all vectors are 3-dimensional");
        }
        double[] v3 = getCrossProduct(v1, v2);
        double area = Double.valueOf(df.format(0.5*Math.sqrt(Math.pow(v3[0],2)+Math.pow(v3[1],2)+Math.pow(v3[2],2))));
        return area;
    }
    /**
     * Method calculates the ares of a parallelogram enclosed between two vectors
     * @param v1
     * @param v2
     * @return area
     */
    public static double getParallelogramArea(double[] v1, double[] v2) {
        if(v1.length != 3 || v2.length != 3) {
            throw new IllegalArgumentException("Make sure all vectors are 3-dimensional");
        }
        double[] v3 = getCrossProduct(v1, v2);
        double area = Double.valueOf(df.format(Math.sqrt(Math.pow(v3[0],2)+Math.pow(v3[1],2)+Math.pow(v3[2],2))));
        return area;
    }
    /**
     * Method finds the orthogonal project of v1 onto v2
     * @param v1
     * @param v2
     * @return v3 : the orthogonal projection
     */
    public static double[] orthoProjection(double[] v1, double[] v2) {     
        double k = 0;
        double aSquared = 0;
        if(v1.length != v2.length)
            throw new IllegalArgumentException("Make sure both vectors are of the same dimension");
        double[] v3 = new double[v1.length];
        k = getDotProduct(v1, v2);
        for(int i = 0; i<v1.length; i++)
            aSquared += Math.pow(v2[i],2);
        for(int j = 0; j<v1.length; j++)
            v3[j] = Double.valueOf(df.format((k/aSquared)*v2[j]));
        return v3;
    } 
}
