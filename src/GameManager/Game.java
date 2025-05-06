package GameManager;

import GameManager.Nutria.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Player player;
    private ImageIcon icon = new ImageIcon("Textures/icon.jpg");
    private JButton caveButton = new JButton();
    private JLabel background;

    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private MainNutria nutria;
    private CombatManager combat;
    private JLabel nutriaHealthLabel;
    private JLabel enemyHealthLabel;
    private JButton attackButton;
    private JTextArea combatLog;

    public Game(Player player){
        this.player = player;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(new Dimension(1280, 720));

        addFrames();

        this.add(mainPanel, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
    }

    private void addFrames() {
        mainPanel.add(createGameFrame(), "hub");
        mainPanel.add(createCaveFrame(), "cave");
    }

    private JPanel createGameFrame() {
        JPanel hubPanel = new JPanel(null);

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

        hubPanel.revalidate();
        hubPanel.repaint();

        return hubPanel;
    }

    private JPanel createCaveFrame() {
        JPanel cavePanel = new JPanel(null);

        nutriaHealthLabel = new JLabel();
        nutriaHealthLabel.setBounds(270, 240, 200, 30);
        nutriaHealthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        cavePanel.add(nutriaHealthLabel);

        enemyHealthLabel = new JLabel();
        enemyHealthLabel.setBounds(890, 240, 200, 30);
        enemyHealthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        cavePanel.add(enemyHealthLabel);

        attackButton = new JButton("Útočit");
        attackButton.setBounds(20, 625, 200, 50);
        attackButton.setFocusable(false);
        cavePanel.add(attackButton);

        combatLog = new JTextArea();
        combatLog.setEditable(false);
        combatLog.setLineWrap(true);
        combatLog.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(combatLog);
        scrollPane.setBounds(410, 0, 400, 200);
        cavePanel.add(scrollPane);

        attackButton.addActionListener(e -> {
            if (combat.isBattleOver()) {
                return;
            }

            String playerAction = combat.playerAttack();
            String enemyAction = combat.enemyAttack();

            combatLog.append(playerAction + "\n");
            if (!enemyAction.isEmpty()) {
                combatLog.append(enemyAction + "\n");
            }

            updateHealthLabels();

            if (combat.isBattleOver()) {
                if (combat.isPlayerDead()) {
                    combatLog.append("Prohrál jsi!\n");
                    cardLayout.show(mainPanel, "hub");
                    System.out.println("lose");

                } else {
                    if (combat.hasMoreEnemies()) {
                        combat.loadNextEnemy();
                        combatLog.append("Nepřítel poražen! Přichází další...\n");
                        updateHealthLabels();
                    } else {
                        combatLog.append("Vyhrál jsi všechny souboje!\n");
                        cardLayout.show(mainPanel, "hub");
                        System.out.println("win");
                    }
                }
            }
        });

        ImageIcon backgroundImage = new ImageIcon("Textures/cave_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        cavePanel.add(background);
        cavePanel.setComponentZOrder(background, cavePanel.getComponentCount() - 1);

        cavePanel.revalidate();
        cavePanel.repaint();

        return cavePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == caveButton) {
            resetCombat();
            cardLayout.show(mainPanel, "cave");
        }
    }

    private void resetCombat() {
        nutria = new MainNutria(100, 10);

        enemyList.clear();
        enemyList.add(new Enemy("Ptacek", 30, 8, 1, 1,"bird.png"));
        enemyList.add(new Enemy("Krab", 40, 10, 2, 1,"crab.png"));
        enemyList.add(new Enemy("Medved", 50, 5, 3, 2,"bear.png"));

        combat = new CombatManager(nutria, enemyList);

        combatLog.setText("");

        updateHealthLabels();
    }

    private void updateHealthLabels() {
        nutriaHealthLabel.setText("Nutria HP: " + nutria.getHealth());
        enemyHealthLabel.setText(combat.getCurrentEnemy().getType() + " HP: " + combat.getCurrentEnemy().getHealth());
    }
}