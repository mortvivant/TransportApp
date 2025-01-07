package Views;

import Controllers.TicketController;
import Models.Ticket;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpeditionPage extends JFrame {
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Code","Type","Origin","Destination","Time","Date","Price","Number Of Ticket"},0){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JTable table = new JTable(model);
    private JScrollPane pane = new JScrollPane(table);
    private JLabel label1 = new JLabel("Type:");
    private JLabel label2 = new JLabel("Date:");
    private JLabel label3 = new JLabel("Origin:");
    private JLabel label4 = new JLabel("Destination:");
    private JLabel label5 = new JLabel("Price:");
    private JLabel label6 = new JLabel("Time:");
    private JLabel label7 = new JLabel("Number of Ticket:");
    private JButton button1 = new JButton("<- Back");
    private JButton button2 = new JButton("Create");
    private JButton button3 = new JButton("Update");
    private JButton button4 = new JButton("Delete");
    private JButton button5 = new JButton("Edit");
    private static JTextField field1 = new JTextField();
    private static JSpinner spinnerDate = new JSpinner(new SpinnerDateModel());
    private static JTextField field3 = new JTextField();
    private static JTextField field4 = new JTextField();
    private static JTextField field5 = new JTextField();
    private static JTextField field6 = new JTextField();
    private static JTextField field7 = new JTextField();


    public ExpeditionPage(){
        label1.setBounds(20,440,100,20);
        label2.setBounds(20,480,100,20);
        label3.setBounds(20,520,100,20);
        label4.setBounds(250,440,100,20);
        label5.setBounds(250,480,100,20);
        label6.setBounds(250,520,100,20);
        label7.setBounds(200,560,130,20);
        pane.setBounds(10,10,720,400);
        button1.setBounds(10,580,100,30);
        button2.setBounds(600,440,100,20);
        button3.setBounds(600,480,100,20);
        button4.setBounds(600,520,100,20);
        button5.setBounds(600,560,100,20);
        field1.setBounds(70,440,100,20);
        spinnerDate.setBounds(70,480,100,20);
        spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate,"MM/dd/yyyy"));
        field3.setBounds(70,520,100,20);
        field4.setBounds(320,440,100,20);
        field5.setBounds(320,480,100,20);
        field6.setBounds(320,520,100,20);
        field7.setBounds(320,560,100,20);

        button3.setEnabled(false);

        add(pane);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(label1);
        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(field1);
        add(spinnerDate);
        add(field3);
        add(field4);
        add(field5);
        add(field6);
        add(field7);

        spinnerDate.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Date selected =(Date) spinnerDate.getValue();
                if(!selected.after(getCurrentDate())){
                    spinnerDate.setValue(getCurrentDate());
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage page = new AdminPage();
                dispose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = field1.getText();
                String date = getDate();
                String time = field6.getText();
                String origin = field3.getText();
                String destination = field4.getText();
                int price = Integer.parseInt(field5.getText());
                int numberOfTicket = Integer.parseInt(field7.getText());
                if(!type.isEmpty() && !date.isEmpty() && !time.isEmpty() && !origin.isEmpty() && !destination.isEmpty() && price!=0 && numberOfTicket!=0){
                    TicketController.createExpedition();
                    model.addRow(new Object[]{Ticket.getCode(),type,origin,destination,time,date,price,numberOfTicket});
                }
                field1.setText("");
                field3.setText("");
                field4.setText("");
                field5.setText("");
                field6.setText("");
                field7.setText("");
            }
        });


        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();

                if(row!=-1){
                    TicketController.update(row);
                    field6.setText("");
                    field5.setText("");
                    TicketController.showTickets();
                    field1.setEnabled(true);
                    spinnerDate.setEnabled(true);
                    field3.setEnabled(true);
                    field4.setEnabled(true);
                    field7.setEnabled(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"You have to choose a row");
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row!=-1){
                    TicketController.delete(row);
                    model.removeRow(table.getSelectedRow());
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                field1.setEnabled(false);
                spinnerDate.setEnabled(false);
                field3.setEnabled(false);
                field4.setEnabled(false);
                button3.setEnabled(true);
                field7.setEnabled(false);
            }
        });

        setBounds(450,100,750,700);
        setTitle("Expeditions");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static String getField1() {
        return field1.getText();
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

    public static String getField3() {
        return field3.getText();
    }

    public static String getDate(){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        Date returnDate = (Date) spinnerDate.getValue();
        String returnDateStr = dateFormat1.format(returnDate);
        return returnDateStr;
    }

    public static String getField7(){
        return field7.getText();
    }

    public Date getCurrentDate(){
        return new Date(System.currentTimeMillis());
    }
}
