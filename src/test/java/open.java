import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class open {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Directory Chooser Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton openButton = new JButton("Open Directory Chooser");
        frame.add(openButton);

        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                // 设置文件选择器的模式为选择目录
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                // 显示文件选择对话框
                int returnValue = fileChooser.showOpenDialog(null);

                // 检查用户是否选择了目录
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "You selected directory: " + selectedDirectory.getAbsolutePath());
                } else {
                    JOptionPane.showMessageDialog(null, "No directory selected.");
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}

