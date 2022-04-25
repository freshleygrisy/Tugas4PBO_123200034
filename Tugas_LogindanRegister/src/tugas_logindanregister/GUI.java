package tugas_logindanregister;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame{
    JLabel LabUsername = new JLabel("Username : ");
    JTextField FldUsername = new JTextField (20);
    JLabel LabPass = new JLabel ("Password : ");
    JPasswordField FldPass = new JPasswordField(10);
    JButton ButLogin = new JButton("Login");
    JLabel LabReg = new JLabel ("Username : ");
    JTextField FldReg = new JTextField (20);
    JLabel LabRPass = new JLabel ("Password : ");
    JPasswordField FldRPass = new JPasswordField (10);
    JButton ButReg = new JButton ("Register");
    
    public GUI(){
        setVisible(true);
        setTitle("");
        setSize(600,230);
        setLayout(null);
        
        add(LabUsername);
        add(FldUsername);
        add(LabPass);
        add(FldPass);
        add(ButLogin);
        
        add(LabReg);
        add(FldReg);
        add(LabRPass);
        add(FldRPass);
        add(ButReg);
        
        LabUsername.setBounds(30, 40, 100,30);
        FldUsername.setBounds(120,40,150,30);
        LabPass.setBounds(30,80,100,30);
        FldPass.setBounds(120,80,150,30);
        ButLogin.setBounds(120,150,90,20);
        
        LabReg.setBounds(300,40,100,30);
        FldReg.setBounds(390,40,150,30);
        LabRPass.setBounds(300,80,100,30);
        FldRPass.setBounds(390,80,150,30);
        ButReg.setBounds(390,150,90,20);
        
        ButLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Connector conn = new Connector();
                String user = FldUsername.getText();
                System.out.println(user);
                if (conn.checkUsername(user) && user != "" && conn.checkLogin(user, String.valueOf(FldPass.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                }
                else if (user.isEmpty() || String.valueOf(FldPass.getPassword()).isEmpty()) { //error handling
                    JOptionPane.showMessageDialog(null, "Please fill username/password!");
                }
                else if (!conn.checkUsername(user)) {
                    JOptionPane.showMessageDialog(null, "Username not available!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"Incorrect Password!");
                }
                
            }
            
        });
        ButReg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Connector conn = new Connector();
                String reguser = FldReg.getText();
                String regpass = String.valueOf(FldRPass.getPassword());
                if (!reguser.isEmpty() && !regpass.isEmpty()) {
                    conn.masukData(reguser,regpass);
                    JOptionPane.showMessageDialog(null, "Register Successful!");
                }
                else if (reguser.isEmpty() || regpass.isEmpty()) { //error handling
                    JOptionPane.showMessageDialog(null, "Please fill username/password!");
                }
            }
        });
    }
    
}
