package random;

/**
 * @author cli
 * @since 8/31/17
 */
public class Reflection
{

    public static class Test
    {
        public void test()
        {
            validate();
        }

        public void validate()
        {
            System.out.println("Test");
        }
    }

    public static class Generator
    {
        public static class TestSubject extends Test
        {
            public String k;

            @Override
            public void validate()
            {
                System.out.println("Generator");
            }
        }

        public void print()
        {
            TestSubject t = new TestSubject();
            t.test();
        }
    }

    public static void main(String[] args)
    {
        Generator g = new Generator();
        g.print();
    }
}
