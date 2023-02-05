package com.example.io.zip;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author zsl
 * @date 2023/1/20
 * @apiNote
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        toZip();

    }

    // 好像写成了无限递归....
    public static void toZip() throws IOException {
        Path testZipPath = Path.of("C:/Users/hougu/IdeaProjects/epic/io/src/main/resources/tmp/test.zip");
        // Files.createDirectories(testZipPath.getParent());
        try (OutputStream out = Files.newOutputStream(testZipPath);
            ZipOutputStream zipOutputStream = new ZipOutputStream(out);
            Stream<Path> dir = Files.walk(Paths.get("C:/Users/hougu/IdeaProjects/epic/io/src/main/resources/tmp"))){
            dir.forEachOrdered(
                path -> {
                    try {
                        if(Files.isRegularFile(path)){
                            zipOutputStream.putNextEntry(new ZipEntry(path.toString()));
                            Files.copy(path,zipOutputStream);
                        }    else {
                            zipOutputStream.putNextEntry(new ZipEntry(path.toString()+"/"));
                        }
                        zipOutputStream.closeEntry();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            );
        }
    }

    public static void unZip() throws IOException {
        Path testZipPath = Path.of("C:/Users/hougu/IdeaProjects/epic/io/src/main/resources/tmp/test.zip");
        ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(testZipPath));
        ZipEntry zipEntry;
        while ((zipEntry = zipInputStream.getNextEntry())!=null){
            System.out.println(zipEntry.getName());
            Path tmp = Path.of("tmp");
            if(zipEntry.getName().endsWith("/")){
                Files.createDirectories(tmp.resolve(zipEntry.getName()));
            }else{
                Files.copy(zipInputStream,tmp.resolve(zipEntry.getName()));
            }
            zipInputStream.closeEntry();
        }
    }

    // apache 的 VFS2 读取特定文件
}
