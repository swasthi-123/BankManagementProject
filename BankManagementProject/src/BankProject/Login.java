package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel headingText,inputLabel,passwordLabel;
    JTextField inputText;
    JPasswordField passwordInput;
    JButton loginBtn,clearBtn,signupBtn,exitBtn,adminLoginBtn;
    String AccountNumber;
    String AccountPinNumber;
    String database;
    Login(String database){
        this.database  = database;
        setFont(new Font("System", Font.BOLD, 28));
        setTitle("BANKING ACCOUNT MANAGEMENT SOFTWARE");

        headingText = new JLabel("WELCOME TO THE BANK");
        headingText.setFont(new Font("Osward", Font.BOLD, 38));

        inputLabel = new JLabel("Account No:");
        inputLabel.setFont(new Font("Raleway", Font.BOLD, 28));

        passwordLabel = new JLabel("PIN:");
        passwordLabel.setFont(new Font("Raleway", Font.BOLD, 28));

        inputText = new JTextField(15);
        inputText.setFont(new Font("Arial", Font.BOLD, 14));

        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                if(Character.isAlphabetic(ch)){
                    JOptionPane.showMessageDialog(null,"Only numbers are allowed");
                    inputText.setText(inputText.getText().substring(0,inputText.getText().length()-1));
                }
            }
        });

        passwordInput = new JPasswordField(15);
        passwordInput.setFont(new Font("Arial", Font.BOLD, 14));

        loginBtn = new JButton("SIGN IN");
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);

        clearBtn = new JButton("CLEAR");
        clearBtn.setBackground(Color.BLACK);
        clearBtn.setForeground(Color.WHITE);

        signupBtn = new JButton("SIGN UP");
        signupBtn.setBackground(Color.BLACK);
        signupBtn.setForeground(Color.WHITE);

        adminLoginBtn = new JButton("EMPLOYEE");
        adminLoginBtn.setBackground(Color.BLACK);
        adminLoginBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);

        setLayout(null);

        headingText.setBounds(160,50,600,200);
        add(headingText);

        inputLabel.setBounds(125,150,375,200);
        add(inputLabel);

        inputText.setBounds(350,235,230,30);
        add(inputText);

        passwordLabel.setBounds(150,225,375,200);
        add(passwordLabel);

        passwordInput.setBounds(350,310,230,30);
        add(passwordInput);

        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(350,400,100,30);
        add(loginBtn);

        clearBtn.setFont(new Font("Arial", Font.BOLD, 14));
        clearBtn.setBounds(480,400,100,30);
        add(clearBtn);

        signupBtn.setFont(new Font("Arial", Font.BOLD, 14));
        signupBtn.setBounds(350,450,230,30);
        add(signupBtn);

        adminLoginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        adminLoginBtn.setBounds(350,500,230,30);
        add(adminLoginBtn);

        exitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        exitBtn.setBounds(350,550,230,30);
        add(exitBtn);



        loginBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        signupBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        adminLoginBtn.addActionListener(this);

        setSize(800,800);
        setLocation(500,200);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae){

        try{
            AccountNumber  = inputText.getText();
            AccountPinNumber  = String.valueOf(passwordInput.getPassword());

            if(ae.getSource()==loginBtn) {
                if (AccountNumber.equals(""))
                    throw new EmptyInputException("Invalid Account Number");
                Conn conn = new Conn(database);
                String query = "select * from Accounts where AccNo = '" + AccountNumber + "' and pinNo = '" + AccountPinNumber + "'";
                conn.setQuery(query);
                ResultSet solution = conn.executeStatement();
                int solutionCount = 0;
                if (conn.querySuccessState) {
                    while (solution.next()) {
                        solutionCount++;
                        }
                    if(solutionCount==1)
                    {
                        String pin = Utils.getPin(AccountNumber,database);
                        if(pin.equals(AccountPinNumber)) {
                            setVisible(false);
                            new Transactions(AccountNumber, database).setVisible(true);
                        }
                        else
                        {
                            throw new EmptyInputException("Account Number or pin is incorrect");
                        }
                    }
                    else if(solutionCount == 0)
                    {
                        throw new EmptyInputException("Account Number or pin is incorrect");
                    }
                }
            }else if(ae.getSource()==clearBtn){
                inputText.setText("");
                passwordInput.setText("");
            }else if(ae.getSource()==signupBtn){
                setVisible(false);
                new Signup(database).setVisible(true);
            }
            else if(ae.getSource()==adminLoginBtn)
            {
                setVisible(false);
                new EmployeeLogin(database).setVisible(true);
            }
            else  if(ae.getSource() == exitBtn)
            {
                setVisible(false);
                System.exit(0);
            }
        }catch (EmptyInputException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            setVisible(false);
            new Login(database).setVisible(true);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("error: "+e);
        }
    }
}