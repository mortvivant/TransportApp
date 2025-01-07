package Views;

import Controllers.UserController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackListPage extends JFrame {

    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Name","Surname","Username","Gender","PhoneNumber","Password"},0);
    private JTable table = new JTable(model);
    private JScrollPane pane = new JScrollPane(table);
    private JPanel panel = new JPanel();
    private JButton button1 = new JButton("<- Back");
    private JButton button2 = new JButton("Delete User");

    public BlackListPage() {
        button1.setBounds(10,580,100,30);
        button2.setBounds(510,580,110,30);

        pane.setBounds(10,20,610,550);
        TitledBorder border = new TitledBorder("Black List");
        panel.setBorder(border);
        panel.setLayout(null);
        panel.setBounds(0,0,630,630);
        panel.add(pane);
        panel.add(button1);
        panel.add(button2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminPage page = new AdminPage();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() != -1){
                    UserController.deleteFromBlackList(table.getSelectedRow());
                    UserController.showBlackList();
                }
            }
        });

        add(panel);
        setBounds(450,100,650,650);
        setTitle("Black List");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
