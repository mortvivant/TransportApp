package Controllers;

import Models.Ticket;
import Views.ExpeditionPage;
import Views.HomePage;
import Views.TicketsPage;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketController {
    private static final String path = "jdbc:sqlite:./databases/Info.db";

    public static void insert(Ticket ticket) {
        String sql = "INSERT INTO Ticket(code,type,origin,destination,time,date,price,numberOfTicket) VALUES(?,?,?,?,?,?,?,?)";

        try (var conn = DriverManager.getConnection(path);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ticket.getCode());
            pstmt.setString(2, ticket.getType());
            pstmt.setString(3, ticket.getOrigin());
            pstmt.setString(4, ticket.getDestination());
            pstmt.setString(5, ticket.getTime());
            pstmt.setString(6, ticket.getDate());
            pstmt.setString(7, String.valueOf(ticket.getPrice()));
            pstmt.setInt(8,ticket.getNumberOfTicket());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void createExpedition() {
        Ticket.generateCode();
        String code = Ticket.getCode();
        String type = ExpeditionPage.getField1();
        String date = ExpeditionPage.getDate();
        String origin = ExpeditionPage.getField3();
        String destination = ExpeditionPage.getField4();
        int price = Integer.parseInt(ExpeditionPage.getField5());
        String time = ExpeditionPage.getField6();
        int numberOfTicket = Integer.parseInt(ExpeditionPage.getField7());
        Ticket ticket = new Ticket(code,type,origin, destination,time,date,price,numberOfTicket);
        TicketController.insert(ticket);
    }

    public static void showTickets() {
        String sql = "Select * FROM Ticket";

        try (var conn = DriverManager.getConnection(path)) {
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ExpeditionPage.model.setRowCount(0);

            while (rs.next()) {
                String code = rs.getString("code");
                String type = rs.getString("type");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                int price = rs.getInt("price");
                int numberOfTicket = rs.getInt("numberOfTicket");
                ExpeditionPage.model.addRow(new Object[]{code,type,origin, destination,time,date,price,numberOfTicket});
            }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    public static void delete(int row){
        String sql = "Delete FROM Ticket where code = ?";
        String d1 = (String) ExpeditionPage.model.getValueAt(row,0);
        try(var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,d1);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void update(int row){
        String sql = "Update Ticket SET time = ? , price = ? where code = ?";
        String d1 = ExpeditionPage.getField5();
        String d2 = ExpeditionPage.getField6();
        String d3 = (String) ExpeditionPage.model.getValueAt(row,0);
        if(!d1.isEmpty() && !d2.isEmpty()){
            try (var conn = DriverManager.getConnection(path)){
                var pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,d2);
                pstmt.setString(2,d1);
                pstmt.setString(3,d3);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null,"Update successful");
            }
            catch (SQLException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    public static void searchTicket(){
        String sql = "Select * FROM Ticket where type = ? and origin = ? and destination = ? and date = ?";
        String d1 = HomePage.getLabel5();
        String d2 = HomePage.getOrigin();
        String d3 = HomePage.getDestination();
        String d4 = HomePage.getDate();
        try (var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,d1);
            pstmt.setString(2,d2);
            pstmt.setString(3,d3);
            pstmt.setString(4,d4);
            ResultSet rs = pstmt.executeQuery();
            HomePage.model.setRowCount(0);

            while (rs.next()){
                String code = rs.getString("code");
                String type = rs.getString("type");
                String date =  rs.getString("date");
                String time = rs.getString("time");
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                int price = rs.getInt("price");
                int numberOfTickets = rs.getInt("numberOfTicket");
                if(numberOfTickets > 0) {
                    HomePage.model.addRow(new Object[]{code, type, origin, destination, time, date, price, numberOfTickets});
                }
                else {
                    JOptionPane.showMessageDialog(null,"Ticket hasn't been found.");
                }
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void buyTicket(int passenger){

        String sql = "Update Ticket Set numberOfTicket = numberOfTicket - ?  where code = ?";

        try(var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,passenger);
            pstmt.setString(2,HomePage.getCode());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static List<String> getAllTableNames() {
        String query = "SELECT name FROM sqlite_master WHERE type='table'";
        List<String> tableNames = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(path);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                tableNames.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving table names: " + e.getMessage());
        }
        return tableNames;
    }

    public static void createReportsTable(String tableName) {
        List<String> existingTables = getAllTableNames();

        if (existingTables.contains(tableName)) {
            return;
        }

        String sql = "CREATE TABLE "+tableName+" (" +
                "    code INTEGER UNIQUE," +
                "    type TEXT NOT NULL," +
                "    origin TEXT NOT NULL," +
                "   destination TEXT NOT NULL," +
                "   time TEXT NOT NULL," +
                "   date TEXT NOT NULL," +
                "   numberOfTicket TEXT NOT NULL" +
                ");";

        try (Connection conn = DriverManager.getConnection(path);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Table '" + tableName + "' created successfully.");

        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void refundTicket(int passenger){
        String sql = "Update Ticket Set numberOfTicket = numberOfTicket + ? where code = ?";

        try(var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,passenger);
            pstmt.setString(2, TicketsPage.getCode());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void insertMyTicket(String username,Ticket ticket){
        String sql = "INSERT INTO " + username+ "(code,type,origin,destination,time,date,numberOfTicket) VALUES(?,?,?,?,?,?,?)";

        try (var conn = DriverManager.getConnection(path);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, ticket.getCode());
            pstmt.setString(2, ticket.getType());
            pstmt.setString(3, ticket.getOrigin());
            pstmt.setString(4, ticket.getDestination());
            pstmt.setString(5, ticket.getTime());
            pstmt.setString(6, ticket.getDate());
            pstmt.setInt(7,ticket.getNumberOfTicket());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Bilet tabloya geçmedi " + e.getMessage());
        }
    }

    public static void showMyTickets(String username){
        String sql = "Select * FROM " + username;

        try(var conn = DriverManager.getConnection(path)){
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            TicketsPage.model.setRowCount(0);

            while (rs.next()){
                String code = rs.getString("code");
                String type = rs.getString("type");
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                String time = rs.getString("time");
                String date = rs.getString("date");
                int passengers = rs.getInt("numberOfTicket");
                TicketsPage.model.addRow(new Object[]{code,type,origin,destination,time,date,passengers});
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void deleteMyTickets(String username,int row){
        String sql = "Delete FROM "+username+" where code = ?";
        String d1 = (String) TicketsPage.model.getValueAt(row,0);
        try(var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,d1);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}

