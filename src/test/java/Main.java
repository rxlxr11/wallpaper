import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Main {

    public static String RePKG = "C:\\Users\\ACER\\Desktop\\tool\\RePKG"; //RePKG文件夹位置
    public static String output = "C:\\Users\\ACER\\Desktop\\tool\\wallpaper"; //输出文件夹位置
    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("From：");
        String sourcePath = scanner.nextLine();
        File source = new File(sourcePath);
        String dest1 = new StringBuilder(RePKG).append("\\"+source.getName()).toString();
        fileOP.createDir(dest1);

        fileOP.copyDir(sourcePath , dest1);
        powershellCmd.shellEXE();
        //

        String out = new StringBuilder(RePKG).append("\\output\\"+source.getName()).toString();
        String dest2 = new StringBuilder(output).append("\\"+source.getName()).toString();
        fileOP.copyDir(out , dest2);
        fileOP.delete(dest1);
        fileOP.delete(out);
    }

}