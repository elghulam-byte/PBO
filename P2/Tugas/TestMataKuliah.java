package Tugas;

public class TestMataKuliah {

    public static void main(String[] args) {

        MataKuliah mk1 = new MataKuliah();
        mk1.kodeMK = "RTI253008";
        mk1.namaMK = "Praktikum Pemrograman Berbasis Objek";
        mk1.sks = 2;
        mk1.nilaiAngka = 4.0;

        MataKuliah mk2 = new MataKuliah();
        mk2.kodeMK = "RTI253009";
        mk2.namaMK = "Algoritma dan Struktur Data";
        mk2.sks = 3;
        mk2.nilaiAngka = 3.5;

        MataKuliah mk3 = new MataKuliah();
        mk3.kodeMK = "RTI253010";
        mk3.namaMK = "Basis Data Lanjut";
        mk3.sks = 3;
        mk3.nilaiAngka = 3.0;

        mk1.tampilData();
        mk2.tampilData();
        mk3.tampilData();

        double totalBobot = mk1.hitungBobotNilai() + mk2.hitungBobotNilai() + mk3.hitungBobotNilai();
        System.out.println("Total Bobot Nilai : " + totalBobot);
    }
}
