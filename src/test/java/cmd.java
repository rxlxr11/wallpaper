import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class cmd {
    public static void main(String[] args) {
        String powerShellScriptPath = "D:\\java\\IDEA\\wallpaper\\src\\main\\java\\myScript.ps1"; // 替换为你的脚本文件路径
        int timeoutInSeconds = 600; // 设置超时时间为10分钟（以秒为单位）

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("powershell.exe", "-File", powerShellScriptPath);
            Process process = processBuilder.start();

            // 创建标准输出流的读取器
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                // 处理 PowerShell 命令的输出
                System.out.println(line);
            }

            // 等待 PowerShell 进程完成，但不超过超时时间
            if (process.waitFor(timeoutInSeconds, java.util.concurrent.TimeUnit.SECONDS)) {
                System.out.println("PowerShell 命令已完成。");
            } else {
                System.out.println("PowerShell 命令超时。");
                // 可以选择在此处执行其他操作，如终止进程
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
