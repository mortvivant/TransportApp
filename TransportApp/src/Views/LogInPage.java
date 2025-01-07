package Views;

import Controllers.TicketController;
import Controllers.UserController;
import Models.Admin;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPage extends JFrame {
    private JPanel panel = new JPanel();
    private JLabel label1 = new JLabel("Username:");
    private JLabel label2 = new JLabel("Password:");
    private static JTextField field1 = new JTextField();
    private static JPasswordField field2 = new JPasswordField();
    private JButton button1 = new JButton("Sign up");
    private JButton button2 = new JButton("Log in");
    private JLabel label3 = new JLabel("You don't have account?");
    private JButton button3 = new JButton("Admin Log in");
    private int counter = 3;

    public LogInPage(){
        UserController.showUsers();
        label1.setBounds(180,100,150,20);
        label2.setBounds(180,140,150,20);
        label3.setBounds(180,190,150,20);
        field1.setBounds(250,100,150,20);
        field2.setBounds(250,140,150,20);
        button1.setBounds(180,220,80,20);
        button2.setBounds(320,220,80,20);
        button3.setBounds(220,500,150,20);

        TitledBorder titled = new TitledBorder("Welcome to Transport App");
        panel.setBorder(titled);
        panel.setLayout(null);
        panel.setBounds(0,0,585,560);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterPage register = new RegisterPage();
                close();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!field1.getText().isEmpty() && !getPassword().isEmpty()){
                    if(UserController.logIn()){
                        HomePage home = new HomePage();
                        TicketController.getAllTableNames();
                        TicketController.createReportsTable(LogInPage.getUsername());
                        close();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Log in was unsuccessful");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Fields cannot be empty.");
                }

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = JOptionPane.showInputDialog("Enter the password");

                if (counter != 1) {
                    if (Integer.parseInt(question) == Admin.password) {
                        close();
                        AdminPage page = new AdminPage();
                        JOptionPane.showMessageDialog(null,"Admin logged successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "You enter wrong password", "Warning", JOptionPane.WARNING_MESSAGE);
                        counter--;
                    }
                }
                else {
                    button3.setEnabled(false);
                    JOptionPane.showMessageDialog(null,"You banned from Admin log in");
                }
            }
        });

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(field1);
        panel.add(field2);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);

        add(panel);
        setBounds(450,100,600,600);
        setTitle("Log Page");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static String getUsername(){
        return field1.getText();
    }

    public static String getPassword(){
        String password = String.valueOf(field2.getPassword());
        return password;
    }

    public void run(){
        setVisible(true);
    }

    public void close(){
        dispose();
    }
}
