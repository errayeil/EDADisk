package com.github.errayeil.EDADisk;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class SysUtils {

    /**
     *
     */
    private SysUtils() {

    }

    /**
     *
     * @return
     */
    public static boolean isWindows() {
        return getOS( ).contains( "Windows" );
    }

    /**
     *
     * @return
     */
    public static boolean isMac() {
        return getOS().contains( "Mac" );
    }

    /**
     *
     * @return
     */
    public static boolean isLinux() {
        return getOS().contains( "Linux" );
    }

    /**
     *
     * @return
     */
    public static boolean isOther() {
        return !isMac() && !isWindows() && !isLinux();
    }

    /**
     *
     * @return
     */
    public static String getJavaVersion() {
        return System.getProperty( "java.version" );
    }

    /**
     *
     * @return
     */
    public static String getDefaultUserDirectory() {
        return System.getProperty( "user.home" );
    }

    /**
     *
     * @return
     */
    public static String getUserName() {
        return System.getProperty( "user.name" );
    }

    /**
     *
     * @return
     */
    private static String getOS() {
        return System.getProperty( "os.name" );
    }
}
