package vectorOperations;

/**
 * Class containing the necessary method for calculating the vectorial dot product
 * 
 * @author Nadeem Samaali
 * @version 1.0.0 | First release
 */
public class dotProduct 
{
    public static double getDotProduct(double[] v1, double[] v2)
    {
        double dotProduct = 0.0;
        for(int i = 0; i<v1.length; i++)
            dotProduct += v1[i]*v2[i];

        return dotProduct;
    }
}
