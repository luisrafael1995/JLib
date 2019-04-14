package pt.luisrafael1995.lib.file;

import pt.luisrafael1995.lib.text.StringUtil;
import pt.luisrafael1995.lib.util.Extra;
import pt.luisrafael1995.lib.util.RandomPlus;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class FileUtil {

    // New file format: <file not extension> (<tries>)<file extension>
    private static final String NEW_FILE_FORMAT = "%s (%d)%s";

    private static final String CURRENT_DIR_NAME = ".";
    private static final String DESKTOP_DIR_NAME = "Desktop";
    private static final String DOWNLOAD_DIR_NAME = "Downloads";

    private static final String HOME_DIR_PROPERTY_KEY = "user.home";
    private static final String TMP_DIR_PROPERTY_KEY = "java.io.tmpdir";

    private static final String INVALID_FILENAME_CHARS_REGEX = "[\\\\/:*?\"<>|]"; // maybe '\0' for linux

    private FileUtil() {
    }

    public static String getExtension(File file) {
        if (file != null) {
            String filename = file.getName();
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex <= 0) {
                dotIndex = filename.length();
            }
            return filename.substring(dotIndex);
        }
        return null;
    }

    public static String getExtension(String filename) {
        return getExtension(filename == null ? null : new File(filename));
    }

    public static String getSimpleName(File file) {
        if (file != null) {
            String filename = file.getName();
            int dotIndex = filename.lastIndexOf('.');
            if (dotIndex <= 0) {
                dotIndex = filename.length();
            }
            return filename.substring(0, dotIndex);
        }
        return null;
    }

    public static String getSimpleName(String filename) {
        return getSimpleName(filename == null ? null : new File(filename));
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
                String newName = String.format(NEW_FILE_FORMAT, pre, ++tries, pos);
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

    // todo: implement or not
    // todo: = = = = = = = = = = = = = = = = = = = = = =
    private static boolean createFolder(File folder) {
        return false;
    }

    private static File createNewFolder(File folder) {
        return null;
    }
    // todo: = = = = = = = = = = = = = = = = = = = = = =

    public static File moveFile(File src, File dest) {
        return moveFile(src, dest, false);
    }

    public static File moveFile(File src, File dest, boolean forceMove) {
        if (Extra.equals(src, dest) || dest == null) {
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
        return filterCaseSensitiveFile(new File(CURRENT_DIR_NAME));
    }

    public static File homeDir() {
        return filterCaseSensitiveFile(new File(System.getProperty(HOME_DIR_PROPERTY_KEY)));
    }

    public static File desktopDir() {
        return filterCaseSensitiveFile(new File(homeDir(), DESKTOP_DIR_NAME));
    }

    public static File downloadsDir() {
        return filterCaseSensitiveFile(new File(homeDir(), DOWNLOAD_DIR_NAME));
    }

    public static File tmpDir() {
        return filterCaseSensitiveFile(new File(System.getProperty(TMP_DIR_PROPERTY_KEY)));
    }

    public static boolean delete(File toDelete) {
        return toDelete == null || delete(toDelete.toPath());
    }

    private static boolean delete(Path toDelete) {
        Extra.ignoreExceptions(() -> {
            Files.walk(toDelete).sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        Extra.ignoreExceptions(() -> {
                            Files.deleteIfExists(path);
                        });
                    });
        });

        return !Files.exists(toDelete);
    }

    public static void deleteIfEmpty(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] list = dir.list();
            if (list != null && list.length == 0) {
                delete(dir);
            }
        }
    }

    public static String filterFilename(String filename, String invalidCharsReplace) {
        String replacement = Optional.of(invalidCharsReplace).orElse("");
        return Optional.of(filename).map(s -> s.replaceAll(INVALID_FILENAME_CHARS_REGEX, replacement)).orElse(null);
    }

    public static File filterCaseSensitiveFile(File file) {
        return filterCaseSensitiveFile(canonicalFile(file), false, null);
    }

    private static File filterCaseSensitiveFile(File file, boolean isDirectory, List<String> ignoreNames) {
        if (file == null) {
            return file;
        }

        String filename = file.getName();
        File parent = filterCaseSensitiveFile(file.getParentFile(), true, null);

        if (parent != null && parent.exists() && parent.isDirectory()) {
            List<String> ignoreParentNames = new ArrayList<>();
            ignoreParentNames.add(parent.getName());

            do {
                try {
                    Optional<String> newFilename = Files.list(parent.toPath()).map(path -> path.toFile().getName())
                            .filter(s -> StringUtil.equalsIgnoreCase(filename, s) && (ignoreNames == null || !ignoreNames.contains(s)))
                            .min((o1, o2) -> RandomPlus.getInstance().nextInt(-1, 1));

                    if (newFilename.isPresent()) {
                        File newFile = new File(parent, newFilename.get());
                        if (!isDirectory || newFile.isDirectory()) {
                            return newFile;
                        }
                    }

                } catch (Exception e) {
                    // nothing
                }

                File newParent = filterCaseSensitiveFile(parent, true, ignoreParentNames);
                if (Extra.equals(parent, newParent) || !newParent.exists() || !newParent.isDirectory()) {
                    break;
                }

                parent = newParent;
                ignoreParentNames.add(parent.getName());
            } while (true);
        }

        return parent == null ? file : new File(parent, filename);
    }
}
