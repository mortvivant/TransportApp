package Views;

import Controllers.TicketController;
import Models.Ticket;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketsPage extends JFrame {
    public static DefaultTableModel model = new DefaultTableModel(new String[] {"Code","Type","Origin","Destination","Time","Date","Passengers"},0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public static JTable table = new JTable(model);
    private JScrollPane pane = new JScrollPane(table);
    private JPanel panel = new JPanel();
    private JButton button1 = new JButton("<- Back");
    private JButton button2 = new JButton("Refund");
    private static Map<String, List<Ticket>> depo = new HashMap<>();

    public TicketsPage(){
        pane.setBounds(10,20,610,550);
        TitledBorder border = new TitledBorder("My Tickets");
        panel.setBorder(border);
        panel.setLayout(null);
        panel.setBounds(0,0,630,630);
        panel.add(pane);
        panel.add(button1);
        panel.add(button2);

        button1.setBounds(10,580,100,30);
        button2.setBounds(520,580,100,30);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage page = new HomePage();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()!=-1){
                    TicketController.refundTicket((int)table.getValueAt(table.getSelectedRow(),6));
                    TicketController.deleteMyTickets(LogInPage.getUsername(), table.getSelectedRow());
                    model.removeRow(table.getSelectedRow());
                }

            }
        });

        add(panel);
        setBounds(450,100,650,650);
        setTitle("Tickets Page");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static String getCode(){
        return (String) model.getValueAt(table.getSelectedRow(),0);
    }
}
