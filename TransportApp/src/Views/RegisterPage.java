package Views;

import Controllers.UserController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel label1 = new JLabel("Enter your name:");
    private JLabel label2 = new JLabel("Enter your surname:");
    private JLabel label3 = new JLabel("Enter your username:");
    private JLabel label4 = new JLabel("Select your gender:");
    private JLabel label5 = new JLabel("Enter your phone number:");
    private JLabel label6 = new JLabel("Create your password:");
    private JLabel label7 = new JLabel("Enter your password again:");
    private static JTextField text1 = new JTextField();
    private static JTextField text2 = new JTextField();
    private static JTextField text3 = new JTextField();
    private static JRadioButton male = new JRadioButton("Male");
    private static JRadioButton female = new JRadioButton("Female");
    private ButtonGroup group = new ButtonGroup();
    private static JTextField text4 = new JTextField();
    private static JTextField text5 = new JTextField();
    private static JTextField text6 = new JTextField();
    private JButton button1 = new JButton("<- Back");
    private JButton button2 = new JButton("Sign up ->");

    public RegisterPage(){
        label1.setBounds(20,50,150,20);
        label2.setBounds(20,100,150,20);
        label3.setBounds(20,150,150,20);
        label4.setBounds(20,200,150,20);
        label5.setBounds(20,250,150,20);
        label6.setBounds(20,300,150,20);
        label7.setBounds(10,350,170,20);
        text1.setBounds(180,50,150,20);
        text2.setBounds(180,100,150,20);
        text3.setBounds(180,150,150,20);
        male.setBounds(180,200,70,20);
        female.setBounds(250,200,70,20);
        group.add(male);
        group.add(female);
        text4.setBounds(180,250,150,20);
        text5.setBounds(180,300,150,20);
        text6.setBounds(180,350,150,20);
        button1.setBounds(20,500,100,20);
        button2.setBounds(450,500,100,20);

        TitledBorder titled = new TitledBorder("Information of User");
        panel.setLayout(null);
        panel.setBorder(titled);
        panel.setBounds(0,0,580,560);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInPage log = new LogInPage();
                log.run();
                dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserController.signUp();
                LogInPage page = new LogInPage();
                dispose();
            }
        });

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(text1);
        panel.add(text2);
        panel.add(text3);
        panel.add(male);
        panel.add(female);
        panel.add(text4);
        panel.add(text5);
        panel.add(text6);
        panel.add(button1);
        panel.add(button2);

        add(panel);
        setBounds(450,100,600,600);
        setTitle("Register Panel");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static String getNameField(){
        return text1.getText();
    }

    public static String getSurnameField(){
        return text2.getText();
    }

    public static String getUsernameField(){
        return text3.getText();
    }

    public static String getGenderField(){
        String gender;
        if (male.isSelected()){
            gender = male.getText();
        }
        else {
            gender = female.getText();
        }
        return gender;
    }

    public static String getPhoneNumberField(){
        return text4.getText();
    }

    public static String getPasswordField(){
        if (text6.getText().equalsIgnoreCase(text5.getText())){
            return text5.getText();
        }
        else {
            JOptionPane.showMessageDialog(null,"Both passwords must be same.");
            return null;
        }
    }
}
