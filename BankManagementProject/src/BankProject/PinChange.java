package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {
    String AccountNumber;
    String AccountPinNumber;
    JLabel heading,oldPinLabel,newPinLabel,confirmNewPinLabel,accountNoLabel;
    JPasswordField oldPinInputField,newPinInputField,confirmNewPinInputField;
    JButton setPin,backBtn,exitBtn;
    String database;
    PinChange(String AccountNumber,String database)
    {
        this.database = database;
        this.AccountNumber = AccountNumber;
        this.AccountPinNumber=Utils.getPin(AccountNumber,database);
        setTitle("CHANGE THE ACCOUNT PIN");

        heading = new JLabel("CHANGE THE PIN OF ACCOUNT ");
        heading.setFont(new Font("Raleway", Font.BOLD, 22));

        accountNoLabel = new JLabel(""+AccountNumber);
        accountNoLabel.setFont(new Font("Raleway", Font.BOLD, 22));

        oldPinLabel = new JLabel("Enter your old PIN");
        oldPinLabel.setFont(new Font("Raleway",Font.BOLD,18));

        oldPinInputField = new JPasswordField();
        oldPinInputField.setFont(new Font("Raleway",Font.BOLD,18));

        newPinLabel = new JLabel("Enter your new PIN");
        newPinLabel.setFont(new Font("Raleway",Font.BOLD,18));

        newPinInputField = new JPasswordField();
        newPinInputField.setFont(new Font("Raleway",Font.BOLD,18));

        confirmNewPinLabel = new JLabel("Confirm your new PIN");
        confirmNewPinLabel.setFont(new Font("Raleway",Font.BOLD,18));

        confirmNewPinInputField = new JPasswordField();
        confirmNewPinInputField.setFont(new Font("Raleway",Font.BOLD,18));


        heading.setBounds(250, 100, 600, 30);
        add(heading);

        accountNoLabel.setBounds(310,140,600,30);
        add(accountNoLabel);

        oldPinLabel.setBounds(100,200,200,30);
        add(oldPinLabel);

        oldPinInputField.setBounds(400,200,200,30);
        add(oldPinInputField);

        newPinLabel.setBounds(100,250,200,30);
        add(newPinLabel);

        newPinInputField.setBounds(400,250,200,30);
        add(newPinInputField);

        confirmNewPinLabel.setBounds(100,300,250,30);
        add(confirmNewPinLabel);

        confirmNewPinInputField.setBounds(400,300,200,30);
        add(confirmNewPinInputField);

        setPin = new JButton("SET PIN");
        setPin.setFont(new Font("System", Font.BOLD, 18));
        setPin.setBackground(Color.BLACK);
        setPin.setForeground(Color.WHITE);

        backBtn = new JButton("BACK");
        backBtn.setFont(new Font("System", Font.BOLD, 18));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setFont(new Font("System", Font.BOLD, 18));
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);

        setLayout(null);

        backBtn.setBounds(260, 380, 125, 50);
        add(backBtn);

        setPin.setBounds(415, 380, 125, 50);
        add(setPin);

        exitBtn.setBounds(300, 550, 200, 50);
        add(exitBtn);

        setPin.addActionListener(this);
        backBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        setSize(800, 800);
        setLocation(500, 90);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    try{
        if(actionEvent.getSource()==setPin)
        {
            String newPin=String.valueOf(newPinInputField.getPassword());
            String newPinCopy = String.valueOf(confirmNewPinInputField.getPassword());
            if(!newPin.equals(newPinCopy) || newPin.equals(""))
            {
                throw new EmptyInputException("The confirmation pin doesn't match with the new pin");
            }
            else {
                Conn conn = new Conn(database);
                String query = "update Accounts set pinNo = '"+newPin+"' where AccNo = '" + AccountNumber+"'";
                conn.setQuery(query);
                conn.executeStatement();
                if (!conn.querySuccessState) {
                    if(conn.getResultCount==1){
                        this.AccountPinNumber = newPin;
                        JOptionPane.showMessageDialog(null, "Your Pin has been changed successfully.Please relogin");
                        setVisible(false);
                        new Login(database).setVisible(true);
                    }
                    else
                    {
                        throw new EmptyInputException("Unable to change pin at the moment please try again");
                    }
                    }
            }
        } else if(actionEvent.getSource() == backBtn)
        {
            setVisible  (false);
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
        new PinChange(AccountNumber,database).setVisible(true);
    }
    catch (Exception e)
    {
        System.out.println("error is "+e);
        setVisible(false);
        new Login(database).setVisible(true);
    }
    }
}