package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Withdraw extends JFrame implements ActionListener {

    double minWithdrawal= 100;
    String AccountNumber;
    JTextArea descriptionInputField;
    String AccountPinNumber;
    JLabel heading,amountLabel,pinLabel,descriptionLabel;
    JTextField amountInputField;
    JPasswordField pinInputField;
    JButton withdrawBtn,backBtn,exitBtn;
    Calendar date;
    String description;
    String database;

    Withdraw(String AccountNumber,String database) {
        this.database = database;
        this.AccountNumber = AccountNumber;
        this.AccountPinNumber = Utils.getPin(AccountNumber,database);
        date = new GregorianCalendar();
        setTitle("WITHDRAW");
        heading = new JLabel("WITHDRAW from " + AccountNumber);
        heading.setFont(new Font("Raleway", Font.BOLD, 22));

        amountLabel = new JLabel("Enter Amount to Withdraw");
        amountLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        amountInputField = new JTextField("0");
        amountInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        pinLabel = new JLabel("Enter the pin");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        pinInputField = new JPasswordField();
        pinInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        descriptionLabel=new JLabel("Description : ");
        descriptionLabel.setFont(new Font("Raleway",Font.BOLD,18));

        descriptionInputField=new JTextArea();
        descriptionInputField.setFont(new Font("Raleway", Font.BOLD,14));

        withdrawBtn = new JButton("WITHDRAW");
        withdrawBtn.setFont(new Font("System", Font.BOLD, 18));
        withdrawBtn.setBackground(Color.BLACK);
        withdrawBtn.setForeground(Color.WHITE);

        backBtn = new JButton("BACK");
        backBtn.setFont(new Font("System", Font.BOLD, 18));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setFont(new Font("System", Font.BOLD, 18));
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);

        setLayout(null);

        heading.setBounds(250, 100, 600, 30);
        add(heading);

        amountLabel.setBounds(200, 200, 300, 30);
        add(amountLabel);

        amountInputField.setBounds(500, 200, 200, 30);
        add(amountInputField);

        pinLabel.setBounds(200, 250, 200, 30);
        add(pinLabel);

        pinInputField.setBounds(500, 250, 200, 30);
        add(pinInputField);

        descriptionLabel.setBounds(200,300,200,30);
        add(descriptionLabel);

        descriptionInputField.setBounds(500,300,200,100);
        add(descriptionInputField);

        backBtn.setBounds(230, 420, 200, 50);
        add(backBtn);

        withdrawBtn.setBounds(450, 420, 200, 50);
        add(withdrawBtn);

        exitBtn.setBounds(320, 590, 200, 50);
        add(exitBtn);

        withdrawBtn.addActionListener(this);
        backBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        setSize(800, 800);
        setLocation(500, 90);
        setVisible(true);

        amountInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                if (Character.isAlphabetic(ch)) {
                    JOptionPane.showMessageDialog(null, "Only numbers are allowed");
                    amountInputField.setText(amountInputField.getText().substring(0, amountInputField.getText().length() - 1));
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    try{
        if(actionEvent.getSource()==withdrawBtn)
        {
            String amountS = amountInputField.getText();
            description=descriptionInputField.getText();
            if(amountS.equals(""))
                throw new EmptyInputException("Invalid Amount to deposit");
            if(description.equals(""))
                throw new EmptyInputException("Please provide some description");
            if(Double.parseDouble(amountS)<minWithdrawal)
                throw new EmptyInputException("Minimum withdrawal amount  is Rs 100");
            double amount = Double.parseDouble(amountS);
            String pin = String.valueOf(pinInputField.getPassword());
            if (pin.equals("") || !pin.equals(AccountPinNumber)) {
                throw new EmptyInputException("INCORRECT PIN TRY AGAIN");
            }
            String currBalance = "0";
                Conn conn = new Conn(database);
                String query = "select Balance from Balance where AccNo = " + this.AccountNumber;
                conn.setQuery(query);
                ResultSet solution = conn.executeStatement();
                if (conn.querySuccessState) {
                    while (solution.next()) {
                        currBalance=solution.getString("BALANCE");
                    }
                } else {
                    throw new EmptyInputException("AccountNumber is incorrect please try again");
                }
                if(Integer.parseInt(currBalance)>=Integer.parseInt(amountS)) {
                    java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTimeInMillis());
                    query = "insert into Transactions(AccNo,date,type,Amount,description) values('" + AccountNumber + "','" + sqlDate + "','W','" + Integer.parseInt(amountS) + "','" + description + "')";
                    conn.setQuery(query);
                    conn.executeStatement();
                    if (!conn.querySuccessState) {
                       if(conn.getResultCount == 1)
                       {
                           JOptionPane.showMessageDialog(null,"Amount "+amount+" is withdrawn from "+AccountNumber);
                           int newBal = Integer.parseInt(currBalance)-Integer.parseInt(amountS);
                           query = "update Balance set Balance = "+newBal+" where AccNo = "+this.AccountNumber;
                           conn.setQuery(query);
                           conn.executeStatement();
                           if(!conn.querySuccessState && conn.getResultCount == 1)
                           {
                               System.out.println("updated in balance table");
                           }
                           else
                           {
                               throw new EmptyInputException("Unable to modify balance table")
;                           }
                       }
                    }
                    new Transactions(AccountNumber,database).setVisible(true);
                    setVisible(false);

                }
                else
                {
                    throw new EmptyInputException("Withdraw amount must be less than or equal to balance");
                }
        }
        else if(actionEvent.getSource()==backBtn)
        {
            new Transactions(AccountNumber,database).setVisible(true);
            setVisible(false);
        }
        else if(actionEvent.getSource()==exitBtn)
        {
            new Login(database).setVisible(true);
            setVisible(false);
        }
    }
    catch (EmptyInputException | NumberFormatException e)
    {
        JOptionPane.showMessageDialog(null,e.getMessage());
        new Withdraw(AccountNumber,database).setVisible(true);
        setVisible(false);

    } catch (Exception e)
    {
        System.out.println("error is "+e);
        new Login(database).setVisible(true);
        setVisible(false);
    }
    }
}
