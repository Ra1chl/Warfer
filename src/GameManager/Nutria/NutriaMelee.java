package GameManager.Nutria;

public class NutriaMelee extends Units {

    public NutriaMelee(int attackPower, int level) {
        super(attackPower, level);
    }

    @Override
    public void attack() {
        // Příklad útoku pro Melee jednotku
        System.out.println("NutriaMelee attacks with melee power: " + attackPower);
    }
}
