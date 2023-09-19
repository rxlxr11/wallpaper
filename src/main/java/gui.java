
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class gui {
    public static String RePKG = "C:\\Users\\ACER\\Desktop\\tool\\RePKG"; //RePKG文件夹位置
    public static String output = "C:\\Users\\ACER\\Desktop\\tool\\wallpaper"; //输出文件夹位置
    Set <JTextField> fieldSet = new HashSet<>();

    JFrame jFrame = new JFrame("wallpaper");
    JPanel jPanelbut = new JPanel();
    JPanel jPanelcentre = new JPanel();
    //创建菜单栏
    JMenuBar jMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("设置");

    JTextField jTextField = new JTextField(28) ;

    JButton confirm = new JButton("确认");
    JButton add = new JButton("添加");

    JMenuItem src = new JMenuItem(new AbstractAction("repkg文件夹") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //显示一个文件选择器
            JFileChooser jFileChooser = new JFileChooser(RePKG); //当前项目路径
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jFileChooser.showOpenDialog(jFrame);
            //获取用户选择的文件
            File file = jFileChooser.getSelectedFile();
            RePKG = file.getPath();
        }
    });
    JMenuItem dest = new JMenuItem(new AbstractAction("目标文件夹") {
        @Override
        public void actionPerformed(ActionEvent e) {
            //显示一个文件选择器
            JFileChooser jFileChooser = new JFileChooser(output); //当前项目路径
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jFileChooser.showSaveDialog(jFrame);
            //获取用户选择的保存文件路径
            File file = jFileChooser.getSelectedFile();
            output = file.getPath();
        }
    });





    //组装视图
    public void init() {
        fieldSet.add(jTextField);

        fileMenu.add(src);
        fileMenu.add(dest);
        jMenuBar.add(fileMenu);

        jPanelcentre.add(jTextField);

        jPanelbut.add(confirm);
        jPanelbut.add(add);

        jFrame.add(jPanelcentre,BorderLayout.CENTER);
        jFrame.add(jPanelbut,BorderLayout.SOUTH);
        jFrame.setJMenuBar(jMenuBar);


        confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                int i = 0;
                int length = fieldSet.size();
                String[] sourcePath = new String[length];
                String[] sourceName = new String[length];
                String[] dest1 = new String[length];
                String[] dest2 = new String[length];
                String[] out = new String[length];

                //获取文本框内容
                for (JTextField textField : fieldSet) {
                    sourcePath[i] = textField.getText();
                    File source = new File(sourcePath[i]);
                    sourceName[i] = source.getName();
                    dest1[i] = new StringBuilder(RePKG).append("\\"+sourceName[i]).toString();
                    fileOP.createDir(dest1[i]);
                    fileOP.copyDir(sourcePath[i] , dest1[i]);
                    i++;
                }

                powershellCmd.shellEXE();

                //删除多余文件
                for (int j = 0; j < length; j++) {
                    out[j] = new StringBuilder(RePKG).append("\\output\\"+sourceName[j]).toString();


                    dest2[j] = new StringBuilder(output).append("\\"+sourceName[j]).toString();
                    fileOP.createDir(dest2[j]);
                    //System.out.println(out+dest2);
                    fileOP.copyDir(out[j] , dest2[j]);
                    try {
                        fileOP.delete(dest1[j]);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        fileOP.delete(out[j]);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                //提示
                JOptionPane.showMessageDialog(jFrame,"success","提示",JOptionPane.PLAIN_MESSAGE);

            }
        });

        //添加文本框
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e){
                JTextField newTextField = new JTextField(28);
                jPanelcentre.add(newTextField);
                fieldSet.add(newTextField);
                jFrame.revalidate();;
                System.out.println("success");
            }

        });

        //通过pack()方法设置最佳大小
        jFrame.pack();
        //设置Frame的位置和大小
        jFrame.setBounds(400, 200, 500, 300);
        //设置Frame可见
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new gui().init();
    }
}