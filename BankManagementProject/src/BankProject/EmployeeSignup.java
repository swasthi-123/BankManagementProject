package BankProject;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeSignup extends JFrame implements ActionListener {

    String database;
    JLabel nameLabel,dateLabel,genderLabel,emailLabel,designationLabel,heading;
    JTextField nameField,emailField,designationField;
    JDatePickerImpl datePicker;
    UtilDateModel model;
    JDatePanelImpl datePanel;
    ButtonGroup genderBg;
    JRadioButton maleBtn,femaleBtn,otherBtn;
JButton submitBtn,backBtn;

    EmployeeSignup(String db)
    {
        database = db;
setLayout(null);
        setTitle("EMPLOYEE SIGNUP");

        heading = new JLabel("EMPLOYEE DETAILS  : ");
        heading.setFont(new Font("Raleway",Font.BOLD,22));
        heading.setBounds(100,50,300,30);
        add(heading);


        nameLabel = new JLabel("*Employee name : ");
        nameLabel.setFont(new Font("Raleway",Font.BOLD,18));
        nameLabel.setBounds(100,100,300,30);
        add(nameLabel);

        dateLabel = new JLabel("*Employee Date Of Birth : ");
        dateLabel.setFont(new Font("Raleway",Font.BOLD,18));
        dateLabel.setBounds(100,150,400,30);
        add(dateLabel);

        genderLabel = new JLabel("*Employee gender : ");
        genderLabel.setFont(new Font("Raleway",Font.BOLD,18));
        genderLabel.setBounds(100,200,300,30);
        add(genderLabel);

        emailLabel = new JLabel("Employee email : ");
        emailLabel.setFont(new Font("Raleway",Font.BOLD,18));
        emailLabel.setBounds(100,250,300,30);
        add(emailLabel);

        designationLabel = new JLabel("*Employee designation : ");
        designationLabel.setFont(new Font("Raleway",Font.BOLD,18));
        designationLabel.setBounds(100,300,300,30);
        add(designationLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Raleway",Font.BOLD,14));
        nameField.setBounds(400,100,300,30);
        add(nameField);


        model = new UtilDateModel();
        model.setDate(2020, 1, 1);
        Properties p = new Properties();
        p.put("text.today","Today");
        p.put("text.month","Month");
        p.put("text.year","Year");
        datePanel = new JDatePanelImpl(model,p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.setBounds(400,150,300,30);
        add(datePicker);


        maleBtn = new JRadioButton("Male");
        maleBtn.setFont(new Font("Raleway",Font.BOLD,14));
        maleBtn.setBackground(Color.WHITE);
        maleBtn.setBounds(400,200,75,30);
        add(maleBtn);

        femaleBtn = new JRadioButton("Female");
        femaleBtn.setFont(new Font("Raleway",Font.BOLD,14));
        femaleBtn.setBackground(Color.WHITE);
        femaleBtn.setBounds(500,200,100,30);
        add(femaleBtn);

        otherBtn = new JRadioButton("Other");
        otherBtn.setFont(new Font("Raleway",Font.BOLD,14));
        otherBtn.setBackground(Color.WHITE);
        otherBtn.setBounds(625,200,75,30);
        add(otherBtn);

        genderBg = new ButtonGroup();
        genderBg.add(maleBtn);
        genderBg.add(femaleBtn);
        genderBg.add(otherBtn);

        emailField = new JTextField();
        emailField.setFont(new Font("Raleway",Font.BOLD,14));
        emailField.setBounds(400,250,300,30);
        add(emailField);

        designationField = new JTextField();
        designationField.setFont(new Font("Raleway",Font.BOLD,14));
        designationField.setBounds(400,300,300,30);
        add(designationField);

        submitBtn = new JButton("SUBMIT");
        submitBtn.setFont(new Font("Raleway", Font.BOLD,14));
        submitBtn.setBackground(Color.BLACK);
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setBounds(400,350,100,30);
        add(submitBtn);

        backBtn = new JButton("BACK");
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        backBtn.setBounds(600,450,100,30);
        add(backBtn);

        backBtn.addActionListener(this);
        submitBtn.addActionListener(this);

        setSize(800,800);
        setLocation(500,200);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() == submitBtn) {
                String name = nameField.getText();
                Date selectedDate = (Date) datePicker.getModel().getValue();
                java.sql.Date dob = new java.sql.Date(selectedDate.getTime());
                String gender = Utils.getSelectedButtonText(genderBg);
                String email = emailField.getText();
                String designation = designationField.getText();
                Pattern pattern;
                Matcher matcher;

                if (!email.equals("")) {
                    pattern = Pattern.compile("^.+@.+\\..+$");
                    matcher = pattern.matcher(email);
                    if (!matcher.matches())
                        throw new EmptyInputException("Email is invalid! Please check");
                } else {
                    email = null;
                }
                if (name == null || name.equals("") || gender == null || gender.equals("") || designation == null || designation.equals("")) {
                    throw new EmptyInputException("* MARKED INPUTS MUST BE FILLED");
                } else {
                    Conn c1 = new Conn(database);
                    String query = "insert into EmployeeDetails(name,DOB,gender,email,designation) values('" + name + "','" + dob + "','" + gender + "','" + email + "','" + designation + "')";
                    c1.setQuery(query);
                    c1.executeStatement();
                    if (!c1.querySuccessState && c1.getResultCount == 1) {
                        Random r = new Random();
                        int high = 10000;
                        int low = 1000;
                        int password = r.nextInt(high - low) + low;
                        query = "insert into EmployeePassword(password) values('" + password + "')";
                        c1.setQuery(query);
                        c1.executeStatement();
                        if (!c1.querySuccessState && c1.getResultCount == 1) {
                            System.out.println("Updated both tables");
                            JOptionPane.showMessageDialog(null,"updated employee details in both table");
                            setVisible(false);
                            new EmployeeLogin(database).setVisible(true);
                        } else {
                            System.out.println("failed to update password table");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Could not update the Employee details try again");
                    }

                }
            }
            else if(actionEvent.getSource() == backBtn)
            {
                setVisible(false);
                new EmployeeLogin(database).setVisible(true);
            }
        }
            catch (Exception e ){
            JOptionPane.showMessageDialog(null,"something is wrong please log in again");
            new Login(database).setVisible(true);
            }
        }
    }
