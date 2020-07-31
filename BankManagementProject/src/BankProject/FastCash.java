package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FastCash extends JFrame implements ActionListener {

    String amountS="1000";
    String AccountNumber;
    String AccountPinNumber;
    JLabel heading,amountLabel,pinLabel,constAmountInputField;
    JPasswordField pinInputField;
    JButton withdrawBtn,backBtn,exitBtn;
    Calendar date;
    String database;

    FastCash(String AccountNumber,String database) {
        this.database = database;
        this.AccountNumber = AccountNumber;
        this.AccountPinNumber = Utils.getPin(AccountNumber,database);
        date = new GregorianCalendar();
        setTitle("FASTCASH WITHDRAWAL");

        heading = new JLabel("WithDraw from " + AccountNumber);
        heading.setFont(new Font("Raleway", Font.BOLD, 22));

        amountLabel = new JLabel("Amount to Withdraw");
        amountLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        constAmountInputField = new JLabel("1000");
        constAmountInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        pinLabel = new JLabel("Enter the pin");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        pinInputField = new JPasswordField();
        pinInputField.setFont(new Font("Raleway", Font.BOLD, 14));

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

        constAmountInputField.setBounds(500, 200, 200, 30);
        add(constAmountInputField);

        pinLabel.setBounds(200, 250, 200, 30);
        add(pinLabel);

        pinInputField.setBounds(500, 250, 200, 30);
        add(pinInputField);

        backBtn.setBounds(230, 380, 200, 50);
        add(backBtn);

        withdrawBtn.setBounds(450, 380, 200, 50);
        add(withdrawBtn);

        exitBtn.setBounds(320, 550, 200, 50);
        add(exitBtn);

        withdrawBtn.addActionListener(this);
        backBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        setSize(800, 800);
        setLocation(500, 90);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
            if(actionEvent.getSource()==withdrawBtn)
            {
                String pin = String.valueOf(pinInputField.getPassword());
                if (pin.equals("") || !pin.equals(this.AccountPinNumber)) {
                    throw new EmptyInputException("INCORRECT PIN TRY AGAIN");
                }String currBalance = "0";
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
                String description = "FAST CASH";
                if(Integer.parseInt(currBalance)>=Integer.parseInt(amountS)) {
                    java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTimeInMillis());
                    query = "insert into Transactions(AccNo,date,type,Amount,description) values('" + AccountNumber + "','" + sqlDate + "','W','" + Integer.parseInt(amountS) + "','" + description + "')";
                    conn.setQuery(query);
                    conn.executeStatement();
                    if (!conn.querySuccessState) {
                        if(conn.getResultCount == 1)
                        {
                            JOptionPane.showMessageDialog(null,"Amount "+amountS+" is withdrawn from "+AccountNumber);
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
                                throw new EmptyInputException("Unable to modify balance table");                           }
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
                setVisible(false);
                new Transactions(AccountNumber,database).setVisible(true);

            }
            else if(actionEvent.getSource()==exitBtn)
            {
                setVisible(false);
                new Login(database).setVisible(true);
            }
        }
        catch (EmptyInputException | NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            setVisible(false);
            new FastCash(AccountNumber,database).setVisible(true);


        } catch (Exception e)
        {
            System.out.println("error is "+e);
            setVisible(false);
            new Login(database).setVisible(true);

        }
    }
}
