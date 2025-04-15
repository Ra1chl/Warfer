package GameManager;

import GameManager.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    ImageIcon icon = new ImageIcon("Textures/nutria_icon.png");
    private Player player;

    JButton caveButton = new JButton();
    private JLabel background;

    Game(Player player){
        this.player = player;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(new Dimension(1280, 720));

        this.addFrames();
        this.add(mainPanel, BorderLayout.CENTER);
        this.pack(); // nastaví velikost na základě preferovaných rozměrů
        this.setVisible(true);
    }


    private void addFrames(){
        mainPanel.add(createGameFrame(),"hub");
        mainPanel.add(createCaveFrame(), "cave");
    }

    private JPanel createGameFrame() {
        JPanel hubPanel = new JPanel();
        hubPanel.setLayout(null);

        caveButton.setBounds(60, 470, 180, 180);
        caveButton.addActionListener(this);
        caveButton.setFocusable(false);
        caveButton.setOpaque(false);
        caveButton.setContentAreaFilled(false);
        caveButton.setBorderPainted(false);
        caveButton.setFocusPainted(false);
        hubPanel.add(caveButton);

        ImageIcon backgroundImage = new ImageIcon("Textures/game_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        hubPanel.add(background);
        hubPanel.setComponentZOrder(background, hubPanel.getComponentCount() - 1);

        return hubPanel;
    }

    private JPanel createCaveFrame(){
        JPanel cavePanel = new JPanel();
        cavePanel.setLayout(null);

        JButton attackButton = new JButton();

        attackButton.setBounds(60, 470, 180, 180);
        caveButton.setFocusable(false);

        ImageIcon backgroundImage = new ImageIcon("Textures/cave_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        cavePanel.add(background);
        cavePanel.setComponentZOrder(background, cavePanel.getComponentCount() - 1);

        return cavePanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
if (e.getSource() == caveButton) {
    System.out.println("caveButton");
    cardLayout.show(mainPanel, "cave");
}
    }
}

