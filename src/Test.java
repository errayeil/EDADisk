import com.github.errayeil.EDADisk.SysUtils;

public class Test {

    public Test() {
        runTest();
    }

    /**
     *
     * @param args
     */
    public static void main (String[] args) {
        new Test();
    }

    /**
     *
     */
    public void runTest() {
        System.out.println( SysUtils.getUserName() );
    }
}
