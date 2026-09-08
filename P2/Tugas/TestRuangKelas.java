package Tugas;

public class TestRuangKelas {
    public static void main(String[] args) {
        RuangKelas r1 = new RuangKelas();
        r1.kodeRuang = "RT01";
        r1.namaGedung = "Gedung Sipil";
        r1.kapasitas = 40;
        r1.jumlahMahasiswa = 32;

        r1.tampilData();
    }
}
