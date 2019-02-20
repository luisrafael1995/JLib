package pt.luisrafael1995.lib.file;

import pt.luisrafael1995.lib.extra.ObjectUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class FileUtil {

    // New file format: <file not extension> (<tries>)<file extension>
    private static final String newFileFormat = "%s (%d)%s";
    private static final String CURRENT_DIR_NAME = ".";
    private static final String DOWNLOAD_DIR_NAME = "Downloads";
    private static final String HOME_DIR_PROPERTY_KEY = "user.home";
    private static final String TMP_DIR_PROPERTY_KEY = "java.io.tmpdir";

    private FileUtil() {
    }

    public static String getExtension(File file) {
        return file == null ? null : getExtension(file.getName());
    }

    public static String getExtension(String filename) {
        if (filename != null) {
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex < 0) {
                dotIndex = filename.length();
            }
            return filename.substring(dotIndex);
        }
        return null;
    }

    public static String getSimpleName(File file) {
        return file == null ? null : getSimpleName(file.getName());
    }

    public static String getSimpleName(String filename) {
        if (filename != null) {
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex < 0) {
                dotIndex = filename.length();
            }
            return filename.substring(0, dotIndex);
        }
        return null;
    }

    public synchronized static boolean createFile(File file) {
        if (file != null) {
            List<File> createdFolders = new ArrayList<>();
            File parent = file;
            while ((parent = parent.getParentFile()) != null && !parent.exists()) {
                createdFolders.add(0, parent);
            }

            for (File folder : createdFolders) {
                if (!folder.mkdir()) {
                    break;
                }
            }

            try {
                return file.createNewFile();
            } catch (Exception e) {
                for (File folder : createdFolders) {
                    delete(folder);
                }
            }
        }
        return false;
    }

    public static boolean createFile(String filename) {
        return filename != null && createFile(new File(filename));
    }

    public static boolean createFile(File parent, String filename) {
        return filename != null && createFile(new File(parent, filename));
    }

    public static boolean createFile(String parent, String filename) {
        return filename != null && createFile(new File(parent, filename));
    }

    public synchronized static File createNewFile(File file) {
        if (file != null) {
            String pre = getSimpleName(file);
            String pos = getExtension(file);

            int tries = 0;
            while (!createFile(file)) {
                String newName = String.format(newFileFormat, pre, ++tries, pos);
                file = new File(file.getParentFile(), newName);
            }
        }
        return file;
    }

    public static File createNewFile(String filename) {
        return filename == null ? null : createNewFile(new File(filename));
    }

    public static File createNewFile(File parent, String filename) {
        return filename == null ? null : createNewFile(new File(parent, filename));
    }

    public static File createNewFile(String parent, String filename) {
        return filename == null ? null : createNewFile(new File(parent, filename));
    }

    public static File moveFile(File src, File dest) {
        return moveFile(src, dest, false);
    }

    public static File moveFile(File src, File dest, boolean forceMove) {
        if (ObjectUtil.equals(src, dest) || dest == null) {
            return src;
        }

        if (src == null) {
            return dest;
        }

        if (!createFile(dest) && !forceMove) {
            dest = createNewFile(dest);
        }
        delete(dest);

        return src.renameTo(dest) ? dest : src;
    }

    public static File canonicalFile(File file) {
        try {
            return file.getCanonicalFile();
        } catch (Exception e) {
            return file;
        }
    }

    public static File currentDir() {
        return canonicalFile(new File(CURRENT_DIR_NAME));
    }

    public static File homeDir() {
        return canonicalFile(new File(System.getProperty(HOME_DIR_PROPERTY_KEY)));
    }

    public static File downloadsDir() {
        return canonicalFile(new File(homeDir(), DOWNLOAD_DIR_NAME));
    }

    public static File tmpDir() {
        return canonicalFile(new File(System.getProperty(TMP_DIR_PROPERTY_KEY)));
    }

    public static boolean delete(File toDelete) {
        return toDelete == null || delete(toDelete.toPath());
    }

    private static boolean delete(Path toDelete) {
        ObjectUtil.ignoreExceptions(() -> {
            Files.walk(toDelete).sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        ObjectUtil.ignoreExceptions(() -> {
                            Files.deleteIfExists(path);
                        });
                    });
        });

        return !Files.exists(toDelete);
    }

    public static void deleteIfEmpty(File dir) {
        ObjectUtil.ifNotNull(dir.list(), list -> {
            if (list.length == 0) {
                FileUtil.delete(dir);
            }
        });
    }
}
