package pt.luisrafael1995.lib.storage;

import pt.luisrafael1995.lib.extra.ObjectUtil;
import pt.luisrafael1995.lib.file.FileUtil;
import pt.luisrafael1995.lib.gson.GsonUtil;
import pt.luisrafael1995.lib.stream.IOStreamUtil;
import pt.luisrafael1995.lib.text.StringUtil;

import java.io.*;

public final class SimpleStorageManager {

    private static final File simpleStorageFolder = new File(FileUtil.homeDir(), "JLibSimpleStorage");
    private static final File thisStorageFolder = new File(simpleStorageFolder, FileUtil.getSimpleName(
            new File(SimpleStorageManager.class.getProtectionDomain().getCodeSource().getLocation().getFile()))
    );

    private SimpleStorageManager() {
    }

    private static File getFile(String filename) {
        return filename == null ? null : new File(thisStorageFolder, new File(filename).getName());
    }

    public static void read(String filename, OutputStream os) {
        ObjectUtil.ifNotNull(getFile(filename), file -> ObjectUtil.ignoreExceptions(() -> {
            try (FileInputStream fis = new FileInputStream(file)) {
                IOStreamUtil.copy(fis, os);
            }
        }));
    }

    public static byte[] read(String filename) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            read(filename, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public static <T> T read(String filename, Class<T> c) {
        return GsonUtil.getObject(StringUtil.toString(read(filename)), c);
    }

    public static void write(String filename, InputStream is, boolean append) {
        ObjectUtil.ifNotNull(getFile(filename), file -> {
            FileUtil.createFile(file);
            ObjectUtil.ignoreExceptions(() -> {
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    IOStreamUtil.copy(is, fos);
                }
            });
        });
    }

    public static void write(String filename, byte[] bytes, boolean append) {
        ObjectUtil.ignoreExceptions(() -> {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
                write(filename, bais, append);
            }
        });
    }

    public static void write(String filename, InputStream is) {
        write(filename, is, false);
    }

    public static void write(String filename, byte[] bytes) {
        write(filename, bytes, false);
    }

    public static <T> void write(String filename, T obj) {
        write(filename, StringUtil.getBytes(GsonUtil.toJson(obj)));
    }

    public static void remove(String filename) {
        FileUtil.delete(getFile(filename));
        FileUtil.deleteIfEmpty(thisStorageFolder);
        FileUtil.deleteIfEmpty(simpleStorageFolder);
    }
}
