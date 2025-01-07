package Views;

import Controllers.TicketController;
import Controllers.UserController;
import Models.User;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    private JPanel panel = new JPanel();
    private JButton button1 = new JButton("Black List");
    private JButton button3 = new JButton("Show Expedition");
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Name","Surname","Username","Gender","PhoneNumber","Password"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable table = new JTable(model);
    private JScrollPane pane = new JScrollPane(table);
    private JButton button4 = new JButton("Add User");
    private JButton button5 = new JButton("Delete User");
    private JButton button6 = new JButton("Add Black List");
    private JTextField field1 = new JTextField();
    private JLabel label1 = new JLabel("Name:");
    private JTextField field2 = new JTextField();
    private JLabel label2 = new JLabel("Surname:");
    private JTextField field3 = new JTextField();
    private JLabel label3 = new JLabel("Username:");
    private JRadioButton male = new JRadioButton("Male");
    private JRadioButton female = new JRadioButton("Female");
    private ButtonGroup group = new ButtonGroup();
    private JLabel label4 = new JLabel("Gender:");
    private JTextField field5 = new JTextField();
    private JLabel label5 = new JLabel("PhoneNumber:");
    private JTextField field6 = new JTextField();
    private JLabel label6 = new JLabel("Password:");
    private JButton button7 = new JButton("<- Back");

    public AdminPage(){
        button1.setBounds(70,50,150,30);
        button3.setBounds(470,50,150,30);
        button4.setBounds(520,510,150,20);
        button5.setBounds(520,540,150,20);
        button6.setBounds(520,570,150,20);
        button7.setBounds(20,635,100,20);
        field1.setBounds(90,510,150,20);
        label1.setBounds(20,510,80,20);
        field2.setBounds(90,550,150,20);
        label2.setBounds(20,550,100,20);
        field3.setBounds(90,590,150,20);
        label3.setBounds(20,590,100,20);
        male.setBounds(300,510,70,20);
        female.setBounds(380,510,80,20);
        group.add(male);
        group.add(female);
        label4.setBounds(250,510,100,20);
        field5.setBounds(340,550,150,20);
        label5.setBounds(250,550,150,20);
        field6.setBounds(340,590,150,20);
        label6.setBounds(250,590,150,20);
        pane.setBounds(20,100,650,400);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TitledBorder border = new TitledBorder("Admin");
        panel.setBorder(border);
        panel.setLayout(null);
        panel.setBounds(0,0,685,660);
        panel.add(button1);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);
        panel.add(field1);
        panel.add(field2);
        panel.add(field3);
        panel.add(male);
        panel.add(female);
        panel.add(field5);
        panel.add(field6);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(pane);

        UserController.showUsers();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlackListPage page = new BlackListPage();
                UserController.showBlackList();
                dispose();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicketController.showTickets();
                ExpeditionPage page = new ExpeditionPage();
                dispose();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = field1.getText();
                String surname = field2.getText();
                String username = field3.getText();
                String gender;
                int phoneNumber = Integer.parseInt(field5.getText());
                if (male.isSelected()){
                    gender = male.getText();
                }
                else {
                    gender = female.getText();
                }
                String password = field6.getText();
                if(!name.isEmpty() && !surname.isEmpty() && !username.isEmpty() && phoneNumber!=0) {
                    User user = new User(name,surname,username,gender,phoneNumber,password);
                    UserController.insert(user);
                    UserController.showUsers();
                }
                else {
                    JOptionPane.showMessageDialog(null,"Fields cannot be empty.");
                }
                field1.setText("");
                field2.setText("");
                field3.setText("");
                group.clearSelection();
                field5.setText("");
                field6.setText("");
            }
        });

        button5.addActionListener(new ActionListener() {//
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(table.getSelectedRow()!=-1){
                    UserController.delete(row);
                    model.removeRow(row);
                    UserController.showUsers();
                }
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                String name = (String) model.getValueAt(row,0);
                String surname = (String) model.getValueAt(row,1);
                String username = (String) model.getValueAt(row,2);
                String gender = (String) model.getValueAt(row,3);
                String phoneNumber = String.valueOf(model.getValueAt(row,4));
                String password = (String) model.getValueAt(row,5);
                User user = new User(name,surname,username,gender,Integer.parseInt(phoneNumber),password);
                UserController.insertToBlackList(user);
                JOptionPane.showMessageDialog(null,"User added to Black List");
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInPage page = new LogInPage();
                dispose();
            }
        });
        add(panel);
        setBounds(450,100,700,700);
        setTitle("Admin Page");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
