package Views;

import Controllers.UserController;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoPage extends JFrame {
    private JButton button1 = new JButton("<- Back");
    private JButton button2 = new JButton("Edit");
    private JPanel panel = new JPanel();
    private JLabel label1 = new JLabel("Username:");
    private JLabel label2 = new JLabel("Name:");
    private JLabel label3 = new JLabel("Surname:");
    private JLabel label4 = new JLabel("Gender:");
    private JLabel label5 = new JLabel("Phone number:");
    private JLabel label6 = new JLabel("Password");
    private static JTextField field1 = new JTextField();
    private static JTextField field2 = new JTextField();
    private static JTextField field3 = new JTextField();
    private static JTextField field4 = new JTextField();
    private static JTextField field5 = new JTextField();
    private static JTextField field6 = new JTextField();
    private JButton apply = new JButton("Update");

    public UserInfoPage() {
        button1.setBounds(10,560,100,30);
        button2.setBounds(500,560,100,30);
        apply.setBounds(380,560,100,30);
        label1.setBounds(20,50,150,30);
        field1.setBounds(120,60,150,20);
        label2.setBounds(20,90,150,30);
        field2.setBounds(120,95,150,20);
        label3.setBounds(20,130,150,30);
        field3.setBounds(120,135,150,20);
        label4.setBounds(20,170,150,30);
        field4.setBounds(120,175,150,20);
        label5.setBounds(20,210,150,30);
        field5.setBounds(120,215,150,20);
        label6.setBounds(20,250,150,30);
        field6.setBounds(120,255,150,20);
        UserController.showInfo();
        field1.setBackground(Color.YELLOW);
        field2.setBackground(Color.YELLOW);
        field3.setBackground(Color.YELLOW);
        field4.setBackground(Color.YELLOW);
        field5.setBackground(Color.YELLOW);
        field6.setBackground(Color.YELLOW);
        field1.setEnabled(false);
        field2.setEnabled(false);
        field3.setEnabled(false);
        field4.setEnabled(false);
        field5.setEnabled(false);
        field6.setEnabled(false);


        TitledBorder border = new TitledBorder("User Information");
        panel.setBorder(border);
        panel.setLayout(null);
        panel.setBounds(0,0,630,610);
        panel.add(button1);
        panel.add(button2);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(field1);
        panel.add(field2);
        panel.add(field3);
        panel.add(field4);
        panel.add(field5);
        panel.add(field6);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePage page = new HomePage();
                dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.add(apply);
                field2.setEnabled(true);
                field3.setEnabled(true);
                field4.setEnabled(true);
                field5.setEnabled(true);
                field6.setEnabled(true);
            }
        });

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController.update();
                JOptionPane.showMessageDialog(null,"Your Information has been updated.");
            }
        });

        add(panel);
        setBounds(450,100,650,650);
        setTitle("User Info Page");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void setField1(String d) {
        UserInfoPage.field1.setText(d);
    }

    public static void setField2(String d) {
        UserInfoPage.field2.setText(d);
    }

    public static void setField3(String d) {
        UserInfoPage.field3.setText(d);
    }

    public static void setField4(String d) {
        UserInfoPage.field4.setText(d);
    }

    public static void setField5(int d) {
        UserInfoPage.field5.setText(String.valueOf(d));
    }

    public static void setField6(String d) {
        UserInfoPage.field6.setText(d);
    }

    public static String getField6() {
        return field6.getText();
    }

    public static String getField5() {
        return field5.getText();
    }

    public static String getField4() {
        return field4.getText();
    }

    public static String getField1() {
        return field1.getText();
    }

    public static String getField3() {
        return field3.getText();
    }

    public static String getField2() {
        return field2.getText();
    }
}
