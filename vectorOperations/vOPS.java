package vectorOperations;

/**
 * Class containing the basic methods necessary for setting up and printing vectors
 * 
 * @author Nadeem Samaali
 * @version 1.0.0 | First release
 */
public class vOPS 
{
    /**
     * Method initializing the vector from the string list of components inputted by user
     * @param V Vector
     * @param E String array of the component entries
     */
    public static void setVectorFromString(double[] V, String[] E)
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
        System.out.print("\n[");
        for(int i = 0; i<V.length-1; i++)
            System.out.print(V[i]+",");
        System.out.print(V[V.length-1] + "]\n");  
    }
}
