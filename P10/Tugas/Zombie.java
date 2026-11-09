package P10.Tugas;

public class Zombie implements Destroyable {
    protected int health;
    protected int level;

    public void heal() {
        // Implementasi method heal standar atau dikosongkan karena di-override di subclass
    }

    @Override
    public void destroyed() {
        // Logika pengurangan health akan diatur spesifik atau di-override di subclass
    }

    public String getZombieInfo() {
        return "Health = " + health + "\nLevel = " + level;
    }
}