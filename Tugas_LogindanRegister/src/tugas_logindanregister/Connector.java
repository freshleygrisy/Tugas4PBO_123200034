package tugas_logindanregister;
import java.sql.*;
import javax.swing.JOptionPane;

public class Connector {
    String DBurl = "jdbc:mysql://localhost/tugasjdbc";
    String DBusername = "root";
    String DBpassword = "";
    
    String data[] = new String[2];
    Connection conn;
    Statement statement;
    static String[] username;
    public Connector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
        }catch(Exception ex){
            System.out.println("Connection Failed " + ex.getMessage() );
        }
    }
    
    void masukData (String username, String password){
        try {
            if (!checkUsername(username)) {
                String query = "INSERT INTO `users` (`username`, `password`)" 
                        + "VALUES('" + username + "','" + password + "')";
                statement = conn.createStatement();
                statement.executeUpdate(query);
                System.out.println("Input Succesful!");
                JOptionPane.showMessageDialog(null, "Register Success, please login!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Username already exists, please change it!");
            }
        }catch (Exception ex) {
            System.out.println("Failed Input");
        }
    }
    
    boolean checkUsername (String username){
        try {
            String query = "SELECT * FROM `users` WHERE username = '"+username+"'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while(resultSet.next()){
                data[0] = resultSet.getString("username");
            }
            statement.close();
            data[0].toString();
            return true;
        }catch (Exception e){
            System.out.println("Not available");
            return false;
        }
    }
    
    
    boolean checkLogin (String username, String password){
        try {
            String query = "SELECT * FROM `users` WHERE username='"+username+"'";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[0] = resultSet.getString("username");
                data[1] = resultSet.getString("password");
            }
            statement.close();
            System.out.println(data[1].toString());
            System.out.println(password);
            if (data[1].toString().equals(password)) {
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            System.out.println("Not Available!");
            return false;
        }
    }
    
    
    
}
