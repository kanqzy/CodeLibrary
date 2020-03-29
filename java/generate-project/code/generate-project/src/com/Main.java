package com;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 执行入口
 * 
 * @author kanq
 */
public class Main {
    public static final String execPath = getJarFilePath();
    public static void main(String[] args) throws Exception {
        inputConfigPath(new Action() {
            @Override
            public void excute(String configPath) {
                if (configPath == null || !new File(configPath).exists()) {
                    System.out.println("config path illegal");
                    return;
                }
                System.out.println("configPath:" + configPath);
                try {
                    String configStr = FileUtil.read(configPath);
                    Config config = Config.from(configStr);
                    new ProjectMaker(config).make();
                } catch (Exception e) {
                    System.out.println("action err");
                    e.printStackTrace();
                }

            }
        });
    }

    private static void inputConfigPath(Action action) {
        // 显示屏宽高
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screentHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        // 窗体宽高
        int width = 600;
        int height = 400;

        JFrame frame = new JFrame("输入配置文件路径");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setBounds((screenWidth - width) / 2, (screentHeight - height) / 2, width, height);

        JLabel label = new JLabel("路径:");
        JTextField textField = new JTextField(16);
        JButton button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String configPath = textField.getText();
                action.excute(configPath);
            }
        });

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(label);
        contentPane.add(textField);
        contentPane.add(button);
        frame.setVisible(true);
    }

    /**
	 * 获取执行jar或class文件绝对路径
	 * 
	 * @return
	 */
	private static String getJarFilePath() {
		String filePath = "";
		try {
			filePath = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			filePath = java.net.URLDecoder.decode(filePath, "UTF-8");
			filePath = filePath.substring(0, filePath.lastIndexOf("/"));
		} catch (Exception e) {
			System.out.println("getJarFilePath err :" + e.getMessage());
			e.printStackTrace();
		}
		return filePath;
	}
   
}