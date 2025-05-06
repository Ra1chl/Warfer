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
    private JButton upgradeButton = new JButton();
    private JLabel background;

    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private MainNutria nutria;  // Nutria inicializována v konstruktoru
    private CombatManager combat;
    private JLabel nutriaHealthLabel;
    private JLabel enemyHealthLabel;
    private JButton attackButton;
    private JTextArea combatLog;

    private JLabel enemyImageLabel;

    private JButton exitButton;

    // Přidáme labely pro max health a max attack (pro upgrade)
    private int upgradeCostWood = 5;
    private int upgradeCostChestnut = 5;

    public Game(Player player) {
        this.player = player;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(icon.getImage());

        // Inicializace hlavní nutrie se základními hodnotami, aby nebyla null
        this.nutria = new MainNutria(100, 10);

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
        mainPanel.add(createUpgradeFrame(), "upgrade");
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

        upgradeButton.setBounds(900, 275, 200, 180);
        upgradeButton.addActionListener(this);
        upgradeButton.setFocusable(false);
        upgradeButton.setOpaque(false);
        upgradeButton.setContentAreaFilled(false);
        upgradeButton.setBorderPainted(false);
        upgradeButton.setFocusPainted(false);
        hubPanel.add(upgradeButton);

        ImageIcon backgroundImage = new ImageIcon("Textures/game_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        hubPanel.add(background);
        hubPanel.setComponentZOrder(background, hubPanel.getComponentCount() - 1);

        hubPanel.revalidate();
        hubPanel.repaint();

        return hubPanel;
    }

    private JPanel createUpgradeFrame() {
        JPanel upgradePanel = new JPanel(null);

        ImageIcon backgroundImage = new ImageIcon("Textures/base_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        upgradePanel.add(background);

        // Štítky pro zobrazení aktuálních hodnot maxHealth a maxAttack
        JLabel currentMaxHealthLabel = new JLabel("" + nutria.getMaxHealth());
        currentMaxHealthLabel.setBounds(630, 652, 250, 30);
        currentMaxHealthLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Zvětšení textu
        upgradePanel.add(currentMaxHealthLabel);

        JLabel currentMaxAttackLabel = new JLabel("" + nutria.getMaxAttackPower());
        currentMaxAttackLabel.setBounds(630, 612, 250, 30);
        currentMaxAttackLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Změňte velikost podle potřeby // Zvětšení textu
        upgradePanel.add(currentMaxAttackLabel);

        // Štítky pro zobrazení surovin
        JLabel woodLabel = new JLabel("Dřevo: " + nutria.getResourceAmount(ResourceType.WOOD));
        woodLabel.setBounds(100, 200, 250, 30);
        upgradePanel.add(woodLabel);

        JLabel chestnutLabel = new JLabel("Kaštany: " + nutria.getResourceAmount(ResourceType.NUT));
        chestnutLabel.setBounds(100, 250, 250, 30);
        upgradePanel.add(chestnutLabel);

        // Tlačítko pro upgrade max zdraví
        JButton upgradeHealthButton = new JButton("UPGRADE");
        upgradeHealthButton.setBounds(740, 460, 270, 50);
        upgradeHealthButton.addActionListener(e -> {
            if (nutria.getResourceAmount(ResourceType.WOOD) >= upgradeCostWood && nutria.getResourceAmount(ResourceType.NUT) >= upgradeCostChestnut) {
                nutria.removeResource(ResourceType.WOOD, upgradeCostWood);
                nutria.removeResource(ResourceType.NUT, upgradeCostChestnut);
                nutria.increaseMaxHealth(10); // Zvýšení max health o 10
                currentMaxHealthLabel.setText("" + nutria.getMaxHealth());
               // woodLabel.setText("Dřevo: " + nutria.getResourceAmount(ResourceType.WOOD));
                //chestnutLabel.setText("Kaštany: " + nutria.getResourceAmount(ResourceType.NUT));
                upgradeCostWood += 2;
                upgradeCostChestnut += 2;
            } else {
                JOptionPane.showMessageDialog(upgradePanel, "Nemáte dostatek surovin!");
            }
        });
        upgradePanel.add(upgradeHealthButton);

        // Tlačítko pro upgrade max útoku
        JButton upgradeAttackButton = new JButton("UPGRADE");
        upgradeAttackButton.setBounds(285, 460, 270, 50);
        upgradeAttackButton.addActionListener(e -> {
            if (nutria.getResourceAmount(ResourceType.WOOD) >= upgradeCostWood && nutria.getResourceAmount(ResourceType.NUT) >= upgradeCostChestnut) {
                nutria.removeResource(ResourceType.WOOD, upgradeCostWood);
                nutria.removeResource(ResourceType.NUT, upgradeCostChestnut);
                nutria.increaseMaxAttackPower(5); // Zvýšení max útoku o 5
                currentMaxAttackLabel.setText("" + nutria.getMaxAttackPower());
                upgradeCostWood += 2;
                upgradeCostChestnut += 2;
            } else {
                JOptionPane.showMessageDialog(upgradePanel, "Nemáte dostatek surovin!");
            }
        });
        upgradePanel.add(upgradeAttackButton);

        exitButton = new JButton("Back");
        exitButton.setBounds(20, 625, 200, 50);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        upgradePanel.add(exitButton);

        upgradePanel.setComponentZOrder(background, upgradePanel.getComponentCount() - 1);

        return upgradePanel;
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

        enemyImageLabel = new JLabel();
        enemyImageLabel.setBounds(890, 300, 300, 300);
        cavePanel.add(enemyImageLabel);

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

    private void resetCombat() {
        // Místo vytváření nové instance, resetujeme pouze zdraví a útok
        nutria.setHealth(nutria.getMaxHealth()); // Obnovíme zdraví na maximum
        nutria.setAttackPower(nutria.getMaxAttackPower()); // Obnovíme útok na maximum

        enemyList.clear();
        enemyList.add(new Enemy("Ptacek", 30, 8, 1, "bird.png"));
        enemyList.add(new Enemy("Krab", 40, 10, 2, "crab.png"));
        enemyList.add(new Enemy("Medved", 50, 5, 3, "bear.png"));
        enemyList.add(new Enemy("bee", 60, 9, 3, "bee.png"));
        enemyList.add(new Enemy("Duck", 60, 10, 3, "duck.png"));

        combat = new CombatManager(nutria, enemyList);

        combatLog.setText("");

        updateHealthLabels();
    }

    private void updateHealthLabels() {
        nutriaHealthLabel.setText("Nutria HP: " + nutria.getHealth());
        Enemy currentEnemy = combat.getCurrentEnemy();
        enemyHealthLabel.setText(currentEnemy.getType() + " HP: " + currentEnemy.getHealth());

        // Přidání odměny po poražení nepřítele
        if (currentEnemy.getHealth() <= 0) {
            nutria.addResource(ResourceType.NUT, currentEnemy.getReward());
            nutria.addResource(ResourceType.WOOD, currentEnemy.getReward());
            combatLog.append("Nutria získala " + currentEnemy.getReward() + " odměnu za poražení " + currentEnemy.getType() + "!\n");

            // Aktualizace zobrazení surovin
            combatLog.append("Kaštany: " + nutria.getResourceAmount(ResourceType.NUT));
        }

        ImageIcon icon = new ImageIcon("Textures/" + currentEnemy.getObrazek());
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        enemyImageLabel.setIcon(new ImageIcon(scaledImage));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == caveButton) {
            resetCombat();
            cardLayout.show(mainPanel, "cave");
        }
        if (e.getSource() == upgradeButton) {
            cardLayout.show(mainPanel, "upgrade");
        }


        if (e.getSource() == exitButton) {
            cardLayout.show(mainPanel, "hub");
        }
    }
}
