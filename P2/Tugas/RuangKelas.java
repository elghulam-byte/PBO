package Tugas;

public class RuangKelas {
    public String kodeRuang;
    public String namaGedung;
    public int kapasitas;
    public int jumlahMahasiswa;

    public int hitungSisaKursi() {
        return kapasitas - jumlahMahasiswa;
    }

    public void tampilData() {
        System.out.println("Kode Ruang       : " + kodeRuang);
        System.out.println("Nama Gedung      : " + namaGedung);
        System.out.println("Kapasitas        : " + kapasitas);
        System.out.println("Jumlah Mahasiswa : " + jumlahMahasiswa);
        System.out.println("Sisa Kursi       : " + hitungSisaKursi());
        System.out.println("----------------------------------------");
    }
}
