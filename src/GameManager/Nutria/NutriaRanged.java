package GameManager.Nutria;

public class NutriaRanged extends Units {

    public NutriaRanged(int attackPower, int level) {
        super(attackPower, level);
    }

    @Override
    public void attack() {
        // Příklad útoku pro Ranged jednotku
        System.out.println("NutriaRanged attacks from a distance with power: " + attackPower);
    }
}
