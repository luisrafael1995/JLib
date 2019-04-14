package pt.luisrafael1995.lib.storage;

import pt.luisrafael1995.lib.file.FileUtil;
import pt.luisrafael1995.lib.gson.GsonUtil;
import pt.luisrafael1995.lib.stream.IOStreamUtil;
import pt.luisrafael1995.lib.text.StringUtil;
import pt.luisrafael1995.lib.util.Extra;

import java.io.*;

public final class SimpleStorage {

    private static final String STORAGE_FOLDER_NAME = FileUtil.getSimpleName(
            SimpleStorage.class.getProtectionDomain().getCodeSource().getLocation().getFile()
    );
    private final File folder;

    public SimpleStorage(File outputDir) {
        if (outputDir == null || (!outputDir.exists() && outputDir.isDirectory())) {
            outputDir = FileUtil.currentDir();
        }
        folder = new File(outputDir, STORAGE_FOLDER_NAME);
    }

    private File getFile(String filename) {
        return filename == null ? null : new File(folder, new File(filename).getName());
    }

    public void read(String filename, OutputStream os) {
        File file;
        if ((file = getFile(filename)) != null) {
            Extra.ignoreExceptions(() -> {
                try (FileInputStream fis = new FileInputStream(file)) {
                    IOStreamUtil.copy(fis, os);
                }
            });
        }
    }

    public byte[] read(String filename) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            read(filename, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }

    public <T> T read(String filename, Class<T> c) {
        return GsonUtil.getObject(StringUtil.toString(read(filename)), c);
    }

    public void write(String filename, InputStream is, boolean append) {
        File file;
        if ((file = getFile(filename)) != null) {
            FileUtil.createFile(file);
            Extra.ignoreExceptions(() -> {
                try (FileOutputStream fos = new FileOutputStream(file, append)) {
                    IOStreamUtil.copy(is, fos);
                }
            });
        }
    }

    public void write(String filename, byte[] bytes, boolean append) {
        Extra.ignoreExceptions(() -> {
            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
                write(filename, bais, append);
            }
        });
    }

    public void write(String filename, InputStream is) {
        write(filename, is, false);
    }

    public void write(String filename, byte[] bytes) {
        write(filename, bytes, false);
    }

    public <T> void write(String filename, T obj) {
        write(filename, StringUtil.getBytes(GsonUtil.toJson(obj)));
    }

    public void delete(String filename) {
        FileUtil.delete(getFile(filename));
        FileUtil.deleteIfEmpty(folder);
    }
}
