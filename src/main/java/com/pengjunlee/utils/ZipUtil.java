package com.pengjunlee.utils;

/**
 * @author pengjunlee
 * @create 2019-11-19 17:23
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.Enumeration;

@Slf4j
public class ZipUtil {

    /**
     * 由于压缩方法是静态方法，无须创建工具类实例，故将构造方法声明为私有
     */
    private ZipUtil() {

    }

    /**
     * 压缩文件或目录
     *
     * @param srcPath  要压缩的源文件或目录
     * @param destPath 生成的压缩文件全名，这里是绝对路径
     * @param encoding 压缩时采用的编码格式
     */
    public static void zip(String srcPath, String destPath, String encoding) {
        if (isEmptyStr(srcPath) || isEmptyStr(destPath)) {
            log.error("invalid zip parameters...");
            return;
        }
        if (isEmptyStr(encoding)) {
            encoding = "UTF-8";
        }
        zip(new File(srcPath), new File(destPath), encoding);
    }

    /**
     * 压缩文件或目录
     *
     * @param srcFile  目录或者单个文件
     * @param destFile 压缩后的ZIP文件
     * @param encoding 压缩时采用的编码格式
     */
    public static void zip(File srcFile, File destFile, String encoding) {
        File parentFile = destFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(destFile));
            out.setEncoding(encoding);
            zip(srcFile, out);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (null != out) {
                try {
                    out.close();// 记得关闭资源
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 压缩文件或目录
     *
     * @param srcFile 目录或者单个文件
     * @param out     压缩文件输出流
     */
    public static void zip(File srcFile, ZipOutputStream out) {
        zip(srcFile, out, "");
    }

    /**
     * 压缩文件或目录
     *
     * @param srcFile 目录或者单个文件
     * @param out     压缩文件输出流
     * @param curPaht 正在压缩的当前文件与srcPath的相对路径
     */
    public static void zip(File srcFile, ZipOutputStream out, String curPaht) {
        if (srcFile.isDirectory()) {
            File[] files = srcFile.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    String name = srcFile.getName();
                    if (!"".equals(curPaht)) {
                        name = curPaht + File.separator + name;
                    }
                    ZipUtil.zip(file, out, name);
                }
            }
        } else {
            doZip(srcFile, out, curPaht);
        }
    }

    /**
     * 压缩文件
     *
     * @param file    要压缩的文件
     * @param out     压缩文件输出流
     * @param curPath 正在压缩的当前文件与srcPath的相对路径
     */
    public static void doZip(File file, ZipOutputStream out, String curPath) {
        String entryName = null;
        if (!"".equals(curPath)) {
            entryName = curPath + File.separator + file.getName();
        } else {
            entryName = file.getName();
        }
        ZipEntry entry = new ZipEntry(entryName);
        FileInputStream fis = null;
        try {
            out.putNextEntry(entry);
            fis = new FileInputStream(file);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
                out.flush();
            }
            out.closeEntry();
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

    }

    /**
     * 判断是否为空字符串
     *
     * @param str
     * @return
     */
    private static boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 解压缩
     *
     * @param zipFilePath     压缩文件路径
     * @param outputDirectory 解压缩后文件存放的目录
     * @param encoding        解压缩时采用的编码格式
     */
    public static void unzip(String zipFilePath, String outputDirectory, String encoding) {
        if (isEmptyStr(zipFilePath) || isEmptyStr(outputDirectory)) {
            log.error("invalid unzip parameters...");
            return;
        }
        if (isEmptyStr(encoding)) {
            encoding = "UTF-8";
        }
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipFilePath, encoding);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        unzip(zipFile, outputDirectory, encoding);
    }

    /**
     * 解压缩
     *
     * @param zipFile         源压缩文件
     * @param outputDirectory 解压缩后文件存放目录
     * @param encoding        解压缩时采用的编码格式
     */
    @SuppressWarnings("rawtypes")
    private static void unzip(ZipFile zipFile, String outputDirectory, String encoding) {
        File outputFile = new File(outputDirectory);
        if (!outputFile.exists()) {
            outputFile.mkdirs();
        }
        try {
            Enumeration zipEntries = zipFile.getEntries();
            ZipEntry zipEntry = null;

            while (zipEntries.hasMoreElements()) {
                zipEntry = (ZipEntry) zipEntries.nextElement();

                String entryName = new String(zipEntry.getName().getBytes(
                        encoding), encoding);

                InputStream in = null;
                FileOutputStream out = null;

                try {
                    if (zipEntry.isDirectory()) {
                        String name = zipEntry.getName();
                        name = name.substring(0, name.length() - 1);

                        File f = new File(outputDirectory + File.separator
                                + name);
                        f.mkdirs();
                    } else {
                        int index = entryName.lastIndexOf("\\");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator
                                    + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        index = entryName.lastIndexOf("/");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator
                                    + entryName.substring(0, index));
                            df.mkdirs();
                        }

                        File f = new File(outputDirectory + File.separator
                                + zipEntry.getName());
                        // f.createNewFile();
                        in = zipFile.getInputStream(zipEntry);
                        out = new FileOutputStream(f);

                        int len;
                        byte[] buffer = new byte[1024 * 1024];

                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }
                        out.flush();
                    }
                } catch (IOException e) {
                    log.error("解压失败...");
                    log.error(e.getMessage());
                } finally {
                    if (null != in) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    }
                    if (null != out) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            }

        } catch (IOException e) {
            log.error("解压失败...");
            log.error(e.getMessage());
        } finally {
            if (null != zipFile) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

    }

    // 创建目录（文件夹）删除的方法
    public static void delete(String path) {
        // 为传进来的路径参数创建一个文件对象
        File file = new File(path);
        // 如果目标路径是一个文件，那么直接调用delete方法删除即可
        // file.delete();
        // 如果是一个目录，那么必须把该目录下的所有文件和子目录全部删除，才能删除该目标目录，这里要用到递归函数
        // 创建一个files数组，用来存放目标目录下所有的文件和目录的file对象
        File[] files = new File[50];
        // 将目标目录下所有的file对象存入files数组中
        files = file.listFiles();
        // 循环遍历files数组
        for (File temp : files) {
            // 判断该temp对象是否为文件对象
            if (temp.isFile()) {
                temp.delete();
            }
            // 判断该temp对象是否为目录对象
            if (temp.isDirectory()) {
                // 将该temp目录的路径给delete方法（自己），达到递归的目的
                delete(temp.getAbsolutePath());
                // 确保该temp目录下已被清空后，删除该temp目录
                temp.delete();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        // FdfsUtil.download(new FileOutputStream(new File("D:\\b.zip")), "group1/M00/00/00/rBD67l3UalmEWsNQAAAAAGQ0c7I3040769");
        delete("D:\\edas");
        // ZipUtil.zip("D:\\sourceFolder", "D:\\targetFolder\\test.zip", "GBK");
        ZipUtil.unzip("D:\\b.zip", "D:\\edas", "GBK");
        File poseFile = new File("D:\\edas\\pose");
        if (poseFile.exists()) {
            File[] files = poseFile.listFiles();
            for (File temp : files) {
                if (temp.isDirectory()) {
                    File[] files1 = temp.listFiles();
                    if(files1.length == 2){
                        for (File temp1 : files1){
                            System.out.println(temp1.getAbsolutePath());

                        }
                    }
                }
            }
        }

    }

}
