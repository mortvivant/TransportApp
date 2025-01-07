package Views;

import Controllers.ReportController;
import Controllers.TicketController;
import Models.Ticket;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends JFrame {

    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JButton button1 = new JButton("Airline");
    private JButton button2 = new JButton("Bus");
    private JButton button3 = new JButton("Train");
    private JLabel label = new JLabel("Type");
    private JLabel label1 = new JLabel("Origin");
    private JLabel label2 = new JLabel("Destination");
    private JLabel label3 = new JLabel("Date");
    private JLabel label4 = new JLabel("Passengers");
    private JButton searchButton = new JButton("Search");
    private static String[] citiesWithAirports = {
            "Adana", "Adıyaman", "Ağrı", "Amasya", "Ankara", "Antalya",
            "Balıkesir", "Bingöl", "Bitlis", "Bursa", "Çanakkale", "Denizli",
            "Diyarbakır", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Hakkari", "Hatay", "Iğdır", "Isparta", "İstanbul",
            "İzmir", "Kahramanmaraş", "Kars", "Kastamonu", "Kayseri",
            "Kocaeli", "Konya", "Malatya", "Mardin", "Mersin", "Muğla",
            "Muş", "Nevşehir", "Ordu", "Rize", "Samsun", "Siirt", "Sinop",
            "Sivas", "Şanlıurfa", "Şırnak", "Tekirdağ", "Tokat", "Trabzon",
            "Uşak", "Van", "Yozgat", "Zonguldak"
    };
    private static JComboBox<String> citiesOrigin = new JComboBox<>(citiesWithAirports);
    private static JComboBox<String> citiesDestination = new JComboBox<>(citiesWithAirports);
    private SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(1,1,3,1);
    private JSpinner spinner = new JSpinner(spinnerNumberModel);
    public static DefaultTableModel model = new DefaultTableModel(new String[]{"Code","Type","Origin","Destination","Time","Date","Price","Number of Ticket"},0);
    private static JTable table = new JTable(model);
    private JScrollPane pane = new JScrollPane(table);
    private JButton exit = new JButton("Exit");
    private JButton info = new JButton("User Information");
    private JButton tickets = new JButton("My Tickets");
    private ImageIcon icon = new ImageIcon("./images/istockphoto-831626846-612x612.jpg");
    private Image img = icon.getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
    private JButton search = new JButton(new ImageIcon(img));
    private static JLabel label5 = new JLabel();
    private JButton buy = new JButton("Buy Ticket");
    private static JSpinner spinnerDate = new JSpinner(new SpinnerDateModel());

    public HomePage(){
        TicketController.showMyTickets(LogInPage.getUsername());
        button1.setBounds(100,50,120,20);
        button2.setBounds(270,50,120,20);
        button3.setBounds(440,50,120,20);
        label.setBounds(30,100,120,30);
        label1.setBounds(130,100,120,30);
        label2.setBounds(250,100,120,30);
        label3.setBounds(410,100,120,30);
        label4.setBounds(510,100,120,30);
        label5.setBounds(30,140,150,30);
        citiesOrigin.setBounds(100,150,100,20);
        citiesDestination.setBounds(230,150,100,20);
        spinner.setBounds(530,150,50,20);
        search.setBounds(600,110,60,60);
        pane.setBounds(30,190,600,400);
        buy.setBounds(550,610,100,30);
        spinnerDate.setBounds(400,150,100,20);
        spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate,"MM/dd/yyyy"));

        TitledBorder titled2 = new TitledBorder("Tickets");
        panel2.setBorder(titled2);
        panel2.setBounds(200,0,685,660);
        panel2.setLayout(null);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(label);
        panel2.add(label1);
        panel2.add(label2);
        panel2.add(label3);
        panel2.add(label4);
        panel2.add(citiesOrigin);
        panel2.add(citiesDestination);
        panel2.add(spinner);
        panel2.add(search);
        panel2.add(label5);
        panel2.add(buy);
        panel2.add(spinnerDate);

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
                label5.setText(button1.getText());
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label5.setText(button2.getText());
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label5.setText(button3.getText());
            }
        });

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicketController.searchTicket();
                panel2.add(pane);
            }
        });

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(row!=-1) {
                    String code = (String) HomePage.model.getValueAt(row,0);
                    String type = (String) HomePage.model.getValueAt(row, 1);
                    String origin = (String) HomePage.model.getValueAt(row, 2);
                    String destination = (String) (HomePage.model.getValueAt(row, 3));
                    String time = (String) HomePage.model.getValueAt(row, 4);
                    String date = (String) HomePage.model.getValueAt(row, 5);
                    int price = (int) HomePage.model.getValueAt(row,6);
                    int passengers = (int) spinner.getValue();
                    Ticket ticket = new Ticket(code,type,origin,destination,time,date,price,passengers);
                    TicketController.insertMyTicket(LogInPage.getUsername(),ticket);
                    TicketController.buyTicket(passengers);
                    JOptionPane.showMessageDialog(null,"Ticket was bought.");
                    HomePage.model.setRowCount(0);
                }
                else {
                    JOptionPane.showMessageDialog(null,"You have to choose a row.");
                }
            }
        });
        //Tickets Panel

        tickets.setBounds(10,50,180,20);
        info.setBounds(10,80,180,20);
        exit.setBounds(10,110,180,20);
        TitledBorder titled1 = new TitledBorder("My Account");
        panel1.setLayout(null);
        panel1.setBorder(titled1);
        panel1.setBounds(0,0,200,660);
        panel1.add(tickets);
        panel1.add(info);
        panel1.add(exit);

        tickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TicketsPage page = new TicketsPage();
                dispose();
            }
        });

        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserInfoPage page = new UserInfoPage();
                dispose();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInPage page = new LogInPage();
                dispose();
                ReportController.exportTickets();
                TicketsPage.model.setRowCount(0);
            }
        });

        add(panel1);
        add(panel2);
        setTitle("Home Page");
        setBounds(350,100,900,700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static String getLabel5() {
        return label5.getText();
    }

    public static String getOrigin(){
        return (String) citiesOrigin.getSelectedItem();
    }

    public static String getDestination(){
        return (String) citiesDestination.getSelectedItem();
    }

    public static String getCode(){
        return (String) model.getValueAt(table.getSelectedRow(),0);
    }

    public static String getDate(){
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        Date returnDate = (Date) spinnerDate.getValue();
        String returnDateStr = dateFormat1.format(returnDate);
        return returnDateStr;
    }

    public Date getCurrentDate(){
        return new Date(System.currentTimeMillis());
    }
}
