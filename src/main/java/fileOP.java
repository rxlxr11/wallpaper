import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

public class fileOP {

    //文件夹的拷贝
    public static void copyDir(String sourcePath, String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        String[] filePath = start.list();		//获取该文件夹下的所有文件以及目录的名字
        if(!end.exists()) {
            end.mkdir();
        }
        for(String temp:filePath) {
            //查看其数组中每一个是文件还是文件夹
            if(new File(sourcePath+File.separator+temp).isDirectory()) {
                //为文件夹，进行递归
                copyDir(sourcePath+File.separator+temp, newPath+File.separator+temp);
            }else {
                //为文件则进行拷贝
                copyFile(sourcePath+File.separator+temp, newPath+File.separator+temp);
            }
        }
    }

    //文件的拷贝
    public static void copyFile(String sourcePath, String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(start));
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(end))) {
            int len = 0;
            byte[] flush = new byte[1024];
            while((len=bis.read(flush)) != -1) {
                bos.write(flush, 0, len);
            }
            bos.flush();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void delete (String dir) throws IOException{

        Path directory = Paths.get(dir);
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
                Files.delete(file); // 有效，因为它始终是一个文件
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir); //这将起作用，因为目录中的文件已被删除
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void createDir( String directoryPath) {

        Path path = Paths.get(directoryPath);

        try {
            Files.createDirectory(path);
            System.out.println("目录已创建");
        } catch (IOException e) {
            System.err.println("无法创建目录：" + e);
        }
    }

}