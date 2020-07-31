package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EmployeeDetails extends JFrame implements ActionListener {
    String EmployeeId ;
    String database;
    JLabel heading;
    JLabel idLabel,nameLabel,dobLabel,genderLabel,emailLabel,designationLabel,adminStatusLabel;
    JLabel idValue,nameValue,dobValue,genderValue,emailValue,designationValue,adminStatusValue;
    JButton backBtn;
    EmployeeDetails(String id,String db)
    {
        setTitle("EMPLOYEE DETAILS");
        EmployeeId = id;
        database = db;

        heading = new JLabel("EMPLOYEE DETAILS");
        heading.setFont(new Font("Raleway",Font.BOLD,22));

        idLabel = new JLabel("EMPLOYEE ID : ");
        idLabel.setFont(new Font("Raleway",Font.BOLD,18));
        nameLabel = new JLabel("NAME : ");
        nameLabel.setFont(new Font("Raleway",Font.BOLD,18));
        dobLabel = new JLabel("DATE OF BIRTH : ");
        dobLabel.setFont(new Font("Raleway",Font.BOLD,18));
        genderLabel = new JLabel("GENDER : ");
        genderLabel.setFont(new Font("Raleway",Font.BOLD,18));
        emailLabel = new JLabel("EMAIL : ");
        emailLabel.setFont(new Font("Raleway",Font.BOLD,18));
        designationLabel = new JLabel("DESIGNATION : ");
        designationLabel.setFont(new Font("Raleway",Font.BOLD,18));
        adminStatusLabel = new JLabel("ADMIN : ");
        adminStatusLabel.setFont(new Font("Raleway",Font.BOLD,18));

        idValue = new JLabel("rs.getString(1)");
        nameValue=new JLabel("rs.getString(2)");
        dobValue = new JLabel("rs.getDate(3).toString()");
        genderValue = new JLabel("rs.getString(4)");
        emailValue= new JLabel("rs.getString(5)");
        designationValue = new JLabel("rs.getString(6)");

        try{
            Conn c1;
            c1 = new Conn(database);
            String query = "select * from EmployeeDetails where employeeId = "+EmployeeId;
            c1.setQuery(query);
            ResultSet rs = c1.executeStatement();
            if(c1.querySuccessState)
            while (rs.next())
            {
                idValue.setText(rs.getString(1));
                nameValue.setText(rs.getString(2));
                dobValue.setText(rs.getDate(3).toString());
                genderValue.setText(rs.getString(4));
                emailValue.setText(rs.getString(5));
                designationValue.setText(rs.getString(6));
            }
            idValue.setFont(new Font("Raleway",Font.BOLD,18));
            nameValue.setFont(new Font("Raleway",Font.BOLD,18));
            dobValue.setFont(new Font("Raleway",Font.BOLD,18));
            genderValue.setFont(new Font("Raleway",Font.BOLD,18));
            emailValue.setFont(new Font("Raleway",Font.BOLD,18));
            designationValue.setFont(new Font("Raleway",Font.BOLD,18));

            query = "select admin from EmployeePassword where employeeId = "+EmployeeId;
            c1.setQuery(query);
            rs = c1.executeStatement();
            int status = 0;
            if(c1.querySuccessState)
            while (rs.next())
            {
                status = rs.getInt(1);
            }
            if(status ==1)
                adminStatusValue = new JLabel("ADMIN");
            else
                adminStatusValue = new JLabel("NOT AN ADMIN");
            adminStatusValue.setFont(new Font("Raleway",Font.BOLD,18));
            setLayout(null);

            heading.setBounds(100,50,400,30);
            add(heading);

            idLabel.setBounds(100,100,300,30);
            add(idLabel);
            idValue.setBounds(300,100,300,30);
            add(idValue);

            nameLabel.setBounds(100,150,300,30);
            add(nameLabel);
            nameValue.setBounds(300,150,300,30);
            add(nameValue);

            dobLabel.setBounds(100,200,300,30);
            add(dobLabel);
            dobValue.setBounds(300,200,300,30);
            add(dobValue);

            genderLabel.setBounds(100,250,300,30);
            add(genderLabel);
            genderValue.setBounds(300,250,300,30);
            add(genderValue);

            emailLabel.setBounds(100,300,300,30);
            add(emailLabel);
            emailValue.setBounds(300,300,300,30);
            add(emailValue);

            designationLabel.setBounds(100,350,300,30);
            add(designationLabel);
            designationValue.setBounds(300,350,300,30);
            add(designationValue);

            adminStatusLabel.setBounds(100,400,300,30);
            add(adminStatusLabel);
            adminStatusValue.setBounds(300,400,300,30);
            add(adminStatusValue);

            backBtn = new JButton("BACK");
            backBtn.setFont(new Font("Arial", Font.BOLD, 14));
            backBtn.setBackground(Color.BLACK);
            backBtn.setForeground(Color.WHITE);
            backBtn.setBounds(600,450,100,30);
            add(backBtn);

            backBtn.addActionListener(this);

            setSize(800,700);
            setLocation(500,200);
            setVisible(true);
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        try{
            if (actionEvent.getSource() == backBtn) {
                setVisible(false);
                new EmployeeLogin(database).setVisible(true);
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"something is wrong please Login again");
            setVisible(false);
            new Login(database).setVisible(true);
        }
    }
}
