package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Signup3 extends JFrame implements ActionListener {
    JLabel applicationNoLabel, headingLabel, accountTypeLabel, accountNoLabel, accountNoValueLabel, pinLabel, pinValueLabel, serviceLabel, footer,applicationNoValueLabel;
    JRadioButton savingsAccountButton, fixedDepositButton, currentAccountButton, rDepositAccountButton;
    JCheckBox declaration;
    String[] servicesArray = {"DEBIT CARD", "CREDIT CARD", "Internet Banking", "Mobile Banking", "Email Alerts", "Cheque Service", "UPI", "None"};
    JCheckBox[] servicesCheckBox = new JCheckBox[servicesArray.length];
    JButton submitBtn, cancelBtn;
    ButtonGroup accountTypeGroup;
    String ApplicationNo;
    String AccountPinNumber= "";
    String AccountNumber="";
    String database;

    Signup3(String ApplicationNo,String database) {
        this.database = database;
        this.ApplicationNo = ApplicationNo;
        setFont(new Font("System", Font.BOLD, 22));
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 3");

        headingLabel = new JLabel("Account Details");
        headingLabel.setFont(new Font("Raleway", Font.BOLD, 22));

        accountTypeLabel = new JLabel("*Account Type");
        accountTypeLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        accountNoLabel = new JLabel("Account Number");
        accountNoLabel.setFont(new Font("Raleway", Font.BOLD, 18));

            accountNoValueLabel = new JLabel("XXXX-XXXX-XXXX-XXXX");
        accountNoValueLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        pinLabel = new JLabel("PIN");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        pinValueLabel = new JLabel("XXXX");
        pinValueLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        serviceLabel = new JLabel("*Services requested");
        serviceLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        applicationNoLabel = new JLabel("Application Number :");
        applicationNoLabel.setFont(new Font("Raleway", Font.BOLD, 14));

        footer = new JLabel("* marked inputs MUST be filled");
        footer.setFont(new Font("Raleway", Font.BOLD, 14));

        applicationNoValueLabel = new JLabel(this.ApplicationNo);
        applicationNoValueLabel.setFont(new Font("Raleway", Font.BOLD, 14));


        submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("Raleway", Font.BOLD, 14));
        submitBtn.setBackground(Color.BLACK);
        submitBtn.setForeground(Color.WHITE);

        cancelBtn = new JButton("CANCEL");
        cancelBtn.setFont(new Font("Raleway", Font.BOLD, 14));
        cancelBtn.setBackground(Color.BLACK);
        cancelBtn.setForeground(Color.WHITE);


        savingsAccountButton = new JRadioButton("Savings Bank Account");
        savingsAccountButton.setFont(new Font("Raleway", Font.BOLD, 14));
        savingsAccountButton.setBackground(Color.WHITE);
        savingsAccountButton.setForeground(Color.BLACK);

        fixedDepositButton = new JRadioButton("Fixed Deposit Account");
        fixedDepositButton.setFont(new Font("Raleway", Font.BOLD, 14));
        fixedDepositButton.setBackground(Color.WHITE);
        fixedDepositButton.setForeground(Color.BLACK);

        currentAccountButton = new JRadioButton("Current Deposit Account");
        currentAccountButton.setFont(new Font("Raleway", Font.BOLD, 14));
        currentAccountButton.setBackground(Color.WHITE);
        currentAccountButton.setForeground(Color.BLACK);

        rDepositAccountButton = new JRadioButton("Recurring Deposit Account");
        rDepositAccountButton.setFont(new Font("Raleway", Font.BOLD, 14));
        rDepositAccountButton.setBackground(Color.WHITE);
        rDepositAccountButton.setForeground(Color.BLACK);

        accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(savingsAccountButton);
        accountTypeGroup.add(fixedDepositButton);
        accountTypeGroup.add(currentAccountButton);
        accountTypeGroup.add(rDepositAccountButton);

        for (int i = 0; i < servicesArray.length; i++) {
            servicesCheckBox[i] = new JCheckBox(servicesArray[i]);
            servicesCheckBox[i].setBackground(Color.WHITE);
            servicesCheckBox[i].setFont(new Font("Raleway", Font.BOLD, 12));
        }

        declaration = new JCheckBox("I hereby declares that the above entered details correct to th best of my knowledge.");
        declaration.setFont(new Font("Raleway", Font.BOLD, 12));

        setLayout(null);
        applicationNoLabel.setBounds(600, 90, 200, 30);
        add(applicationNoLabel);

        applicationNoValueLabel.setBounds(770, 90, 60, 30);
        add(applicationNoValueLabel);

        headingLabel.setBounds(350, 50, 400, 40);
        add(headingLabel);

        accountTypeLabel.setBounds(100, 140, 200, 30);
        add(accountTypeLabel);

        savingsAccountButton.setBounds(130, 190, 250, 30);
        add(savingsAccountButton);
        fixedDepositButton.setBounds(450, 190, 250, 30);
        add(fixedDepositButton);
        currentAccountButton.setBounds(130, 230, 250, 30);
        add(currentAccountButton);
        rDepositAccountButton.setBounds(450, 230, 250, 30);
        add(rDepositAccountButton);

        accountNoLabel.setBounds(100, 280, 250, 30);
        add(accountNoLabel);
        accountNoValueLabel.setBounds(330, 280, 250, 30);
        add(accountNoValueLabel);

        pinLabel.setBounds(100, 330, 250, 30);
        add(pinLabel);
        pinValueLabel.setBounds(330, 330, 250, 30);
        add(pinValueLabel);

        serviceLabel.setBounds(100, 380, 300, 30);
        add(serviceLabel);
        int x;
        int y = 390;
        for (int i = 0; i < servicesArray.length; i++) {
            if (i % 2 == 0) {
                y = y + 40;
                x = 130;
            } else {
                x = 450;
            }

            servicesCheckBox[i].setBounds(x, y, 250, 30);
            add(servicesCheckBox[i]);
        }

        declaration.setBounds(100, y + 50, 700, 30);
        add(declaration);

        submitBtn.setBounds(300, 650, 100, 30);
        add(submitBtn);


        cancelBtn.setBounds(420, 650, 100, 30);
        add(cancelBtn);

        footer.setBounds(300,720,500,30);
        add(footer);


        setSize(800, 800);
        setLocation(500, 90);
        setVisible(true);

        submitBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String selectedAccountType;
        ArrayList<String> selectedServices = new ArrayList<>();
        boolean declarationSelected;
        try {
            if(actionEvent.getSource()==submitBtn) {
                selectedAccountType = Utils.getSelectedButtonText(accountTypeGroup);
                for (int i = 0; i < servicesArray.length; i++) {
                    if (servicesCheckBox[i].isSelected()) {
                        String selectedOne = servicesCheckBox[i].getText();
                        selectedServices.add(selectedOne);
                    }
                }
                StringBuilder sb = new StringBuilder();
                for(String s:selectedServices)
                {
                 sb.append(s);
                 sb.append(",");
                }
                String services = sb.toString();
                declarationSelected = declaration.isSelected();
                if (selectedAccountType == null || selectedAccountType.equals("") || selectedServices.isEmpty()) {
                    throw new EmptyInputException("* MARKED INPUTS CANNOT BE EMPTY");
                }
                switch (selectedAccountType) {
                    case "Savings Bank Account":
                        selectedAccountType = "SAVINGS";
                        break;
                    case "Fixed Deposit Account":
                        selectedAccountType = "FIXED";
                        break;
                    case "Current Deposit Account":
                        selectedAccountType = "CURRENT";
                        break;
                    default:
                        selectedAccountType = "RECURRING";
                        break;
                }
                if (!declarationSelected) {
                    throw new EmptyInputException("You have to declare for being true for all details provided");
                }

                ArrayList<Integer> list = new ArrayList<>();
                for (int i=0; i<11; i++) {
                    list.add(i);
                }
                Collections.shuffle(list);

                for(int i=0;i<16;i++){
                    int n=list.get((int)(Math.random()*list.size()));
                    int shuffleCounter = (int) (Math.random());
                    if(shuffleCounter%3==0)
                        Collections.shuffle(list);
                    AccountNumber = AccountNumber.concat(Integer.toString(n%10));
                }

                for(int i=0;i<4;i++){
                    int n=list.get((int)(Math.random()*list.size()));
                    int shuffleCounter = (int) (Math.random());
                    if(shuffleCounter%4==0)
                        Collections.shuffle(list);
                    AccountPinNumber = AccountPinNumber.concat(Integer.toString(n%10));
                }

                Conn conn2 = new Conn(database);
                String updateQuery = "update PersonalDetails set AccNo = '"+AccountNumber+"' where AccNo = '"+ApplicationNo+"'";
                conn2.setQuery(updateQuery);
                conn2.executeStatement();

                Conn conn = new Conn(database);
                String insertQuery = "insert into Accounts values('"+AccountNumber+"','"+AccountPinNumber+"','"+selectedAccountType+"','"+services+"')";
                conn.setQuery(insertQuery);
                conn.executeStatement();

                String createBalance = "insert into Balance values('"+AccountNumber+"','0')";
                Conn conn3 = new Conn(database);
                conn3.setQuery(createBalance);
                conn3.executeStatement();
                if(conn.getResultCount==1 && !conn.querySuccessState && conn2.getResultCount==1 && !conn2.querySuccessState && !conn3.querySuccessState && conn3.getResultCount==1)
                {
                    System.out.println("Saved successfully into the PersonalDetails table,Balance Table and accounts");
                }
                else
                {
                    System.out.println("Something is wrong");
                }

                JOptionPane.showMessageDialog(null,"Account Number "+AccountNumber+" with PIN "+AccountPinNumber+" pin is created");
                setVisible(false);
                new Transactions(AccountNumber,database).setVisible(true);
            }
            else if(actionEvent.getSource()==cancelBtn)
            {
                Conn conn = new Conn(database);
                String query = "delete from PersonalDetails where AccNo = "+ApplicationNo;
                conn.setQuery(query);
                conn.executeStatement();
                if(conn.getResultCount==1 && !conn.querySuccessState) {
                    JOptionPane.showMessageDialog(null, "Your Previously collected data has been removed");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Your previously collected information will be deleted soon");
                }
                setVisible(false);
                new Login(database).setVisible(true);
            }
        }catch (EmptyInputException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        catch (Exception e) {
            System.out.println("error "+e);
        }
    }
}
