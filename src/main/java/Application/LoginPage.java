import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.lightGray;
import static java.awt.Color.white;

public class LoginPage {
    // Icons
    Icon logo = new ImageIcon("Icons/logo.png");
    Icon greenpark = new ImageIcon("Icons/greenpark.png");
    Icon paddington = new ImageIcon("Icons/paddington.png");
    Icon milesend = new ImageIcon("Icons/milesend.png");
    // Labels
    JLabel userNameLbl = new JLabel("Username");
    JLabel passwordLbl = new JLabel("Password");
    JLabel title = new JLabel(logo);
    // Text Fields
    JTextField userName = new JTextField();
    JPasswordField password = new JPasswordField();
    // Button
    JButton paddingtonLogin = new JButton(" Paddington",paddington);
    JButton greenParkLogin = new JButton(" Green Park",greenpark);
    JButton milesEndLogin = new JButton(" Miles End",milesend);

    LoginPage(){
// Initialising main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setLayout(null);

// Adding the Phab Pharmacies Logo
        JPanel logoPnl = new JPanel();
        logoPnl.setBackground(white);
        logoPnl.setBounds(217,40,366,82);

        logoPnl.add(title);

        mainPanel.add(logoPnl);

// Initialising the labels and text fields
        JPanel credentialsPnl = new JPanel();
        credentialsPnl.setBackground(white);
        credentialsPnl.setLayout(new GridLayout(4,1,0,5));
        credentialsPnl.setBounds(210,160,380,150);

        JLabel[] allLabels = {userNameLbl,passwordLbl};
        for(int i=0;i<allLabels.length;i++){
            allLabels[i].setFont(new Font(null,Font.BOLD,14));
        }

        credentialsPnl.add(userNameLbl); credentialsPnl.add(userName);
        credentialsPnl.add(passwordLbl); credentialsPnl.add(password);
        mainPanel.add(credentialsPnl);

// Adding buttons to frame
        JPanel buttonPnl = new JPanel();
        buttonPnl.setBackground(white);
        buttonPnl.setLayout(new GridLayout(1,3,5,0));
        buttonPnl.setBounds(210,327,380,35);

        JButton[] allButtons = {paddingtonLogin,greenParkLogin,milesEndLogin};
        for(int i=0;i<allButtons.length;i++){
            allButtons[i].setContentAreaFilled(true);
            allButtons[i].setBorderPainted(false);
            allButtons[i].setFocusPainted(false);
            allButtons[i].setBackground(lightGray);
            allButtons[i].setMargin(new Insets(4,3,4,3));
            buttonPnl.add(allButtons[i]);
        }

        mainPanel.add(buttonPnl);

// Initialising frame
        JFrame frame = new JFrame("Phab Pharmacies - Login");
        frame.setSize(800,530);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel.setBackground(white);
        frame.setBackground(white);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setResizable(false);

        paddingtonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardPage dashboardPage = new DashboardPage();
                dashboardPage.branchName.setText("Paddington");
                dashboardPage.currentUser.setText(userName.getText());
                frame.setVisible(false);
            }
        });
        greenParkLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardPage dashboardPage = new DashboardPage();
                dashboardPage.branchName.setText("Green Park");
                dashboardPage.currentUser.setText(userName.getText());
                frame.setVisible(false);
            }
        });
        milesEndLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardPage dashboardPage = new DashboardPage();
                dashboardPage.branchName.setText("Miles End");
                dashboardPage.currentUser.setText(userName.getText());
                frame.setVisible(false);
            }
        });
    }
}
