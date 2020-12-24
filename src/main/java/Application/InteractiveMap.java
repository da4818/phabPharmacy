import com.sun.java.swing.plaf.windows.WindowsComboBoxUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

public class InteractiveMap {
    // Icons
    Icon home = new ImageIcon("Icons/home.png");

    Icon aMap = new ImageIcon("Maps/a-map.png");
    Icon bMap = new ImageIcon("Maps/b-map.png");
    Icon cMap = new ImageIcon("Maps/c-map.png");
    Icon dMap = new ImageIcon("Maps/d-map.png");
    Icon eMap = new ImageIcon("Maps/e-map.png");
    Icon fMap = new ImageIcon("Maps/f-map.png");
    Icon map = new ImageIcon("Maps/map.png");

    Icon allergy = new ImageIcon("MedsInSection/allergy.png");
    Icon coldandflu = new ImageIcon("MedsInSection/coldandflu.png");
    Icon digestion = new ImageIcon("MedsInSection/digestion.png");
    Icon firstaid = new ImageIcon("MedsInSection/firstaid.png");
    Icon headaches = new ImageIcon("MedsInSection/headaches.png");
    Icon skincare = new ImageIcon("MedsInSection/skincare.png");
    // Labels
    JLabel currentUserLabel = new JLabel("Logged in as: ");
    JLabel currentUser = new JLabel();
    JLabel branchNameLabel = new JLabel("Branch: ");
    JLabel branchName = new JLabel();
    JLabel theMap = new JLabel(map);
    JLabel listOfMeds = new JLabel("Select section to reveal medication in section");
    // Buttons
    JButton toDashboardPage = new JButton("Back to Home",home);

    InteractiveMap(){
        // Initialising main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(800,500));
        mainPanel.setLayout(null);

// Adding the back to home button
        JPanel backToHome = new JPanel();
        backToHome.setBackground(white);
        backToHome.setBounds(20,12,140,30);

        toDashboardPage.setPreferredSize(new Dimension(135,25));
        toDashboardPage.setBorderPainted(false);
        toDashboardPage.setFocusPainted(false);
        toDashboardPage.setContentAreaFilled(true);
        toDashboardPage.setBackground(lightGray);

        backToHome.add(toDashboardPage);
        mainPanel.add(backToHome);

// Current User Details
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

// The maps
        JPanel maps = new JPanel();
        maps.setLayout(new GridLayout(1,1));
        maps.setBackground(white);
        maps.setBounds(400,60,356,400);

        maps.add(theMap);
        mainPanel.add(maps);

// Meds in this section
        JPanel medsInSectPnl = new JPanel();
        medsInSectPnl.setBorder(new CompoundBorder(new TitledBorder(null,"Medication in this Section",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,new Font(null,Font.BOLD,14)),new EmptyBorder(2,2,2,2)));
        //medsInSectPnl.setLayout(new GridLayout(1,1));
        medsInSectPnl.setBackground(white);
        listOfMeds.setFont(new Font(null,Font.ITALIC,12));

        medsInSectPnl.add(listOfMeds);
        medsInSectPnl.setBounds(50,150,290,medsInSectPnl.getPreferredSize().height);

        mainPanel.add(medsInSectPnl);

// Combo box
        JPanel mapSectionsPnl = new JPanel();
        mapSectionsPnl.setBackground(white);
        mapSectionsPnl.setLayout(new GridLayout(1,1));
        mapSectionsPnl.setBounds(50,80,290,60);
        mapSectionsPnl.setBorder(new CompoundBorder(BorderFactory.createTitledBorder(null,"Select Medication Section",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,new Font(null,Font.BOLD,14)),BorderFactory.createEmptyBorder(2,2,2,2)));
        String[] sections = {"","First Aid","Skincare","Headaches and Pain Relief","Digestion","Allergies","Cold and Flu"};
        JComboBox mapSections = new JComboBox(sections);

        Icon[] allMaps = {map,aMap,bMap,cMap,dMap,eMap,fMap};
        Icon[] allMedSections = {null,firstaid,skincare,headaches,digestion,allergy,coldandflu};

        mapSections.setUI(new WindowsComboBoxUI());
        mapSections.setBackground(lightGray);
        mapSections.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object section = mapSections.getSelectedItem();
                for (int i = 0; i < sections.length; i++) {
                    if (section == sections[i]) {
                        theMap.setIcon(allMaps[i]);
                        medsInSectPnl.setLayout(new GridLayout(1,1));
                        listOfMeds.setIcon(allMedSections[i]);
                        medsInSectPnl.setBounds(50,150,290,medsInSectPnl.getPreferredSize().height);
                    }
                }
            }
        });

        mapSectionsPnl.add(mapSections);
        mainPanel.add(mapSectionsPnl);

// Initialising frame
        JFrame frame = new JFrame("Phab Pharmacies - Find in Store");
        frame.setSize(800,530);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        mainPanel.setBackground(white);
        frame.setBackground(white);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setResizable(false);

// Back to home button
        toDashboardPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardPage dashboardPage = new DashboardPage();
                dashboardPage.currentUser.setText(currentUser.getText());
                dashboardPage.branchName.setText(branchName.getText());
                frame.setVisible(false);
            }
        });

    }
}
