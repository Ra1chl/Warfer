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
    private ImageIcon icon = new ImageIcon("Textures/icon.jpg");

    private UnitsManager unitsManager;
    private UpgradeManager upgradeManager;
    private CombatManager combat;

    private JButton caveButton = new JButton();
    private JButton upgradeButton = new JButton();
    private JButton forestButton = new JButton();
    private JButton trainingButton = new JButton();
    private JButton bossButton = new JButton();
    private JButton attackButtonBoss;
    private JButton attackButton;

    private JLabel background;
    private JLabel nutriaHealthLabelBoss;
    private JLabel enemyHealthLabelBoss;
    private JLabel nutriaHealthLabel;
    private JLabel enemyHealthLabel;
    private JLabel enemyImageLabel;
    private JLabel woodLabel;
    private JLabel chestnutLabel;

    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private MainNutria nutria;  // Nutria inicializována v konstruktoru

    private JTextArea combatLog;
    private JTextArea combatLogBoss;
    private JLabel enemyImageLabelBoss;

    private boolean win = false;

    public Game() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(icon.getImage());

        // Inicializace hlavní nutrie se základními hodnotami, aby nebyla null
        this.nutria = new MainNutria(100, 1000);
        this.unitsManager = new UnitsManager(nutria);
        this.upgradeManager = new UpgradeManager(nutria);

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
        mainPanel.add(createForestFrame(), "forest");
        mainPanel.add(createTrainingFrame(), "training");
        mainPanel.add(createBossFrame(), "boss");
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

        forestButton.setBounds(350, 275, 200, 180);
        forestButton.addActionListener(this);
        forestButton.setFocusable(false);
        forestButton.setOpaque(false);
        forestButton.setContentAreaFilled(false);
        forestButton.setBorderPainted(false);
        forestButton.setFocusPainted(false);
        hubPanel.add(forestButton);

        trainingButton.setBounds(600, 145, 200, 180);
        trainingButton.addActionListener(this);
        trainingButton.setFocusable(false);
        trainingButton.setOpaque(false);
        trainingButton.setContentAreaFilled(false);
        trainingButton.setBorderPainted(false);
        trainingButton.setFocusPainted(false);
        hubPanel.add(trainingButton);

        bossButton.setBounds(800, 470, 200, 180);
        bossButton.addActionListener(this);
        bossButton.setFocusable(false);
        bossButton.setOpaque(false);
        bossButton.setContentAreaFilled(false);
        bossButton.setBorderPainted(false);
        bossButton.setFocusPainted(false);
        hubPanel.add(bossButton);

        // Štítky pro zobrazení surovin
        woodLabel = new JLabel("Dřevo: " + nutria.getResourceAmount(ResourceType.WOOD));
        woodLabel.setBounds(100, 50, 250, 30);
        hubPanel.add(woodLabel);

        chestnutLabel = new JLabel("Kaštany: " + nutria.getResourceAmount(ResourceType.NUT));
        chestnutLabel.setBounds(100, 75, 250, 30);
        hubPanel.add(chestnutLabel);

        woodLabel.setText("Dřevo: " + nutria.getResourceAmount(ResourceType.WOOD));
        chestnutLabel.setText("Kaštany: " + nutria.getResourceAmount(ResourceType.NUT));
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

        // Tlačítko pro upgrade max zdraví
        JButton upgradeHealthButton = new JButton("UPGRADE");
        upgradeHealthButton.setBounds(740, 460, 270, 50);
        upgradeHealthButton.addActionListener(e -> {
            if (upgradeManager.upgradeHealth()) {
                currentMaxHealthLabel.setText("" + nutria.getMaxHealth());
                // Aktualizujte i labely se surovinami podle potřeby
            } else {
                JOptionPane.showMessageDialog(upgradePanel, "Nemáte dostatek surovin!");
            }
        });
        upgradePanel.add(upgradeHealthButton);

        // Tlačítko pro upgrade max útoku
        JButton upgradeAttackButton = new JButton("UPGRADE");
        upgradeAttackButton.setBounds(285, 460, 270, 50);
        upgradeAttackButton.addActionListener(e -> {
            if (upgradeManager.upgradeAttack()) {
                currentMaxAttackLabel.setText("" + nutria.getMaxAttackPower());
                // Aktualizujte labely se surovinami podle potřeby
            } else {
                JOptionPane.showMessageDialog(upgradePanel, "Nemáte dostatek surovin!");
            }
        });
        upgradePanel.add(upgradeAttackButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 625, 200, 50);
        backButton.setFocusable(false);

        backButton.addActionListener(e -> {
            updateResourceLabels();
            cardLayout.show(mainPanel, "hub");
        });

        upgradePanel.add(backButton);

        upgradePanel.setComponentZOrder(background, upgradePanel.getComponentCount() - 1);

        return upgradePanel;
    }

    private JPanel createForestFrame() {
        JPanel forestPanel = new JPanel(null);

        // Vytvoření tlačítka s obrázkem
        JButton treeClick = new JButton();
        treeClick.setBounds(402, 283, 475, 475);
        ImageIcon icon = new ImageIcon("Textures/tree.png");
        Image scaledImage = icon.getImage().getScaledInstance(475, 475, Image.SCALE_SMOOTH);
        treeClick.setIcon(new ImageIcon(scaledImage));
        treeClick.setBorderPainted(true); // volitelné - odstraní obrys tlačítka
        treeClick.setContentAreaFilled(false); // volitelné - odstraní výplň tlačítka
        treeClick.setFocusPainted(false);// volitelné - odstraní rámeček při fokusu

        forestPanel.add(treeClick);

        treeClick.addActionListener(e -> {
            System.out.println("Na obrázek bylo kliknuto!");
            nutria.addResource(ResourceType.WOOD, 1);
            updateResourceLabels();
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 625, 200, 50);
        backButton.setFocusable(false);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "hub");
        });

        forestPanel.add(backButton);

        ImageIcon backgroundImage = new ImageIcon("Textures/forest_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        forestPanel.add(background);
        forestPanel.setComponentZOrder(background, forestPanel.getComponentCount() - 1);

        return forestPanel;
    }

    private JPanel createTrainingFrame() {
        JPanel trainingPanel = new JPanel(null);

        ImageIcon backgroundImage = new ImageIcon("Textures/training_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        trainingPanel.add(background);


        JButton buyMeleeButton = new JButton("Koupit Melee Unit (20 Kaštanů)");
        buyMeleeButton.setBounds(285, 560, 300, 50);
        buyMeleeButton.addActionListener(e -> {
            if (unitsManager.buyMeleeUnit()) {
                JOptionPane.showMessageDialog(this, "Melee jednotka koupena! +5 útoku");
                updateResourceLabels();
            } else {
                JOptionPane.showMessageDialog(this, "Nemáte dostatek Kaštanů!");
            }
        });
        trainingPanel.add(buyMeleeButton);

        JButton buyTankButton = new JButton("Koupit Tank Unit (20 Kaštanů)");
        buyTankButton.setBounds(710, 560, 300, 50);
        buyTankButton.addActionListener(e -> {
            if (unitsManager.buyTankUnit()) {
                JOptionPane.showMessageDialog(this, "Tank jednotka koupena! +5 zdraví");
                updateResourceLabels();
            } else {
                JOptionPane.showMessageDialog(this, "Nemáte dostatek Kaštanů!");
            }
        });
        trainingPanel.add(buyTankButton);
        JButton backButton = new JButton("Zpět");
        backButton.setBounds(20, 625, 200, 50);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "hub"));
        trainingPanel.add(backButton);

        trainingPanel.setComponentZOrder(background, trainingPanel.getComponentCount() - 1);

        return trainingPanel;
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
                        win = true;
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

    private JPanel createBossFrame() {
        JPanel bossPanel = new JPanel(null);

        nutriaHealthLabelBoss = new JLabel();
        nutriaHealthLabelBoss.setBounds(270, 240, 200, 30);
        nutriaHealthLabelBoss.setFont(new Font("Arial", Font.BOLD, 20));
        bossPanel.add(nutriaHealthLabelBoss);
        enemyHealthLabelBoss = new JLabel();
        enemyHealthLabelBoss.setBounds(890, 240, 200, 30);
        enemyHealthLabelBoss.setFont(new Font("Arial", Font.BOLD, 20));
        bossPanel.add(enemyHealthLabelBoss);
        attackButtonBoss = new JButton("Útočit");
        attackButtonBoss.setBounds(20, 625, 200, 50);
        attackButtonBoss.setFocusable(false);
        bossPanel.add(attackButtonBoss);
        combatLogBoss = new JTextArea();
        combatLogBoss.setEditable(false);
        combatLogBoss.setLineWrap(true);
        combatLogBoss.setWrapStyleWord(true);
        JScrollPane scrollPaneBoss = new JScrollPane(combatLogBoss);
        scrollPaneBoss.setBounds(410, 0, 400, 200);
        bossPanel.add(scrollPaneBoss);
        enemyImageLabelBoss = new JLabel();
        enemyImageLabelBoss.setBounds(890, 300, 300, 300);
        bossPanel.add(enemyImageLabelBoss);


        attackButtonBoss.addActionListener(e -> {
            if (combat.isBattleOver()) {
                return;
            }

            String playerAction = combat.playerAttack();
            String enemyAction = combat.enemyAttack();

            combatLogBoss.append(playerAction + "\n");
            if (!enemyAction.isEmpty()) {
                combatLog.append(enemyAction + "\n");
            }

            updateHealthLabelsBoss();

            if (combat.isBattleOver()) {
                if (combat.isPlayerDead()) {
                    combatLogBoss.append("Prohrál jsi!\n");
                    cardLayout.show(mainPanel, "hub");
                    System.out.println("lose");
                } else {
                    combatLogBoss.append("Vyhrál jsi s bossem!\n");

                    new WinScene(nutria);
                    cardLayout.show(mainPanel, "hub");
                    this.win=false;
                    System.out.println("win");
                }
            }
        });

        ImageIcon backgroundImage = new ImageIcon("Textures/boss_bg.png");
        background = new JLabel(backgroundImage);
        background.setBounds(0, 0, 1280, 720);
        bossPanel.add(background);
        bossPanel.setComponentZOrder(background, bossPanel.getComponentCount() - 1);

        bossPanel.revalidate();
        bossPanel.repaint();

        return bossPanel;
    }

    private void resetCombat() {
        nutria.setHealth(nutria.getMaxHealth()); // Obnovíme zdraví na maximum
        nutria.setAttackPower(nutria.getMaxAttackPower()); // Obnovíme útok na maximum

        enemyList.clear();
        enemyList.add(new Enemy("Ptacek", 15, 5, 50, "bird.png"));
        enemyList.add(new Enemy("Vcela", 30, 10, 2, "bee.png"));
        enemyList.add(new Enemy("Krab", 50, 15, 3, "crab.png"));
        enemyList.add(new Enemy("Krecek", 65, 20, 4, "hamster.png"));
        enemyList.add(new Enemy("Duck", 80, 25, 7, "duck.png"));
        enemyList.add(new Enemy("Medved", 110, 30, 10, "bear.png"));
        enemyList.add(new Enemy("Crocodile", 200, 40, 20, "croco.png"));

        combat = new CombatManager(nutria, enemyList);

        combatLog.setText("");

        updateHealthLabels();
    }

    private void resetBossCombat() {
        nutria.setHealth(nutria.getMaxHealth());
        nutria.setAttackPower(nutria.getMaxAttackPower());
        enemyList.clear();
        enemyList.add(new Enemy("Boss", 200, 35, 50, "")); // Boss jediný
        combat = new CombatManager(nutria, enemyList);
        combatLog.setText("");
        updateHealthLabelsBoss();
    }

    private void updateResourceLabels() {
        woodLabel.setText("Dřevo: " + nutria.getResourceAmount(ResourceType.WOOD));
        chestnutLabel.setText("Kaštany: " + nutria.getResourceAmount(ResourceType.NUT));
    }


    private void updateHealthLabels() {
        nutriaHealthLabel.setText("Nutria HP: " + nutria.getHealth());
        Enemy currentEnemy = combat.getCurrentEnemy();
        enemyHealthLabel.setText(currentEnemy.getType() + " HP: " + currentEnemy.getHealth());

        // Přidání odměny po poražení nepřítele
        if (currentEnemy.getHealth() <= 0) {
            nutria.addResource(ResourceType.NUT, currentEnemy.getReward());
            updateResourceLabels();
        }

        ImageIcon icon = new ImageIcon("Textures/" + currentEnemy.getObrazek());
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        enemyImageLabel.setIcon(new ImageIcon(scaledImage));
    }

    private void updateHealthLabelsBoss() {
        nutriaHealthLabelBoss.setText("Nutria HP: " + nutria.getHealth());
        Enemy currentEnemy = combat.getCurrentEnemy();
        enemyHealthLabelBoss.setText(currentEnemy.getType() + " HP: " + currentEnemy.getHealth());

        // Přidání odměny po poražení nepřítele
        if (currentEnemy.getHealth() <= 0) {
            nutria.addResource(ResourceType.NUT, currentEnemy.getReward());
            updateResourceLabels();
        }
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
        if (e.getSource() == forestButton) {
            cardLayout.show(mainPanel, "forest");
        }
        if (e.getSource() == trainingButton) {
            cardLayout.show(mainPanel, "training");
        }
        if (e.getSource() == bossButton) {
            if (!(win == false)) {
                resetBossCombat();
                cardLayout.show(mainPanel, "boss");
            } else {
                JOptionPane.showMessageDialog(this, "Nejprve musite porazit nepratele v prvni jekzni");
            }

        }
    }
}
