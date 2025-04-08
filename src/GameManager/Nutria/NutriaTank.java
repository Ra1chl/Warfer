package GameManager.Nutria;

public class NutriaTank extends Units {

    public NutriaTank(int attackPower, int level) {
        super(attackPower, level);
    }

    @Override
    public void attack() {
        // Příklad útoku pro Tank jednotku
        System.out.println("NutriaTank attacks with heavy power: " + attackPower);
    }
}
