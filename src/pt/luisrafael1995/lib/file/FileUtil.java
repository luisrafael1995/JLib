package pt.luisrafael1995.lib.file;

import pt.luisrafael1995.lib.collection.CollectionUtil;
import pt.luisrafael1995.lib.text.StringUtil;
import pt.luisrafael1995.lib.util.Extra;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class FileUtil {

    // New file format: <file not extension> (<tries>)<file extension>
    private static final String NEW_FILE_FORMAT = "%s (%d)%s";
    private static final String NEW_FOLDER_FORMAT = "%s (%d)";

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
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.exists() && !parent.mkdirs()) {
                    return false;
                }
            }

            try {
                return file.createNewFile();
            } catch (Exception ignore) {
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
            while (!createFile(file) && file.exists()) {
                String newName = String.format(NEW_FILE_FORMAT, pre, ++tries, pos);
                file = new File(file.getParentFile(), newName);
            }

            if (!file.exists()) {
                file = null;
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

    public static boolean createFolder(File folder) {
        return folder != null && folder.mkdirs();
    }

    public static boolean createFolder(String folderPath) {
        return folderPath != null && createFolder(new File(folderPath));
    }

    public static File createNewFolder(File folder) {
        if (folder != null) {
            String name = folder.getName();

            int tries = 0;
            while (!createFolder(folder) && folder.exists()) {
                String newName = String.format(NEW_FOLDER_FORMAT, name, ++tries);
                folder = new File(folder.getParentFile(), newName);
            }

            if (!folder.exists()) {
                folder = null;
            }
        }
        return folder;
    }

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
        } catch (Exception ignore) {
            return file;
        }
    }

    private static void listFiles(List<File> output, File file, int depth, boolean includeDirs) {
        if (file == null || !file.exists() || !file.isDirectory() || depth == 0) {
            return;
        }

        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        for (File subFile : files) {
            if (includeDirs || !subFile.isDirectory()) {
                output.add(subFile);
            }
            listFiles(output, subFile, depth - 1, includeDirs);
        }
    }

    public static List<File> listFiles(File file, int depth, boolean includeDirs) {
        List<File> filesList = new ArrayList<>();
        listFiles(filesList, file, depth, includeDirs);
//        Collections.sort(filesList);
        return filesList;
    }

    public static List<File> listFiles(File file, int depth) {
        return listFiles(file, depth, true);
    }

    public static List<File> listFiles(File file, boolean includeDirs) {
        return listFiles(file, 1, includeDirs);
    }

    public static List<File> listFiles(File file) {
        return listFiles(file, true);
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
        List<File> fileList = listFiles(toDelete);
        for (File file : fileList) {
            delete(file);
        }
        return toDelete.delete();
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
        String replacement = invalidCharsReplace == null ? "" : invalidCharsReplace;
        return filename == null ? null : filename.replaceAll(INVALID_FILENAME_CHARS_REGEX, replacement);
    }

    public static File filterCaseSensitiveFile(File file) {
        return filterCaseSensitiveFile(canonicalFile(file), false, null);
    }

    private static File filterCaseSensitiveFile(File file, boolean isDirectory, List<String> ignoreNames) {
        if (file == null) {
            return null;
        }

        final String filename = file.getName();
        File parent = filterCaseSensitiveFile(file.getParentFile(), true, null);

        if (parent != null && parent.exists() && parent.isDirectory()) {
            List<String> ignoreParentNames = new ArrayList<>();
            ignoreParentNames.add(parent.getName());

            do {
                try {
                    List<File> files = listFiles(parent);
                    List<String> fileNames = CollectionUtil.convertList(files, File::getName);
                    String newFilename = CollectionUtil.find(fileNames, name ->
                            StringUtil.equalsIgnoreCase(filename, name) &&
                                    (ignoreNames == null || !ignoreNames.contains(name)));

                    if (newFilename != null) {
                        File newFile = new File(parent, newFilename);
                        if (!isDirectory || newFile.isDirectory()) {
                            return newFile;
                        }
                    }

                } catch (Exception ignore) {
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
