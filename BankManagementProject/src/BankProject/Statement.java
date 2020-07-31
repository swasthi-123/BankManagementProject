package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Statement extends JFrame implements ActionListener {
    String AccountNumber;
    String AccountPinNumber;
    ArrayList<String> transactions;
    JLabel headingLabel,accountNoLabel;
    JLabel[] statementLabel;
    JButton exitBtn,backBtn;
    String database;

    Statement(String AccountNumber,String database ){
        this.database = database;
        this.AccountNumber = AccountNumber;
        this.AccountPinNumber = Utils.getPin(AccountNumber,database);
        transactions = new ArrayList<>();
       try{
           Conn conn = new Conn(database);
           String query  = "select * from Transactions where AccNo = '"+this.AccountNumber+"' order by date desc";
           conn.setQuery(query);
           ResultSet solution = conn.executeStatement();
           if(conn.querySuccessState)
           {
               while (solution.next())
               {
                   transactions.add(solution.getString("AccNo")+"  "+solution.getTimestamp("date")+"  "+solution.getString("type")+"  "+solution.getString("Amount")+"  "+solution.getString("description"));
               }
           }
       }
       catch (Exception e)
       {
           System.out.println("error is "+e);
       }

        headingLabel = new JLabel("BANK STATEMENT OF ACCOUNT");
        headingLabel.setFont(new Font("Raleway",Font.BOLD,22));

        accountNoLabel = new JLabel(""+AccountNumber);
        accountNoLabel.setFont(new Font("Raleway",Font.BOLD,22));

        statementLabel = new JLabel[Math.min(transactions.size()+1,5)];

        for (int i=0;i<Math.min(5,transactions.size());i++)
        {
            statementLabel[i] = new JLabel(transactions.get(i));
            statementLabel[i].setFont(new Font("Raleway",Font.PLAIN,12));
        }

        setLayout(null);

        headingLabel.setBounds(250, 100, 600, 30);
        add(headingLabel);

        accountNoLabel.setBounds(330,140,600,30);
        add(accountNoLabel);

        backBtn = new JButton("BACK");
        backBtn.setFont(new Font("System", Font.BOLD, 18));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setFont(new Font("System", Font.BOLD, 18));
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);
        int xPos = 100;
        int yPos=200;

        for(int i=0;i<Math.min(transactions.size(),5);i++)
        {
            statementLabel[i].setBounds(xPos,yPos,600,30);
            add(statementLabel[i]);
            yPos+=50;
        }


        backBtn.setBounds(200,yPos,150,50);
        add(backBtn);


        exitBtn.setBounds(450,yPos,150,50);
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
                setVisible(false);
                new Login(database).setVisible(true);
            }
    }
}