package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends JFrame implements ActionListener {

    JLabel applicationLabel,headingLabel,nameLabel,fatherNameLabel,DOBLabel,dateLabel,monthLabel,yearLabel,genderLabel,emailLabel,mStatusLabel,addressLabel1,addressLabel2,cityLabel,pinLabel,stateLabel,footer;
    JTextField nameField,fatherNameField,emailField,addressField1,addressField2, cityField,pinField,stateField;
    JRadioButton maleBtn,femaleBtn,otherBtn1,otherBtn2,marriedBtn,unMarriedBtn;
    JComboBox<Integer> day,year;
    JComboBox<String> month;
    JButton nextBtn;
    ButtonGroup maritalStatus,genderStatus;

    Random rand = new Random();
    String ApplicationNo="";
    String database;

    String name;
    String fatherName;
    GregorianCalendar dob;
    String genderStatusValue;
    String email;
    String maritalStatusValue;
    String address1;
    String address2;
    String city;
    int pinCode;
    String state;

    Signup(String database)
    {
        this.database =database;

        for(int i=0;i<4;i++)
        {
            int n = rand.nextInt(10);
            ApplicationNo=ApplicationNo.concat(Integer.toString(n));
        }
        setFont(new Font("System", Font.BOLD, 28));
        setTitle("CREATE NEW ACCOUNT - APPLICATION FORM");
        applicationLabel = new JLabel("APPLICATION FORM NUMBER : "+ApplicationNo);
        applicationLabel.setFont(new Font("Raleway",Font.BOLD,25));

        headingLabel = new JLabel("Page 1 : Personal Details");
        headingLabel.setFont(new Font("Raleway", Font.BOLD,22));

        nameLabel = new JLabel("*Name : ");
        nameLabel.setFont(new Font("Raleway", Font.BOLD,20));

        fatherNameLabel = new JLabel("*Father's name : ");
        fatherNameLabel.setFont(new Font("Raleway", Font.BOLD,20));

        DOBLabel = new JLabel("*Date of Birth : ");
        DOBLabel.setFont(new Font("Raleway", Font.BOLD,20));

        dateLabel = new JLabel("Date : ");
        dateLabel.setFont(new Font("Raleway", Font.BOLD,14));

        monthLabel = new JLabel("Month : ");
        monthLabel.setFont(new Font("Raleway", Font.BOLD,14));

        yearLabel = new JLabel("Year : ");
        yearLabel.setFont(new Font("Raleway", Font.BOLD,14));

        genderLabel = new JLabel("*Gender : ");
        genderLabel.setFont(new Font("Raleway", Font.BOLD,20));

        emailLabel = new JLabel("Email : ");
        emailLabel.setFont(new Font("Raleway", Font.BOLD,20));

        mStatusLabel = new JLabel("*Marital Status : ");
        mStatusLabel.setFont(new Font("Raleway", Font.BOLD,20));

        addressLabel1 = new JLabel("*Address Line 1 : ");
        addressLabel1.setFont(new Font("Raleway", Font.BOLD,20));

        addressLabel2 = new JLabel("Address Line 2 : ");
        addressLabel2.setFont(new Font("Raleway", Font.BOLD,20));

        cityLabel = new JLabel("*City : ");
        cityLabel.setFont(new Font("Raleway", Font.BOLD,20));

        pinLabel = new JLabel("*PIN code : ");
        pinLabel.setFont(new Font("Raleway", Font.BOLD,20));

        stateLabel = new JLabel("*State");
        stateLabel.setFont(new Font("Raleway", Font.BOLD,20));

        footer = new JLabel("* marked inputs MUST be filled");
        footer.setFont(new Font("Raleway", Font.BOLD,14));

        nameField = new JTextField();
        nameField.setFont(new Font("Raleway", Font.BOLD,14));

        fatherNameField = new JTextField();
        fatherNameField.setFont(new Font("Raleway", Font.BOLD,14));

        emailField = new JTextField();
        emailField.setFont(new Font("Raleway", Font.BOLD,14));

        addressField1 = new JTextField();
        addressField1.setFont(new Font("Raleway", Font.BOLD,14));

        addressField2 = new JTextField();
        addressField2.setFont(new Font("Raleway", Font.BOLD,14));

        cityField = new JTextField();
        cityField.setFont(new Font("Raleway", Font.BOLD,14));

        pinField = new JTextField();
        pinField.setFont(new Font("Raleway", Font.BOLD,14));

        stateField = new JTextField();
        stateField.setFont(new Font("Raleway", Font.BOLD,14));

        nextBtn = new JButton("NEXT");
        nextBtn.setFont(new Font("Raleway", Font.BOLD,14));
        nextBtn.setBackground(Color.BLACK);
        nextBtn.setForeground(Color.WHITE);

        maleBtn = new JRadioButton("Male");
        maleBtn.setFont(new Font("Raleway",Font.BOLD,14));
        maleBtn.setBackground(Color.WHITE);

        femaleBtn = new JRadioButton("Female");
        femaleBtn.setFont(new Font("Raleway",Font.BOLD,14));
        femaleBtn.setBackground(Color.WHITE);

        otherBtn1 = new JRadioButton("Other");
        otherBtn1.setFont(new Font("Raleway",Font.BOLD,14));
        otherBtn1.setBackground(Color.WHITE);


        marriedBtn = new JRadioButton("Married");
        marriedBtn.setFont(new Font("Raleway",Font.BOLD,14));
        marriedBtn.setBackground(Color.WHITE);

        unMarriedBtn = new JRadioButton("Unmarried");
        unMarriedBtn.setFont(new Font("Raleway",Font.BOLD,14));
        unMarriedBtn.setBackground(Color.WHITE);

        otherBtn2 = new JRadioButton("Other");
        otherBtn2.setFont(new Font("Raleway",Font.BOLD,14));
        otherBtn2.setBackground(Color.WHITE);

        int max_year_diff = 200;
        Integer[] birthYear = new Integer[max_year_diff];
        int curr_year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i=0;i<=max_year_diff-1;i++)
        {
            birthYear[i] = curr_year;
            curr_year--;
        }
        year = new JComboBox<>(birthYear);

        String[] months = {"January","February","March","April","May","JUne",
        "July","August","September","October","November","December"};
        month = new JComboBox<>(months);


        Integer[] dates = new Integer[31];
        for(int i=0;i<31;i++)
            dates[i]= i+1;
        day = new JComboBox<>(dates);


        setLayout(null);
        applicationLabel.setBounds(220,20,600,40);
        add(applicationLabel);

        headingLabel.setBounds(290,70,600,30);
        add(headingLabel);

        nameLabel.setBounds(100,140,100,30);
        add(nameLabel);

        nameField.setBounds(300,140,400,30);
        add(nameField);

        fatherNameLabel.setBounds(100,190,200,30);
        add(fatherNameLabel);

        fatherNameField.setBounds(300,190,400,30);
        add(fatherNameField);

        DOBLabel.setBounds(100,240,200,30);
        add(DOBLabel);

        dateLabel.setBounds(300,240,70,30);
        add(dateLabel);

        day.setBounds(370,240,50,30);
        add(day);

        monthLabel.setBounds(430,240,70,30);
        add(monthLabel);

        month.setBounds(500,240,90,30);
        add(month);

        yearLabel.setBounds(600,240,60,30);
        add(yearLabel);

        year.setBounds(660,240,70,30);
        add(year);

        genderLabel.setBounds(100,290,200,30);
        add(genderLabel);

        maleBtn.setBounds(300,290,90,30);
        add(maleBtn);

        femaleBtn.setBounds(450,290,90,30);
        add(femaleBtn);

        otherBtn1.setBounds(635,290,90,30);
        add(otherBtn1);

        genderStatus = new ButtonGroup();
        genderStatus.add(maleBtn);
        genderStatus.add(femaleBtn);
        genderStatus.add(otherBtn1);

        emailLabel.setBounds(100,340,200,30);
        add(emailLabel);

        emailField.setBounds(300,340,400,30);
        add(emailField);


        mStatusLabel.setBounds(100,390,200,30);
        add(mStatusLabel);

        marriedBtn.setBounds(300,390,100,30);
        add(marriedBtn);

        unMarriedBtn.setBounds(450,390,150,30);
        add(unMarriedBtn);

        otherBtn2.setBounds(635,390,100,30);
        add(otherBtn2);

        maritalStatus = new ButtonGroup();
        maritalStatus.add(marriedBtn);
        maritalStatus.add(unMarriedBtn);
        maritalStatus.add(otherBtn2);

        addressLabel1.setBounds(100,440,200,30);
        add(addressLabel1);

        addressField1.setBounds(300,440,400,30);
        add(addressField1);

        addressLabel2.setBounds(100,490,200,30);
        add(addressLabel2);

        addressField2.setBounds(300,490,400,30);
        add(addressField2);

        cityLabel.setBounds(100,540,200,30);
        add(cityLabel);

        cityField.setBounds(300,540,400,30);
        add(cityField);

        pinLabel.setBounds(100,590,200,30);
        add(pinLabel);

        pinField.setBounds(300,590,400,30);
        add(pinField);

        stateLabel.setBounds(100,640,200,30);
        add(stateLabel);

        stateField.setBounds(300,640,400,30);
        add(stateField);

        nextBtn.setBounds(620,690,80,30);
        add(nextBtn);

        footer.setBounds(300,720,500,30);
        add(footer);

        nextBtn.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setSize(800,800);
        setLocation(500,90);
        setVisible(true);


        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                if(!Character.isAlphabetic(ch) & ch!=' ' & e.getKeyCode()!=KeyEvent.VK_BACK_SPACE ){
                    JOptionPane.showMessageDialog(null,"Only characters are allowed");
                    nameField.setText(nameField.getText().substring(0,nameField.getText().length()-1));
                }
            }
        });

        fatherNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                if(!Character.isAlphabetic(ch) & ch!=' ' & e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
                    JOptionPane.showMessageDialog(null,"Only characters are allowed");
                    fatherNameField.setText(fatherNameField.getText().substring(0,fatherNameField.getText().length()-1));
                }
            }
        });

        pinField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                if(Character.isAlphabetic(ch)){
                    JOptionPane.showMessageDialog(null,"Only numbers are allowed");
                    pinField.setText(pinField.getText().substring(0,pinField.getText().length()-1));
                }
            }
        });
    }

    public void actionPerformed(ActionEvent actionEvent) {


        try{
            name = nameField.getText();
            fatherName = fatherNameField.getText();
            address1 = addressField1.getText();
            city = cityField.getText();
            state = stateField.getText();
            if(pinField.getText().equals("") || pinField.getText()==null)
                throw new EmptyInputException("* MARKED INPUTS CANNOT BE EMPTY");
            else
                pinCode = Integer.parseInt(pinField.getText());
            genderStatusValue = Utils.getSelectedButtonText(genderStatus);
            maritalStatusValue = Utils.getSelectedButtonText(maritalStatus);
            address2 = addressField2.getText();
            if(address2.equals(""))
                address2=null;
            email = emailField.getText();
            Pattern pattern;
            Matcher matcher;

            if(!email.equals("")) {
                pattern = Pattern.compile("^.+@.+\\..+$");
                matcher = pattern.matcher(email);
                if (!matcher.matches())
                    throw new EmptyInputException("Email is invalid! Please check");
            }
            else
            {
                email=null;
            }
            if(name == null || name.equals("") || fatherName == null || fatherName.equals("") || address1 == null || address1.equals("") || city == null || city.equals("") || state==null || state.equals("") || genderStatusValue==null || maritalStatusValue == null)
                throw new EmptyInputException("* MARKED INPUTS CANNOT BE EMPTY");
            if(year.getSelectedItem() ==null || day.getSelectedItem()==null || month.getSelectedItem()==null)
                throw new EmptyInputException("DOB CANNOT BE EMPTY");
            dob = new GregorianCalendar( (int)year.getSelectedItem(),month.getSelectedIndex(),(int)day.getSelectedItem());
            new Signup2(ApplicationNo,this,database).setVisible(true);
            setVisible(false);
        }
        catch (EmptyInputException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("error "+e);
        }
    }
}

