import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.lightGray;
import static java.awt.Color.white;

public class DashboardPage {
    // Icons and Logo
    Icon logo = new ImageIcon("Icons/logo.png");
    Icon pos = new ImageIcon("Icons/pos.png");
    Icon login = new ImageIcon("Icons/login.png");
    Icon map = new ImageIcon("Icons/map.png");
    Icon onlineOrder = new ImageIcon("Icons/onlineorder.png");
    // Labels
    JLabel currentUserLabel = new JLabel("Logged in as: ");
    JLabel currentUser = new JLabel();
    JLabel branchNameLabel = new JLabel("Branch: ");
    JLabel branchName = new JLabel();
    JLabel title = new JLabel(logo);
    // Buttons
    JButton pointOfSaleBttn =  new JButton("Point of Sale",pos);
    JButton onlineOrdersBttn = new JButton("Online Orders",onlineOrder);
    JButton findProdInStoreBttn = new JButton("Find Product in Store",map);
    JButton logInPageBttn = new JButton("Log Out/Switch User",login);

    DashboardPage(){
// Initialising main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setLayout(null);

// Current user details
        JPanel crrntUserDet = new JPanel();
        crrntUserDet.setLayout(new GridLayout(2,2));
        crrntUserDet.setBounds(580,10,180,30);
        crrntUserDet.setBackground(white);

        JLabel[] crrntUserDetLbl = {currentUserLabel,branchNameLabel};
        JLabel[] crrntUserDetName = {currentUser,branchName};

        for(int i=0;i<crrntUserDetLbl.length;i++){
            crrntUserDetLbl[i].setFont(new Font(null,Font.BOLD,10));
            crrntUserDetLbl[i].setForeground(new Color(51,171,240));
            crrntUserDetName[i].setFont(new Font(null,Font.PLAIN,10));
            crrntUserDetName[i].setForeground(new Color(92,180,68));
            crrntUserDet.add(crrntUserDetLbl[i]);
            crrntUserDet.add(crrntUserDetName[i]);
        }

        mainPanel.add(crrntUserDet);

// Adding the Phab Pharmacies Logo
        JPanel logoPnl = new JPanel();
        logoPnl.setBackground(white);
        logoPnl.setBounds(217,40,366,82);

        logoPnl.add(title);

        mainPanel.add(logoPnl);

// Adding buttons
        JPanel bttnPanel = new JPanel();
        bttnPanel.setBackground(white);
        bttnPanel.setBounds(217,165,366,260);
        bttnPanel.setLayout(new GridLayout(4,1,5,10));

        JButton[] bttnArray = {pointOfSaleBttn,onlineOrdersBttn,findProdInStoreBttn,logInPageBttn};
        for(int i=0;i<bttnArray.length;i++){
            bttnArray[i].setContentAreaFilled(true);
            bttnArray[i].setBorderPainted(false);
            bttnArray[i].setFocusPainted(false);
            bttnArray[i].setBackground(lightGray);
            bttnArray[i].setFont(new Font(null,Font.BOLD,14));
            bttnPanel.add(bttnArray[i]);
        }

        mainPanel.add(bttnPanel);

// Initialising frame
        JFrame frame = new JFrame("Phab Pharmacies - Dashboard");
        frame.setSize(800,530);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel.setBackground(white);
        frame.setBackground(white);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setResizable(false);

// Assign functions to buttons
        pointOfSaleBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PointOfSalePage pointOfSalePage = new PointOfSalePage();
                pointOfSalePage.branchName.setText(branchName.getText());
                pointOfSalePage.currentUser.setText(currentUser.getText());
                frame.setVisible(false);
            }
        });
        onlineOrdersBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OnlineOrderList onlineOrderList = new OnlineOrderList();
                onlineOrderList.branchName.setText(branchName.getText());
                onlineOrderList.currentUser.setText(currentUser.getText());
                frame.setVisible(false);
            }
        });
        logInPageBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPage loginPage = new LoginPage();
                frame.setVisible(false);
            }
        });
        findProdInStoreBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InteractiveMap interactiveMap = new InteractiveMap();
                interactiveMap.branchName.setText(branchName.getText());
                interactiveMap.currentUser.setText(currentUser.getText());
                frame.setVisible(false);
            }
        });
    }
}
