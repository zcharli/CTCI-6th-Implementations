package random;

import java.util.ArrayList;

/**
 * @author cli
 * @since 9/24/18
 */
public class ExceptionTest
{
    
    static class SqlException extends Exception {
        public SqlException(String a) {
            super(a);
        }
    }
    
    static class SQLNonTransientException extends SqlException {
        public SQLNonTransientException(String a) {
            super(a);
        }
    }
    
    public static void throwT() throws SQLNonTransientException
    {
        throw new SQLNonTransientException("poop");
    }
    
    public static void throwing() throws SqlException
    {
        try {
            throwT();
        } catch (SqlException e) {
            throw e;
        }
    }
    
    public static void run()
    {
        try
        {
            throwing();
        } catch (SQLNonTransientException e) {
            System.out.println("nonTran");
        }
        catch (SqlException e) {
            System.out.println("sql");
        }
    
        System.out.println(new int[]{}.toString());
    }
    
    public static void main(String[] args)
    {
        run();
    }
}
