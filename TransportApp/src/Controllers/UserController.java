package Controllers;

import Models.User;
import Views.*;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    private static final String path = "jdbc:sqlite:./databases/Info.db";

    public static void connect(){
        try {
            var conn = DriverManager.getConnection(path);
            JOptionPane.showMessageDialog(null,"Connection to SQLite has been established.");

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error connecting to database" + e.getMessage());
        }
    }

    public static boolean logIn(){
        var sql = "SELECT * FROM User WHERE username = ? AND password = ?";
        String d1 = LogInPage.getUsername();
        String d2 = LogInPage.getPassword();
        boolean access = false;

        try (var conn = DriverManager.getConnection(path)) {
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, d1);
            stmt.setString(2, d2);
            var rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Username and password are valid");
                access = true;
            }
            else {
                System.out.println("Username or password is invalid");
            }
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
        }
        return access;
    }

    public static void insert(User user){
            String sql = "INSERT INTO User(name,surname,username,gender,phoneNumber,password) VALUES(?,?,?,?,?,?)";

        try (var conn = DriverManager.getConnection(path);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4,user.getGender());
            pstmt.setInt(5,user.getPhoneNumber());
            pstmt.setString(6, user.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void signUp(){
        String name = RegisterPage.getNameField();
        String surname = RegisterPage.getSurnameField();
        String username = RegisterPage.getUsernameField();
        String gender = RegisterPage.getGenderField();
        String phoneNumber = RegisterPage.getPhoneNumberField();
        String password = RegisterPage.getPasswordField();

        if(!name.isEmpty() && !surname.isEmpty() && !username.isEmpty() && !gender.isEmpty() && !phoneNumber.isEmpty() && !password.isEmpty()){
            User user = new User(name,surname,username,gender,Integer.parseInt(phoneNumber),password);
            UserController.insert(user);
        }
        else {
            JOptionPane.showMessageDialog(null,"All fields must be filled");
        }
    }

    public static void showInfo(){
        String sql = "SELECT * FROM User where username = ?";
        String d1 = LogInPage.getUsername();
        try(var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,d1);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                UserInfoPage.setField1(rs.getString("username"));
                UserInfoPage.setField2(rs.getString("name"));
                UserInfoPage.setField3(rs.getString("surname"));
                UserInfoPage.setField4(rs.getString("gender"));
                UserInfoPage.setField5(rs.getInt("phoneNumber"));
                UserInfoPage.setField6(rs.getString("password"));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void delete(int row){
        String d1 = (String) AdminPage.model.getValueAt(row,2);
        String sql = "Delete From User WHERE username = ?";

        try (var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,d1);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void showUsers(){
        String sql = "Select * FROM User";

        try(var conn = DriverManager.getConnection(path)){
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            AdminPage.model.setRowCount(0);

            while (rs.next()){
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String username = rs.getString(4);
                String gender = rs.getString(5);
                String phoneNumber = rs.getString(6);
                String password = rs.getString(7);
                User u = new User(name,surname,username,gender,Integer.parseInt(phoneNumber),password);
                AdminPage.model.addRow(new Object[]{name,surname,username,gender,phoneNumber,password});
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void update(){
        String sql = "Update User SET name = ? , surname = ? , gender = ? , phoneNumber = ?, password = ? Where username = ?";
        try(var conn = DriverManager.getConnection(path)) {
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,UserInfoPage.getField2());
            pstmt.setString(2,UserInfoPage.getField3());
            pstmt.setString(3,UserInfoPage.getField4());
            pstmt.setString(4,UserInfoPage.getField5());
            pstmt.setString(5,UserInfoPage.getField6());
            pstmt.setString(6,UserInfoPage.getField1());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void insertToBlackList(User user){
        String sql = "INSERT INTO blacklist(name,surname,username,gender,phoneNumber,password) VALUES(?,?,?,?,?,?)";

        try (var conn = DriverManager.getConnection(path);
             var pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,user.getName());
            pstmt.setString(2, user.getSurname());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4,user.getGender());
            pstmt.setInt(5,user.getPhoneNumber());
            pstmt.setString(6, user.getPassword());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void deleteFromBlackList(int row){
        String d1 = (String) BlackListPage.model.getValueAt(row,2);
        String sql = "Delete From blacklist WHERE username = ?";

        try (var conn = DriverManager.getConnection(path)){
            var pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,d1);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public static void showBlackList(){
        String sql = "Select * FROM blacklist";

        try(var conn = DriverManager.getConnection(path)){
            var stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            BlackListPage.model.setRowCount(0);

            while (rs.next()){
                String name = rs.getString(1);
                String surname = rs.getString(2);
                String username = rs.getString(3);
                String gender = rs.getString(4);
                String phoneNumber = rs.getString(5);
                String password = rs.getString(6);
                BlackListPage.model.addRow(new Object[]{name,surname,username,gender,phoneNumber,password});
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}

