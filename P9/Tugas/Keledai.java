package P9.Tugas;

public class Keledai extends Binatang implements IHerbivora {
    private String suara;
    private String warnaBulu;

    public Keledai(String nama, int jmlKaki, String suara, String warnaBulu) {
        super(nama, jmlKaki);
        this.suara = suara;
        this.warnaBulu = warnaBulu;
    }

    @Override
    public void displayMakan() {
        System.out.println("Jenis: Herbivora");
        System.out.println("Makanan: Tumbuhan");
    }

    @Override
    public void displayBinatang() {
        System.out.println("Nama: " + this.nama);
        System.out.println("Jumlah Kaki: " + this.jmlKaki);
        System.out.println("Suara: " + this.suara);
        System.out.println("Warna Bulu: " + this.warnaBulu);
    }

    public void displayData() {
        displayMakan();
        displayBinatang();
    }
}
