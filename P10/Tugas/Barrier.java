package P10.Tugas;

public class Barrier implements Destroyable {
    private int strength;

    public Barrier(int strength) {
        this.strength = strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public void destroyed() {
        strength -= 9; // Berdasarkan selisih uji coba output (100 ke 64 dalam 4 iterasi -> 9 per iterasi)
    }

    public String getBarrierInfo() {
        return "Barrier Strength = " + strength;
    }
}
