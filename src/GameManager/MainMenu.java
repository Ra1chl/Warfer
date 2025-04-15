package GameManager;

import GameManager.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {

    JFrame menuFrame = new JFrame();
    JPanel menuPanel = new JPanel(null); // null layout přímo zde
    JLabel title = new JLabel();
    JButton startButton = new JButton();
    JButton exitButton = new JButton();
    private JLabel background;
    ImageIcon icon = new ImageIcon("Textures/nutria_icon.png");
    private Player player;


    public MainMenu(Player player) {
        this.player = player;

        // Titulek hry
        title.setText("NUTRIA WARFARE");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(340, 100, 600, 80); // Uprostřed
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.WHITE);

        // Start tlačítko
        startButton.setBounds(520, 310, 250, 100);
        startButton.addActionListener(this);
        startButton.setFocusable(false);

        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);

        // Quit tlačítko
        exitButton.setBounds(540, 470, 200, 80);
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);

        // Panel
        menuPanel.setBounds(0, 0, 1280, 720);
        menuPanel.add(title);
        menuPanel.add(startButton);
        menuPanel.add(exitButton);

        // Pozadí (MUSÍ BÝT PŘED FRAME SETVISIBLE)
        ImageIcon backgroundImage = new ImageIcon("Textures/menu_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        menuPanel.add(background);
        menuPanel.setComponentZOrder(background, menuPanel.getComponentCount() - 1); // Pošle pozadí dozadu

        // Frame
        menuFrame.setTitle("Nutria Warfare");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setResizable(false);
        menuFrame.setSize(1280, 720);
        menuFrame.setLocationRelativeTo(null); // vycentrovat na obrazovce
        menuFrame.setIconImage(icon.getImage());
        menuFrame.add(menuPanel);
        menuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            menuFrame.dispose();
            System.out.println("Game starting...");
            new Game(player);
        }
        if (e.getSource() == exitButton) {
            menuFrame.dispose();
            System.out.println("Game exiting...");
        }
    }
}
