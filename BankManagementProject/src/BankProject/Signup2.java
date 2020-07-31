package BankProject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Signup2 extends JFrame implements ActionListener{

    JLabel headingLabel,religionLabel,categoryLabel,incomeLabel,qualificationLabel,occupationLabel,panLabel,
            aadhaarLabel,seniorLabel,accountLabel,applicationNoLabel,applicationNoValueLabel,footer;
    JButton nextBtn;
    JRadioButton r1,r2,r3,r4;
    JTextField panInputField,aadhaarInputField;
    JComboBox <String>religionInputField,categoryInputField,incomeInputField,qualificationInputField,occupationInputField;
    ButtonGroup seniority,account;
    String ApplicationNo;
    Signup signup;
    String database;


    Signup2(String ApplicationNo,Signup signUp,String database){
        this.database = database;
        this.ApplicationNo = ApplicationNo;
        this.signup = signUp;
        setFont(new Font("System", Font.BOLD, 22));
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");



        headingLabel = new JLabel("Page 2: Additional Details");
        headingLabel.setFont(new Font("Raleway", Font.BOLD, 22));

        religionLabel = new JLabel("*Religion:");
        religionLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        categoryLabel = new JLabel("*Category:");
        categoryLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        incomeLabel = new JLabel("*Income:");
        incomeLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        qualificationLabel = new JLabel("*Qualification:");
        qualificationLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        occupationLabel = new JLabel("*Occupation:");
        occupationLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        panLabel = new JLabel("*PAN Number:");
        panLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        aadhaarLabel = new JLabel("*Aadhaar Number:");
        aadhaarLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        seniorLabel = new JLabel("*Senior Citizen:");
        seniorLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        accountLabel = new JLabel("*Existing Account:");
        accountLabel.setFont(new Font("Raleway", Font.BOLD, 18));

        applicationNoLabel = new JLabel("Application Number :");
        applicationNoLabel.setFont(new Font("Raleway", Font.BOLD, 14));
        applicationNoValueLabel = new JLabel(this.ApplicationNo);
        applicationNoValueLabel.setFont(new Font("Raleway", Font.BOLD, 14));


        footer = new JLabel("* marked inputs MUST be filled");
        footer.setFont(new Font("Raleway", Font.BOLD,14));

        nextBtn = new JButton("Next");
        nextBtn.setFont(new Font("Raleway", Font.BOLD, 14));
        nextBtn.setBackground(Color.BLACK);
        nextBtn.setForeground(Color.WHITE);


        panInputField = new JTextField();
        panInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        aadhaarInputField = new JTextField();
        aadhaarInputField.setFont(new Font("Raleway", Font.BOLD, 14));



        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);

        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);

        seniority = new ButtonGroup();
        seniority.add(r1);
        seniority.add(r2);

        r3 = new JRadioButton("Yes");
        r3.setFont(new Font("Raleway", Font.BOLD, 14));
        r3.setBackground(Color.WHITE);

        r4 = new JRadioButton("No");
        r4.setFont(new Font("Raleway", Font.BOLD, 14));
        r4.setBackground(Color.WHITE);

        account = new ButtonGroup();
        account.add(r3);
        account.add(r4);


        String[] religion = {"Hindu","Muslim","Sikh","Christian","Other"};
        religionInputField = new JComboBox<>(religion);
        religionInputField.setBackground(Color.WHITE);
        religionInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] category = {"General","OBC","SC","ST","Other"};
        categoryInputField = new JComboBox<>(category);
        categoryInputField.setBackground(Color.WHITE);
        categoryInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] income = {"Null","<1,50,000","<2,50,000","<5,00,000","Up-to 10,00,000","Above 10,00,000"};
        incomeInputField = new JComboBox<>(income);
        incomeInputField.setBackground(Color.WHITE);
        incomeInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] education = {"Non-Graduate","Graduate","Post-Graduate","Doctorate","Others"};
        qualificationInputField = new JComboBox<>(education);
        qualificationInputField.setBackground(Color.WHITE);
        qualificationInputField.setFont(new Font("Raleway", Font.BOLD, 14));

        String[] occupation = {"Salaried","Self-Employed","Business","Student","Retired","Others"};
        occupationInputField = new JComboBox<>(occupation);
        occupationInputField.setBackground(Color.WHITE);
        occupationInputField.setFont(new Font("Raleway", Font.BOLD, 14));


        setLayout(null);


        applicationNoLabel.setBounds(600,70,200,30);
        add(applicationNoLabel);

        applicationNoValueLabel.setBounds(770,70,60,30);
        add(applicationNoValueLabel);

        headingLabel.setBounds(280,30,600,40);
        add(headingLabel);

        religionLabel.setBounds(100,120,100,30);
        add(religionLabel);

        religionInputField.setBounds(350,120,320,30);
        add(religionInputField);

        categoryLabel.setBounds(100,170,150,30);
        add(categoryLabel);

        categoryInputField.setBounds(350,170,320,30);
        add(categoryInputField);

        incomeLabel.setBounds(100,220,100,30);
        add(incomeLabel);

        incomeInputField.setBounds(350,220,320,30);
        add(incomeInputField);

        qualificationLabel.setBounds(100,270,150,30);
        add(qualificationLabel);

        qualificationInputField.setBounds(350,270,320,30);
        add(qualificationInputField);


        occupationLabel.setBounds(100,320,150,30);
        add(occupationLabel);

        occupationInputField.setBounds(350,320,320,30);
        add(occupationInputField);

        panLabel.setBounds(100,370,150,30);
        add(panLabel);

        panInputField.setBounds(350,370,320,30);
        add(panInputField);

        aadhaarLabel.setBounds(100,420,200,30);
        add(aadhaarLabel);

        aadhaarInputField.setBounds(350,420,320,30);
        add(aadhaarInputField);

        seniorLabel.setBounds(100,470,200,30);
        add(seniorLabel);

        r1.setBounds(350,470,100,30);
        add(r1);

        r2.setBounds(460,470,100,30);
        add(r2);

        accountLabel.setBounds(100,520,200,30);
        add(accountLabel);

        r3.setBounds(350,520,100,30);
        add(r3);

        r4.setBounds(460,520,100,30);
        add(r4);

        nextBtn.setBounds(570,600,100,30);
        add(nextBtn);

        nextBtn.addActionListener(this);

        footer.setBounds(300,720,500,30);
        add(footer);

        getContentPane().setBackground(Color.WHITE);

        setSize(800,800);
        setLocation(500,90);
        setVisible(true);

        aadhaarInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char ch = e.getKeyChar();
                if(Character.isAlphabetic(ch)){
                    JOptionPane.showMessageDialog(null,"Only numbers are allowed");
                    aadhaarInputField.setText(aadhaarInputField.getText().substring(0,aadhaarInputField.getText().length()-1));
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae){

        String religion;
        String category;
        String income;
        String qualification;
        String PANno;
        String aadhaarNo;
        String seniorityValue;
        String existingAccount;
        try{
            religion = (String)religionInputField.getSelectedItem();
            category = (String)categoryInputField.getSelectedItem();
            income = (String)categoryInputField.getSelectedItem();
            qualification = (String)occupationInputField.getSelectedItem();
            PANno = panInputField.getText();
            aadhaarNo = aadhaarInputField.getText();
            seniorityValue = Utils.getSelectedButtonText(seniority);
            existingAccount = Utils.getSelectedButtonText(account);
            if(PANno == null || PANno.equals("") || aadhaarNo == null || aadhaarNo.equals("") || seniorityValue == null || existingAccount == null)
            {
                throw new EmptyInputException("* MARKED INPUTS CANNOT BE EMPTY");
            }
            int seniorityNumber = 0;
            if(seniorityValue.equals("Yes"))
            {
                seniorityNumber = 1;
            }
            int existingAccountNum = 0;
            if(existingAccount.equals("Yes"))
            {
                existingAccountNum=1;
            }
            java.sql.Date date= new java.sql.Date(signup.dob.getTimeInMillis());
            String query = " insert into PersonalDetails values('"+ApplicationNo+"','"+signup.name+"','"+signup.fatherName+"','"+date+"','"+signup.genderStatusValue+"','"+signup.email+"','"+signup.maritalStatusValue+"','"+signup.address1+"','"+signup.address2+"','"+signup.city+"','"+signup.pinCode+"','"+signup.state+"','"+religion+"','"+category+"','"+income+"','"+qualification+"','"+PANno+"','"+aadhaarNo+"','"+seniorityNumber+"','"+existingAccountNum+"')";
            Conn conn = new Conn(database);
            conn.setQuery(query);
            conn.executeStatement();
            if(conn.getResultCount==1 && !conn.querySuccessState)
            {
                System.out.println("Saved successfully into the PersonalDetails table");
                setVisible(false);
                new Signup3(this.ApplicationNo,database).setVisible(true);
            }
            else
            {
                System.out.println("Something is wrong");
                setVisible(false);
                new Login(database).setVisible(true);
            }
        }
        catch (EmptyInputException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        catch (Exception e){
            System.out.println("error "+e);
        }
    }
}