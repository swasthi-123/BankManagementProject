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

public class Deposit extends JFrame implements ActionListener {
    JLabel amountLabel,pinLabel,heading,descriptionLabel;
    JTextField amountInputField;
    JTextArea descriptionInputField;
    JPasswordField pinInputField;
    JButton depositBtn,exitBtn,backBtn;
    String AccountNumber;
    String AccountPinNumber;
    Calendar date;
    String description;
    String database;

    Deposit(String accountNumber,String database )
    {
        this.database = database;
        this.AccountNumber = accountNumber;
        this.AccountPinNumber = Utils.getPin(AccountNumber,database);
        date=new GregorianCalendar();

        setTitle("DEPOSIT");

        heading = new JLabel("DEPOSIT into "+AccountNumber);
        heading.setFont(new Font("Raleway",Font.BOLD,22));

        amountLabel = new JLabel("Enter Amount to Deposit");
        amountLabel.setFont(new Font("Raleway", Font.BOLD,18));

        amountInputField = new JTextField("0");
        pinLabel = new JLabel("Enter the pin");
        amountInputField.setFont(new Font("Raleway", Font.BOLD,14));

        pinLabel.setFont(new Font("Raleway", Font.BOLD,18));

        pinInputField = new JPasswordField();
        pinInputField.setFont(new Font("Raleway", Font.BOLD,14));

        descriptionLabel=new JLabel("Description : ");
        descriptionLabel.setFont(new Font("Raleway",Font.BOLD,18));

        descriptionInputField=new JTextArea();
        descriptionInputField.setFont(new Font("Raleway", Font.BOLD,14));

        depositBtn = new JButton("DEPOSIT");
        depositBtn.setFont(new Font("System", Font.BOLD, 18));
        depositBtn.setBackground(Color.BLACK);
        depositBtn.setForeground(Color.WHITE);

        backBtn = new JButton("BACK");
        backBtn.setFont(new Font("System", Font.BOLD, 18));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setFont(new Font("System", Font.BOLD, 18));
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);

        setLayout(null);

        heading.setBounds(250,100,600,30);
        add(heading);

        amountLabel.setBounds(200,200,300,30);
        add(amountLabel);

        amountInputField.setBounds(500,200,200,30);
        add(amountInputField);

        pinLabel.setBounds(200,250,200,30);
        add(pinLabel);

        pinInputField.setBounds(500,250,200,30);
        add(pinInputField);

        descriptionLabel.setBounds(200,300,200,30);
        add(descriptionLabel);

        descriptionInputField.setBounds(500,300,200,100);
        add(descriptionInputField);

        backBtn.setBounds(260,420,125,50);
        add(backBtn);

        depositBtn.setBounds(415,420,125,50);
        add(depositBtn);

        exitBtn.setBounds(300,590,200,50);
        add(exitBtn);

        depositBtn.addActionListener(this);
        backBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);

        amountInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                if(Character.isAlphabetic(ch)){
                    JOptionPane.showMessageDialog(null,"Only numbers are allowed");
                    amountInputField.setText(amountInputField.getText().substring(0,amountInputField.getText().length()-1));
                }
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
            if(actionEvent.getSource()==depositBtn) {
                String amountS = amountInputField.getText();
                description=descriptionInputField.getText();
                if(amountS.equals(""))
                    throw new EmptyInputException("Invalid Amount to deposit");
                if(description.equals(""))
                    throw new EmptyInputException("Please provide some description");
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
                    java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTimeInMillis());
                    query = "insert into Transactions(AccNo,date,type,Amount,description) values('" + AccountNumber + "','" + sqlDate + "','D','" + Integer.parseInt(amountS) + "','" + description + "')";
                    conn.setQuery(query);
                    conn.executeStatement();
                    if (!conn.querySuccessState) {
                        if(conn.getResultCount == 1)
                        {
                            JOptionPane.showMessageDialog(null,"Amount "+amount+" is deposited to "+AccountNumber);
                            int newBal = Integer.parseInt(currBalance)+Integer.parseInt(amountS);
                            query = "update Balance set Balance = "+newBal+" where AccNo = "+this.AccountNumber;
                            conn.setQuery(query);
                            conn.executeStatement();
                            if(!conn.querySuccessState && conn.getResultCount == 1)
                            {
                                System.out.println("updated in balance table");
                            }
                            else
                            {
                                throw new EmptyInputException("Unable to modify balance table");                           }
                        }
                    }
                    setVisible(false);
                    new Transactions(AccountNumber,database).setVisible(true);
            }
            else if(actionEvent.getSource()==backBtn)
            {
                    setVisible(false);
                    new Transactions(AccountNumber,database).setVisible(true);
            }
            else if(actionEvent.getSource()==exitBtn)
            {
                setVisible(false);
               new Login(database).setVisible(true);

            }
        }
        catch (EmptyInputException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            setVisible(false);
            new Deposit(AccountNumber,database).setVisible(true);


        }
        catch (Exception e)
        {
            System.out.println("error is "+e);
            new Login(database).setVisible(true);
            setVisible(false);
        }
    }
}
