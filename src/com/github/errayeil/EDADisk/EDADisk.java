package com.github.errayeil.EDADisk;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

/**
 * @author Steven Frizell
 * @version HIP 2
 * @since HIP 2
 */
public class EDADisk {

    /**
     *
     */
    final static Preferences prefs = Preferences.userNodeForPackage( EDADisk.class );

    /**
     *
     */
    public final static String DIR_SET_KEY = "CustomDirSet";

    /**
     *
     */
    public final static String DIR_KEY = "CustomDir";

    /**
     *
     */
    private static String cacheDirectory;

    /**
     *
     */
    private static String smallDirectory;

    /**
     *
     */
    private static String largeDirectory;

    /**
     *
     */
    private static String updateDirectory;

    /**
     *
     */
    private static String configDirectory ;

    /**
     *
     */
    private static String installDirectory;

    /**
     *
     */
    private EDADisk() {

    }

    /**
     *
     * @param newInstallDirectory
     * @param createNow
     */
    public static void setInstallDirectory(final String newInstallDirectory, boolean createNow) {
        prefs.putBoolean( DIR_SET_KEY, true );
        prefs.put( DIR_KEY, newInstallDirectory );

        installDirectory = newInstallDirectory;

        if (createNow) {
           createDirectories( Path.of( installDirectory ) );
        }
    }

    /**
     *
     */
    public static void createInstallDirectory() {
        if (installDirectory == null) {
            installDirectory = prefs.get( DIR_KEY, getDefaultEDAInstallDirectory() );
        }

        createDirectories( Path.of( installDirectory ) );
    }

    /**
     *
     */
    public static void createDirectories() {
        if (installDirectory == null) {
            installDirectory = prefs.get( DIR_KEY, getDefaultEDAInstallDirectory() );
        }

        cacheDirectory = installDirectory + File.separator + "Cache";
        configDirectory = installDirectory + File.separator + "Config";
        updateDirectory = installDirectory + File.separator + "Update";
        smallDirectory = cacheDirectory + File.separator + "Small";
        largeDirectory = largeDirectory + File.separator + "Large";

        try {
            Files.createDirectory( Path.of( cacheDirectory ) );
            Files.createDirectory( Path.of( configDirectory ) );
            Files.createDirectory( Path.of( updateDirectory ) );
            Files.createDirectory( Path.of( smallDirectory ) );
            Files.createDirectory( Path.of( largeDirectory ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public static boolean isInstallDirectoryCreated() {

        if (installDirectory == null) {
            installDirectory = prefs.get( DIR_SET_KEY, getDefaultEDAInstallDirectory() );
        }

        return Files.exists( Path.of( installDirectory));
    }

    /**
     *
     * @return
     */
    public static String getInstallDirectory() {
        if (installDirectory == null) {
            installDirectory = prefs.get( DIR_KEY, getDefaultEDAInstallDirectory() );
        }

        return installDirectory;
    }

    /**
     *
     * @return
     */
    public static String getCacheDirectory() {
        if (cacheDirectory == null) {
            cacheDirectory = getInstallDirectory() + File.separator + "Cache";
        }

        return cacheDirectory;
    }

    /**
     *
     * @return
     */
    public static String getSmallCacheDirectory() {
        if (smallDirectory == null) {
            smallDirectory = getCacheDirectory() + File.separator + "Small";
        }

        return smallDirectory;
    }

    /**
     *
     * @return
     */
    public static String getLargeCacheDirectory() {
        if (largeDirectory == null) {
            largeDirectory = getCacheDirectory() + File.separator + "Large";
        }

        return largeDirectory;
    }

    /**
     *
     * @return
     */
    public static String getUpdateDirectory() {
        if (updateDirectory == null) {
            updateDirectory = getInstallDirectory() + File.separator + "Update";
        }

        return updateDirectory;
    }

    /**
     *
     * @return
     */
    public static String getConfigDirectory() {
        if (configDirectory == null) {
            configDirectory = getInstallDirectory() + File.separator + "Config";
        }

        return configDirectory;
    }

    /**
     *
     * @return
     */
    public static File[] getSmallCacheFiles() {
        if (smallDirectory == null) {
            smallDirectory = getSmallCacheDirectory();
        }

        return new File(smallDirectory).listFiles();
    }

    /**
     *
     * @return
     */
    public static File[] getLargeCacheFiles() {
        if (largeDirectory == null) {
            largeDirectory = getLargeCacheDirectory();
        }

        return new File( largeDirectory ).listFiles();
    }

    /**
     * Gets the default installation directory, platform independent.
     *
     * @return
     */
    public static String getDefaultEDAInstallDirectory() {
        String installDir = "";

            if (SysUtils.isWindows()) {
                //TODO
            } else if (SysUtils.isMac()) {
                String userLibraryDir = SysUtils.getDefaultUserDirectory() + File.separator + "Library";
                String appSupportDir = userLibraryDir + File.separator + "Application Support";
                installDir = appSupportDir + File.separator + "EDA";
            } else if (SysUtils.isLinux()) {
                //TODO
            } else if (SysUtils.isOther()) {
                //TODO
            }

        return installDir;
    }

    /**
     *
     * @return
     */
    private static boolean isCustomDirSet() {
        return prefs.getBoolean( DIR_SET_KEY, false );
    }

    /**
     *
     * @param path
     */
    private static void createDirectories(Path path) {
        if (Files.isDirectory(  path )) {
            try {
                Files.createDirectories( path );
            } catch ( IOException e ) {
                e.printStackTrace( );
            }
        }
    }
}
