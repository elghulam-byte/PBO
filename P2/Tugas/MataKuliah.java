package Tugas;

public class MataKuliah {
    public String kodeMK;
    public String namaMK;
    public int sks;
    public double nilaiAngka;

    public double hitungBobotNilai() {
        return sks * nilaiAngka;
    }

    public void tampilData() {
        System.out.println("Kode MK          : " + kodeMK);
        System.out.println("Nama MK          : " + namaMK);
        System.out.println("SKS              : " + sks);
        System.out.println("Nilai Angka      : " + nilaiAngka);
        System.out.println("Bobot Nilai      : " + hitungBobotNilai());
        System.out.println("----------------------------------------");
    }
}
