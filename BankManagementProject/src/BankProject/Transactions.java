package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {
    JLabel headingLabel,accountNoLabel;
    JButton depositBtn,withdrawBtn,fastCashBtn,miniStatementBtn,pinChangeBtn,balanceEnquiryBtn,exitBtn;
    String AccountNumber;
    String AccountPinNumber;
    String database;
    Transactions(String accountNumber,String database) {
        this.database = database;
        this.AccountNumber = accountNumber;
        this.AccountPinNumber = Utils.getPin(AccountNumber,database);
        setTitle("TRANSACTIONS WINDOW");
        accountNoLabel = new JLabel("Account No : "+AccountNumber);
        accountNoLabel.setFont(new Font("raleway", Font.BOLD,24));
        headingLabel = new JLabel("Select the Transaction option ");
        headingLabel.setFont(new Font("raleway", Font.BOLD,24));

        depositBtn = new JButton("DEPOSIT");
        depositBtn.setBackground(Color.BLACK);
        depositBtn.setForeground(Color.WHITE);

        withdrawBtn = new JButton("WITHDRAW");
        withdrawBtn.setBackground(Color.BLACK);
        withdrawBtn.setForeground(Color.WHITE);

        fastCashBtn = new JButton("FAST CASH");
        fastCashBtn.setBackground(Color.BLACK);
        fastCashBtn.setForeground(Color.WHITE);

        miniStatementBtn = new JButton("MINI STATEMENT");
        miniStatementBtn.setBackground(Color.BLACK);
        miniStatementBtn.setForeground(Color.WHITE);

        pinChangeBtn = new JButton("CHANGE PIN");
        pinChangeBtn.setBackground(Color.BLACK);
        pinChangeBtn.setForeground(Color.WHITE);

        balanceEnquiryBtn = new JButton("CHECK BALANCE");
        balanceEnquiryBtn.setBackground(Color.BLACK);
        balanceEnquiryBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);

        setLayout(null);
        accountNoLabel.setBounds(200,50,600,30);
        add(accountNoLabel);

        headingLabel.setBounds(220,100,400,30);
        add(headingLabel);

        depositBtn.setBounds(190,150,200,30);
        add(depositBtn);

        withdrawBtn.setBounds(440,150,200,30);
        add(withdrawBtn);

        fastCashBtn.setBounds(190,200,200,30);
        add(fastCashBtn);

        miniStatementBtn.setBounds(440,200,200,30);
        add(miniStatementBtn);

        pinChangeBtn.setBounds(190,250,200,30);
        add(pinChangeBtn);

        balanceEnquiryBtn.setBounds(440,250,200,30);
        add(balanceEnquiryBtn);

        exitBtn.setBounds(320,300,200,30);
        add(exitBtn);

        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        fastCashBtn.addActionListener(this);
        miniStatementBtn.addActionListener(this);
        pinChangeBtn.addActionListener(this);
        balanceEnquiryBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setSize(800,800);
        setLocation(500,90);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Object eventSource = actionEvent.getSource();
            if (eventSource == depositBtn) {
                new Deposit(AccountNumber,database).setVisible(true);
            } else if (eventSource == withdrawBtn) {
                new Withdraw(AccountNumber,database).setVisible(true);
            } else if (eventSource == fastCashBtn) {
                new FastCash(AccountNumber,database).setVisible(true);
            } else if (eventSource == miniStatementBtn) {
                new Statement(AccountNumber,database).setVisible(true);
            } else if (eventSource == pinChangeBtn) {
                new PinChange(AccountNumber,database).setVisible(true);
            } else if (eventSource == balanceEnquiryBtn) {
                    new BalanceEnquiry(AccountNumber,database).setVisible(true);
            } else if (eventSource == exitBtn) {
                JOptionPane.showMessageDialog(null, "Exiting from TRANSACTION window");
                new Login(database).setVisible(true);
            }
        } catch (Exception e)
        {
            new Login(database).setVisible(true);
        }
        finally {
            setVisible(false);
        }

    }
}
