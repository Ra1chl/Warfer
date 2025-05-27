package GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GameManager.Nutria.*;


public class WinScene extends JFrame implements ActionListener {


    private JFrame winFrame = new JFrame();
    private JPanel winPanel = new JPanel();
    private JButton exitButton = new JButton("Exit");
    private JLabel background;
    private ImageIcon icon = new ImageIcon("Textures/icon.jpg");
    private MainNutria nutria;


    public WinScene(MainNutria nutria) {
        this.nutria = nutria; // Uložení reference na nutria


        winFrame.setTitle("You Win!");
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.setSize(1280, 720);
        winFrame.setResizable(false);
        winFrame.setIconImage(icon.getImage());


        exitButton.setBounds(540, 470, 200, 80);
        exitButton.addActionListener(this);


        winPanel.setLayout(null);
        winPanel.setBounds(0, 0, 1280, 720);
        winPanel.add(exitButton);


        ImageIcon backgroundImage = new ImageIcon("Textures/win_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1536, 1024);
        winPanel.add(background);


        winFrame.add(winPanel);
        winFrame.setLocationRelativeTo(null);
        winFrame.setVisible(true);


        // Přidání bonusových zdrojů a obnovení zdraví
        rebirth();
    }


    private void rebirth() {
        nutria.setHealth(nutria.getMaxHealth()); // Obnovíme zdraví na maximum
        nutria.addResource(ResourceType.WOOD, 500); // Přidání 500 dřeva
        nutria.addResource(ResourceType.NUT, 500); // Přidání 500 kaštanů
        JOptionPane.showMessageDialog(winFrame, "Získali jste 500 dřeva a 500 kaštanů!");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            winFrame.dispose();
        }
    }
}