//Mohammad Zohaib Abbas, 17L-6305, l176305@lhr.nu.edu.pk, AP-8A

package ap_assign_4;

import java.net.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
/**
 *
 * @author mzohaib
 */
public class Client extends Thread {

    private String serverMessage;
    private JFrame currFrame;
    
    public Client(Socket s, JFrame jFrame) {

            serverMessage=null;
            currFrame=jFrame;
            
            JPanel jPanel=new JPanel();
            JPanel jPanel2=new JPanel();

            //Create JFrame according to screen size, i.e Responsive
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = screenSize.height * 7/10;
            int width = screenSize.width * 35/100;
            jFrame.setPreferredSize(new Dimension(width, height));
            jFrame.setBackground(Color.decode("#00203777"));

            jPanel2.setBorder(new EmptyBorder(30,30,0,30)); // heading panel tlbr
            jPanel2.setBackground(Color.decode("#00203777"));

            JLabel headingLabel=new JLabel("SIGN IN");
            headingLabel.setForeground(Color.decode("#BFFFFF"));
            headingLabel.setFont(new Font("Courier", Font.PLAIN, 40));
            JPanel headPanel=new JPanel();
            headPanel.setPreferredSize(new Dimension(200,75));
            headPanel.setBackground(Color.decode("#00203777"));

            headPanel.add(headingLabel);
            jPanel2.add(headPanel);


            JPanel usrLabelJPanel=new JPanel();
            JLabel userLabel=new JLabel("Username");
            userLabel.setForeground(Color.decode("#BFFFFF"));
            userLabel.setFont(new Font("Courier", Font.PLAIN, 20));
            usrLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
            usrLabelJPanel.add(userLabel);

            JPanel passLabelJPanel=new JPanel();
            JLabel passLabel=new JLabel("Password");
            passLabel.setForeground(Color.decode("#BFFFFF"));
            passLabel.setFont(new Font("Courier", Font.PLAIN, 20));
            passLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
            passLabelJPanel.add(passLabel);

            JPanel usrFieldJPanel=new JPanel();
            JTextField userField=new JTextField();
            usrFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
            usrFieldJPanel.add(userField);

            JPanel passFieldJPanel=new JPanel();
            JTextField passField=new JTextField();
            passFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
            passFieldJPanel.add(passField);

            JPanel btnJPanel=new JPanel();
            JButton loginButton=new JButton("Sign In");
            btnJPanel.setBorder(new EmptyBorder(0,30,0,30));
            btnJPanel.setBackground(Color.decode("#00203777"));

            btnJPanel.add(loginButton);
            loginButton.setPreferredSize(new Dimension(440,50));
            loginButton.setFont(new Font("Courier", Font.PLAIN,20));
            loginButton.setForeground(Color.decode("#00203777"));
            loginButton.setOpaque(true);
            loginButton.setBackground(Color.decode("#BBFFFF"));

            loginButton.addMouseListener(new MouseInputListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {
                    loginButton.setForeground(Color.decode("#03a9f4"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    loginButton.setForeground(Color.decode("#00203777"));
                }

                @Override
                public void mouseDragged(MouseEvent e) {}

                @Override
                public void mouseMoved(MouseEvent e) {}
            });

            loginButton.addActionListener((ActionEvent e) -> {
                if(userField.getText().isEmpty()||passField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jFrame, "Error! Kindly Fill All Fields");
                }
                else{
                    int i=0;
                    while(i<2){
                        try{
                            PrintWriter pr=new PrintWriter(s.getOutputStream());
                            StringBuffer stringBuffer=new StringBuffer("1 "); // 1 indicates login request
                            stringBuffer.append(userField.getText()); 
                            stringBuffer.append(' ');
                            stringBuffer.append(passField.getText());

                            pr.println(stringBuffer.toString());
                            pr.flush();

                        }catch(IOException exception){
                            JOptionPane.showMessageDialog(jFrame, "Connection Error!");
                        }
                        i++;
                    }
                   
                }
            });

            JPanel signupPanel=new JPanel();
            signupPanel.setBorder(new EmptyBorder(5,0,0,0));
            signupPanel.setBackground(Color.decode("#00203777"));
            signupPanel.setLayout(new GridLayout(3,0));
            JLabel signUpJLabel=new JLabel("Don't have an account? Click Here");
            signUpJLabel.setBorder(new EmptyBorder(0, 50, 10,0));
            signUpJLabel.setForeground(Color.decode("#BBBFFF"));
            signUpJLabel.setFont(new Font("Courier", Font.PLAIN, 16));
            signUpJLabel.addMouseListener(new MouseAdapter(){
              @Override
              public void mouseClicked(MouseEvent e) {
                jFrame.setVisible(false);
                signUpPage(jFrame, s);
              }
              @Override
                public void mouseEntered(MouseEvent e) {
                    signUpJLabel.setForeground(Color.decode("#BFFFFF"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    signUpJLabel.setForeground(Color.decode("#BBBFFF"));
                }
            });

            signupPanel.add(signUpJLabel);

            JLabel changeUsernameJLabel=new JLabel("To Update Username, Click Here");
            changeUsernameJLabel.setForeground(Color.decode("#BBBFFF"));
            changeUsernameJLabel.setBorder(new EmptyBorder(0, 65, 10,0));
            changeUsernameJLabel.setFont(new Font("Courier", Font.PLAIN, 16));
            changeUsernameJLabel.addMouseListener(new MouseAdapter(){
              @Override
              public void mouseClicked(MouseEvent e) {
                jFrame.setVisible(false);
                changeUsernamePage(jFrame,s);
              }
              @Override
                public void mouseEntered(MouseEvent e) {
                    changeUsernameJLabel.setForeground(Color.decode("#BFFFFF"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    changeUsernameJLabel.setForeground(Color.decode("#BBBFFF"));
                }
            });

            JLabel changePassJLabel=new JLabel("To Update Password, Click Here");
            changePassJLabel.setForeground(Color.decode("#BBBFFF"));
            changePassJLabel.setBorder(new EmptyBorder(0, 65, 10,0));
            changePassJLabel.setFont(new Font("Courier", Font.PLAIN, 16));
            changePassJLabel.addMouseListener(new MouseAdapter(){
              @Override
              public void mouseClicked(MouseEvent e) {
                jFrame.setVisible(false);
                  changePassPage(jFrame,s);
              }
              @Override
                public void mouseEntered(MouseEvent e) {
                    changePassJLabel.setForeground(Color.decode("#BFFFFF"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    changePassJLabel.setForeground(Color.decode("#BBBFFF"));
                }
            });

            signupPanel.add(changeUsernameJLabel);
            signupPanel.add(changePassJLabel);

            jPanel.setBorder(new EmptyBorder(0,30,0,30)); 
            jPanel.setBackground(Color.decode("#00203777"));

            jPanel.add(userLabel); jPanel.add(userField);
            jPanel.add(passLabel); jPanel.add(passField);


            jPanel.setLayout(new GridLayout(4,1,0,10));

            JPanel jPanel3=new JPanel();
            jPanel3.setBorder(new EmptyBorder(30,30,40,30));
            jPanel3.setBackground(Color.decode("#00203777"));
            jPanel3.add(btnJPanel);
            jPanel3.add(signupPanel);
            jPanel3.setLayout(new GridLayout(2,0,0,0));


            jFrame.add(jPanel3,BorderLayout.PAGE_END);
            jFrame.add(jPanel,BorderLayout.CENTER);
            jFrame.add(jPanel2,BorderLayout.PAGE_START);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setTitle("Login Form");
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
            jFrame.setResizable(false);
        
        
    }
    
    public void signUpPage(JFrame jFrameLogin, Socket s){
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        JPanel jPanel2=new JPanel();
        
        //Create JFrame according to screen size, i.e Responsive
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 6/10;
        int width = screenSize.width * 35/100;
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setBackground(Color.decode("#00203777"));
        
        jPanel2.setBorder(new EmptyBorder(30,30,0,30)); // heading panel tlbr
        jPanel2.setBackground(Color.decode("#00203777"));

        JLabel headingLabel=new JLabel("SIGN UP");
        headingLabel.setForeground(Color.decode("#BFFFFF"));
        headingLabel.setFont(new Font("Courier", Font.PLAIN, 40));
        JPanel headPanel=new JPanel();
        headPanel.setPreferredSize(new Dimension(200,75));
        headPanel.setBackground(Color.decode("#00203777"));

        headPanel.add(headingLabel);
        jPanel2.add(headPanel);
        
        
        JPanel usrLabelJPanel=new JPanel();
        JLabel userLabel=new JLabel("Username");
        userLabel.setForeground(Color.decode("#BFFFFF"));
        userLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        usrLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        usrLabelJPanel.add(userLabel);

        JPanel passLabelJPanel=new JPanel();
        JLabel passLabel=new JLabel("Password");
        passLabel.setForeground(Color.decode("#BFFFFF"));
        passLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        passLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        passLabelJPanel.add(passLabel);
        
        JPanel usrFieldJPanel=new JPanel();
        JTextField userField=new JTextField();
        usrFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        usrFieldJPanel.add(userField);
        
        JPanel passFieldJPanel=new JPanel();
        JTextField passField=new JTextField();
        passFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        passFieldJPanel.add(passField);
        
        JPanel btnJPanel=new JPanel();
        JButton signUpButton=new JButton("Sign Up");
        btnJPanel.setBorder(new EmptyBorder(0,30,0,30));
        btnJPanel.setBackground(Color.decode("#00203777"));
        
        btnJPanel.add(signUpButton);
        signUpButton.setPreferredSize(new Dimension(440,50));
        signUpButton.setFont(new Font("Courier", Font.PLAIN,20));
        signUpButton.setForeground(Color.decode("#00203777"));
        signUpButton.setOpaque(true);
        signUpButton.setBackground(Color.decode("#BBFFFF"));
        
        signUpButton.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                signUpButton.setForeground(Color.decode("#03a9f4"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpButton.setForeground(Color.decode("#00203777"));
            }

            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        
        JLabel signUpJLabel=new JLabel("Already have an Account? Sign In");
        signUpJLabel.setForeground(Color.decode("#BBBFFF"));
        signUpJLabel.setBorder(new EmptyBorder(0, 55, 20,0));
        signUpJLabel.setFont(new Font("Courier", Font.PLAIN, 16));
        signUpJLabel.addMouseListener(new MouseAdapter(){
          @Override
          public void mouseClicked(MouseEvent e) {
            jFrame.setVisible(false);
            jFrameLogin.setVisible(true);
            currFrame=jFrameLogin;
          }
          @Override
            public void mouseEntered(MouseEvent e) {
                signUpJLabel.setForeground(Color.decode("#BFFFFF"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpJLabel.setForeground(Color.decode("#BBBFFF"));
            }
        });
        
        signUpButton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                if(userField.getText().isEmpty()||passField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jFrame, "Error! Kindly Fill All Fields");
                }
                else{
                    if(userField.getText().length()>20){
                        JOptionPane.showMessageDialog(jFrame, "Error! Username Can Not be greater than 20 Characters!");
                        return;
                    }
                    if(passField.getText().length()<4||passField.getText().length()>10){
                        JOptionPane.showMessageDialog(jFrame, "Error! Password should be 4 to 10 character long only!");
                        return;
                    }
                    int i=0;
                    while(i<2){
                        try{
                            PrintWriter pr=new PrintWriter(s.getOutputStream());
                            StringBuffer stringBuffer=new StringBuffer("2 "); // 2 indicates sign up request
                            stringBuffer.append(userField.getText()); 
                            stringBuffer.append(' ');
                            stringBuffer.append(passField.getText());

                            pr.println(stringBuffer.toString());
                            pr.flush();

                        }catch(IOException exception){
                            JOptionPane.showMessageDialog(jFrame, "Connection Error!");
                        }
                        i++;
                    }
                }
            }});
        
        jPanel.setBorder(new EmptyBorder(0,30,0,30)); 
        jPanel.setBackground(Color.decode("#00203777"));
        
        jPanel.add(userLabel); jPanel.add(userField);
        jPanel.add(passLabel); jPanel.add(passField);
        
        
        jPanel.setLayout(new GridLayout(4,1,0,10));
        
        JPanel jPanel3=new JPanel();
        jPanel3.setBorder(new EmptyBorder(30,30,30,30));
        jPanel3.setBackground(Color.decode("#00203777"));
        jPanel3.add(btnJPanel);
        jPanel3.add(signUpJLabel);
        jPanel3.setLayout(new GridLayout(2,1,0,10));
        

        jFrame.add(jPanel3,BorderLayout.PAGE_END);
        jFrame.add(jPanel,BorderLayout.CENTER);
        jFrame.add(jPanel2,BorderLayout.PAGE_START);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Sign Up Form");
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        currFrame=jFrame;
    }
    
    public void changeUsernamePage(JFrame jFrameLogin, Socket s){
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        JPanel jPanel2=new JPanel();
        
        //Create JFrame according to screen size, i.e Responsive
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 6/10;
        int width = screenSize.width * 35/100;
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setBackground(Color.decode("#00203777"));
        
        jPanel2.setBorder(new EmptyBorder(20,30,10,30)); // heading panel tlbr
        jPanel2.setBackground(Color.decode("#00203777"));

        JLabel headingLabel=new JLabel("Account Settings");
        headingLabel.setForeground(Color.decode("#BFFFFF"));
        headingLabel.setBorder(new EmptyBorder(0,50,0,50));
        headingLabel.setFont(new Font("Courier", Font.PLAIN, 30));
        JPanel headPanel=new JPanel();
        headPanel.setPreferredSize(new Dimension(500,50));
        headPanel.setBackground(Color.decode("#00203777"));
        
        JButton backBtn=new JButton("<<");
        backBtn.setFont(new Font("Courier", Font.PLAIN,15));
        backBtn.setPreferredSize(new Dimension(40,20));
        backBtn.setForeground(Color.decode("#00203777"));
        backBtn.setOpaque(true);
        backBtn.setBackground(Color.decode("#BBFFFF"));
        
        backBtn.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                backBtn.setForeground(Color.decode("#03a9f4"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBtn.setForeground(Color.decode("#00203777"));
            }

            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        
        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                jFrameLogin.setVisible(true);
                currFrame=jFrameLogin;
            }
        });
        
        headPanel.add(backBtn);
        headPanel.add(headingLabel);
        jPanel2.add(headPanel);
        
        
        JPanel usrLabelJPanel=new JPanel();
        JLabel userLabel=new JLabel("Old Username");
        userLabel.setForeground(Color.decode("#BFFFFF"));
        userLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        usrLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        usrLabelJPanel.add(userLabel);
        
        JPanel newUsrLabelJPanel=new JPanel();
        JLabel newUserLabel=new JLabel("New Username");
        newUserLabel.setForeground(Color.decode("#BFFFFF"));
        newUserLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        newUsrLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        newUsrLabelJPanel.add(newUserLabel);

        JPanel passLabelJPanel=new JPanel();
        JLabel passLabel=new JLabel("Password");
        passLabel.setForeground(Color.decode("#BFFFFF"));
        passLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        passLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        passLabelJPanel.add(passLabel);
        
        JPanel usrFieldJPanel=new JPanel();
        JTextField userField=new JTextField();
        usrFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        usrFieldJPanel.setPreferredSize(new Dimension(440,30));
        usrFieldJPanel.add(userField);
        
        JPanel newUsrFieldJPanel=new JPanel();
        JTextField newUserField=new JTextField();
        newUsrFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        newUsrFieldJPanel.setPreferredSize(new Dimension(440,30));
        newUsrFieldJPanel.add(userField);
        
        JPanel passFieldJPanel=new JPanel();
        JTextField passField=new JTextField();
        passFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        passFieldJPanel.setPreferredSize(new Dimension(440,30));
        passFieldJPanel.add(passField);
        
        JPanel btnJPanel=new JPanel();
        JButton submitButton=new JButton("Submit");
        btnJPanel.setBorder(new EmptyBorder(0,30,30,30));
        btnJPanel.setBackground(Color.decode("#00203777"));
        
        btnJPanel.add(submitButton);
        submitButton.setPreferredSize(new Dimension(440,50));
        submitButton.setFont(new Font("Courier", Font.PLAIN,20));
        submitButton.setForeground(Color.decode("#00203777"));
        submitButton.setOpaque(true);
        submitButton.setBackground(Color.decode("#BBFFFF"));
        
        submitButton.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setForeground(Color.decode("#03a9f4"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setForeground(Color.decode("#00203777"));
            }

            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userField.getText().isEmpty()||passField.getText().isEmpty()||newUserField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jFrame, "Error! Kindly Fill All Fields");
                }
                else if(userField.getText().equals(newUserField.getText())){
                    JOptionPane.showMessageDialog(jFrame, "Error! Kindly Enter a New Username");
                }
                else{
                    int i=0;
                    while(i<2){
                        try{
                            PrintWriter pr=new PrintWriter(s.getOutputStream());
                            StringBuffer stringBuffer=new StringBuffer("3 "); // 3 indicates changge username request
                            stringBuffer.append(userField.getText()); 
                            stringBuffer.append(' ');
                            stringBuffer.append(newUserField.getText()); 
                            stringBuffer.append(' ');
                            stringBuffer.append(passField.getText());

                            pr.println(stringBuffer.toString());
                            pr.flush();

                        }catch(IOException exception){
                            JOptionPane.showMessageDialog(jFrame, "Connection Error!");
                        }
                        i++;
                    }
                }
            }
        });
        
        jPanel.setBorder(new EmptyBorder(0,30,40,30)); 
        jPanel.setBackground(Color.decode("#00203777"));
        
        jPanel.add(userLabel); jPanel.add(userField);
        jPanel.add(newUserLabel); jPanel.add(newUserField);
        jPanel.add(passLabel); jPanel.add(passField);
        
        
        jPanel.setLayout(new GridLayout(6,1,0,0));
        
        jFrame.add(btnJPanel,BorderLayout.PAGE_END);
        jFrame.add(jPanel,BorderLayout.CENTER);
        jFrame.add(jPanel2,BorderLayout.PAGE_START);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Sign Up Form");
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        currFrame=jFrame;
    }
    
    public void changePassPage(JFrame jFrameLogin,Socket s){
        JFrame jFrame=new JFrame();
        JPanel jPanel=new JPanel();
        JPanel jPanel2=new JPanel();
        
        //Create JFrame according to screen size, i.e Responsive
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 6/10;
        int width = screenSize.width * 35/100;
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setBackground(Color.decode("#00203777"));
        
        jPanel2.setBorder(new EmptyBorder(20,30,10,30)); // heading panel tlbr
        jPanel2.setBackground(Color.decode("#00203777"));

        JLabel headingLabel=new JLabel("Account Settings");
        headingLabel.setForeground(Color.decode("#BFFFFF"));
        headingLabel.setBorder(new EmptyBorder(0,50,0,50));
        headingLabel.setFont(new Font("Courier", Font.PLAIN, 30));
        JPanel headPanel=new JPanel();
        headPanel.setPreferredSize(new Dimension(500,50));
        headPanel.setBackground(Color.decode("#00203777"));
        
        JButton backBtn=new JButton("<<");
        backBtn.setFont(new Font("Courier", Font.PLAIN,15));
        backBtn.setPreferredSize(new Dimension(40,20));
        backBtn.setForeground(Color.decode("#00203777"));
        backBtn.setOpaque(true);
        backBtn.setBackground(Color.decode("#BBFFFF"));
        
        backBtn.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                backBtn.setForeground(Color.decode("#03a9f4"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backBtn.setForeground(Color.decode("#00203777"));
            }

            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        
        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
                jFrameLogin.setVisible(true);
                currFrame=jFrameLogin;
            }
        });
        
        headPanel.add(backBtn);
        headPanel.add(headingLabel);
        jPanel2.add(headPanel);
        
        
        JPanel usrLabelJPanel=new JPanel();
        JLabel userLabel=new JLabel("Username");
        userLabel.setForeground(Color.decode("#BFFFFF"));
        userLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        usrLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        usrLabelJPanel.add(userLabel);
        
        JPanel newPassLabelJPanel=new JPanel();
        JLabel newPassLabel=new JLabel("New Password");
        newPassLabel.setForeground(Color.decode("#BFFFFF"));
        newPassLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        newPassLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        newPassLabelJPanel.add(newPassLabel);

        JPanel passLabelJPanel=new JPanel();
        JLabel passLabel=new JLabel("Old Password");
        passLabel.setForeground(Color.decode("#BFFFFF"));
        passLabel.setFont(new Font("Courier", Font.PLAIN, 20));
        passLabelJPanel.setBorder(new EmptyBorder(0,30,0,30));
        passLabelJPanel.add(passLabel);
        
        JPanel usrFieldJPanel=new JPanel();
        JTextField userField=new JTextField();
        usrFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        usrFieldJPanel.setPreferredSize(new Dimension(440,30));
        usrFieldJPanel.add(userField);
        
        JPanel newPassFieldJPanel=new JPanel();
        JTextField newPassField=new JTextField();
        newPassFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        newPassFieldJPanel.setPreferredSize(new Dimension(440,30));
        newPassFieldJPanel.add(newPassField);
        
        JPanel passFieldJPanel=new JPanel();
        JTextField passField=new JTextField();
        passFieldJPanel.setBorder(new EmptyBorder(0,30,0,30));
        passFieldJPanel.setPreferredSize(new Dimension(440,30));
        passFieldJPanel.add(passField);
        
        JPanel btnJPanel=new JPanel();
        JButton submitButton=new JButton("Submit");
        btnJPanel.setBorder(new EmptyBorder(0,30,30,30));
        btnJPanel.setBackground(Color.decode("#00203777"));
        
        btnJPanel.add(submitButton);
        submitButton.setPreferredSize(new Dimension(440,50));
        submitButton.setFont(new Font("Courier", Font.PLAIN,20));
        submitButton.setForeground(Color.decode("#00203777"));
        submitButton.setOpaque(true);
        submitButton.setBackground(Color.decode("#BBFFFF"));
        
        submitButton.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                submitButton.setForeground(Color.decode("#03a9f4"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                submitButton.setForeground(Color.decode("#00203777"));
            }

            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userField.getText().isEmpty()||passField.getText().isEmpty()||newPassField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(jFrame, "Error! Kindly Fill All Fields");
                }
                else if(passField.getText().equals(newPassField.getText())){
                    JOptionPane.showMessageDialog(jFrame, "Error! Kindly Enter a New Password");
                }
                else{
                    int i=0;
                    while(i<2){
                        try{
                            PrintWriter pr=new PrintWriter(s.getOutputStream());
                            StringBuffer stringBuffer=new StringBuffer("4 "); // 4 indicates change password request
                            stringBuffer.append(userField.getText()); 
                            stringBuffer.append(' ');
                            stringBuffer.append(newPassField.getText()); 
                            stringBuffer.append(' ');
                            stringBuffer.append(passField.getText());

                            pr.println(stringBuffer.toString());
                            pr.flush();

                        }catch(IOException exception){
                            JOptionPane.showMessageDialog(jFrame, "Connection Error!");
                        }
                        i++;
                    }
                }
            }
        });
        
        jPanel.setBorder(new EmptyBorder(0,30,40,30)); 
        jPanel.setBackground(Color.decode("#00203777"));
        
        jPanel.add(userLabel); jPanel.add(userField);
        jPanel.add(passLabel); jPanel.add(passField);
        jPanel.add(newPassLabel); jPanel.add(newPassField);
        
        
        jPanel.setLayout(new GridLayout(6,1,0,0));
        
        jFrame.add(btnJPanel,BorderLayout.PAGE_END);
        jFrame.add(jPanel,BorderLayout.CENTER);
        jFrame.add(jPanel2,BorderLayout.PAGE_START);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Sign Up Form");
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        currFrame=jFrame;
    }
    
    public void setServerMessage(String str){
        serverMessage=str;
        if(serverMessage!=null){
            JOptionPane.showMessageDialog(currFrame, "Server Says: "+serverMessage);
        }
    }
    
    public static void main(String[] args) {
        boolean shouldRun=true;
        try{
            JFrame mainframe=new JFrame();
            Socket s=new Socket("localhost",4999);
            InputStreamReader in = new InputStreamReader(s.getInputStream()); // To receive message from server
            BufferedReader bf=new BufferedReader(in);
            Client client=new Client(s,mainframe);
            while(shouldRun){
                String str=bf.readLine();
                if(str==null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    client.setServerMessage(str);
                }
            }
            in.close();
            bf.close();
            s.close();
        }
        catch(IOException e){
            System.err.println("Connection Error");
        }
    }
    
}
