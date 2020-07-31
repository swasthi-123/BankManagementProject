package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {
    String currBalance = "100";
    String AccountNumber;
    String AccountPinNumber;
    JLabel headingLabel,statementLabel,accountNoLabel;
    JButton exitBtn,backBtn;
    String database;
    BalanceEnquiry(String AccountNumber,String database)
    {
        this.database = database;
        this.AccountNumber = AccountNumber;
        this.AccountPinNumber = Utils.getPin(AccountNumber,database);

        setTitle("BALANCE");

        headingLabel = new JLabel("BALANCE IN YOUR ACCOUNT");
        headingLabel.setFont(new Font("Raleway",Font.BOLD,22));

        accountNoLabel = new JLabel(""+AccountNumber);
        accountNoLabel.setFont(new Font("Raleway",Font.BOLD,22));

        try {
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
        }catch (Exception e)
        {
            System.out.println("error is "+e);
        }


        statementLabel = new JLabel("Current Balance is "+currBalance);
        statementLabel.setFont(new Font("Raleway",Font.BOLD,18));

        setLayout(null);

        headingLabel.setBounds(250, 100, 600, 30);
        add(headingLabel);

        accountNoLabel.setBounds(310,140,600,30);
        add(accountNoLabel);

        statementLabel.setBounds(175,200,400,30);
        add(statementLabel);

        backBtn = new JButton("BACK");
        backBtn.setFont(new Font("System", Font.BOLD, 18));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setFont(new Font("System", Font.BOLD, 18));
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);

        backBtn.setBounds(200,400,150,50);
        add(backBtn);


        exitBtn.setBounds(450,400,150,50);
        add(exitBtn);

        backBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
            if(actionEvent.getSource()==backBtn)
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
        catch (Exception e)
        {
            System.out.println("error is "+e);
        }
    }
}