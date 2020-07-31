package BankProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class EmployeeLogin extends  JFrame implements ActionListener {
    JLabel headingText,employeeIdLabel,passwordLabel;
    JTextField employeeIdField;
    JPasswordField passwordInput;
    JButton loginBtn,clearBtn,exitBtn,signupBtn;
    String database;
    String EmployeeId;
    String Password;

    EmployeeLogin(String database)
    {
        setLayout(null);
        this.database = database;
        setFont(new Font("System", Font.BOLD, 28));
        setTitle("ADMIN");

        headingText = new JLabel("EMPLOYEE LOGIN");
        headingText.setFont(new Font("Osward", Font.BOLD, 38));

        employeeIdLabel = new JLabel("EMPLOYEE ID:");
        employeeIdLabel.setFont(new Font("Raleway", Font.BOLD, 28));

        passwordLabel = new JLabel("PASSWORD:");
        passwordLabel.setFont(new Font("Raleway", Font.BOLD, 28));

        employeeIdField = new JTextField(15);
        employeeIdField.setFont(new Font("Arial", Font.BOLD, 14));

        passwordInput = new JPasswordField(15);
        passwordInput.setFont(new Font("Arial", Font.BOLD, 14));

        loginBtn = new JButton("SIGN IN");
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);

        clearBtn = new JButton("CLEAR");
        clearBtn.setBackground(Color.BLACK);
        clearBtn.setForeground(Color.WHITE);

        signupBtn = new JButton("SIGN UP");
        signupBtn.setBackground(Color.BLACK);
        signupBtn.setForeground(Color.WHITE);

        exitBtn = new JButton("EXIT");
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setForeground(Color.WHITE);




        headingText.setBounds(250,50,600,200);
        add(headingText);

        employeeIdLabel.setBounds(125,150,375,200);
        add(employeeIdLabel);

        employeeIdField.setBounds(350,235,230,30);
        add(employeeIdField);

        passwordLabel.setBounds(125,225,375,200);
        add(passwordLabel);

        passwordInput.setBounds(350,310,230,30);
        add(passwordInput);

        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(350,400,100,30);
        add(loginBtn);

        clearBtn.setFont(new Font("Arial", Font.BOLD, 14));
        clearBtn.setBounds(480,400,100,30);
        add(clearBtn);

        signupBtn.setFont(new Font("Arial", Font.BOLD, 14));
        signupBtn.setBounds(350,450,230,30);
        add(signupBtn);

        exitBtn.setFont(new Font("Arial", Font.BOLD, 14));
        exitBtn.setBounds(350,500,230,30);
        add(exitBtn);

        loginBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        signupBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setSize(800,800);
        setLocation(500,200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try{
            EmployeeId  = employeeIdField.getText();
            Password  = String.valueOf(passwordInput.getPassword());

            if(ae.getSource()==loginBtn) {
                if (EmployeeId.equals(""))
                    throw new EmptyInputException("Invalid Account Number");
                Conn conn = new Conn(database);
                String query = "select * from EmployeePassword where employeeId = '" + EmployeeId + "' and password = '" + Password + "'";
                conn.setQuery(query);
                ResultSet solution = conn.executeStatement();
                int solutionCount = 0;
                if (conn.querySuccessState) {
                    while (solution.next()) {
                        solutionCount++;
                    }
                    if(solutionCount==1)
                    {
                        String adminUserPassword = Utils.getAdminPassword(EmployeeId,database);
                        if(adminUserPassword.equals(Password)) {
                            setVisible(false);
                            new EmployeeDetails(EmployeeId,database).setVisible(true);
                        }
                        else
                        {
                            throw new EmptyInputException("Account Number or pin is incorrect");
                        }
                    }
                    else if(solutionCount == 0)
                    {
                        throw new EmptyInputException("USERNAME OR PASSWORD IS INCORRECT");
                    }
                }
            }else if(ae.getSource()==clearBtn){
                employeeIdField.setText("");
                passwordInput.setText("");
            }else if(ae.getSource()==signupBtn){
                setVisible(false);
                new EmployeeSignup(database).setVisible(true);
            }
            else  if(ae.getSource() == exitBtn)
            {
                setVisible(false);
                new Login(database).setVisible(true);
            }
        }catch (EmptyInputException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            setVisible(false);
            new EmployeeLogin(database).setVisible(true);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("error: "+e);
        }

    }
}
