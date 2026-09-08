package percobaan2;

public class Laptop {

    public String kodeInventaris;
    public String merk;
    public int ramGB;

    public void tampilSpesifikasi() {
        System.out.println("Kode Inventaris : " + kodeInventaris);
        System.out.println("Merk            :" + merk);
        System.out.println("Ram             : " + ramGB);
    }

    public int upgradeRam(int tambahanGB) {
        ramGB = ramGB + tambahanGB;
        return ramGB;
    }

    public int hitungHargaSewa(int jumlahHari) {
        return jumlahHari * 25000;
    }
}
