package percobaan2;

public class TestLaptop {

    public static void main(String[] args) {
        Laptop lab1 = new Laptop();
        lab1.kodeInventaris = "LAB-JTI-017";
        lab1.merk = "Lenovo ThinkPad E14";
        lab1.ramGB = 8;

        lab1.tampilSpesifikasi();

        int ramSetelahUpgrade = lab1.upgradeRam(8);
        System.out.println("RAM setelah upgrade : " + ramSetelahUpgrade + " GB");

        int hargaSewa = lab1.hitungHargaSewa(3);
        System.out.println("Harga sewa : Rp" + hargaSewa);
    }
}
