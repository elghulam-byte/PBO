package Tugas;

public class TestTransaksiPeminjaman {
    public static void main(String[] args) {
        TransaksiPeminjaman t1 = new TransaksiPeminjaman();
        t1.idTransaksi = "TRX001";
        t1.namaPeminjam = "Ahmad";
        t1.judulBuku = "Pemrograman Java";
        t1.jumlahHariKeterlambatanInt = 0;

        TransaksiPeminjaman t2 = new TransaksiPeminjaman();
        t2.idTransaksi = "TRX002";
        t2.namaPeminjam = "Budi";
        t2.judulBuku = "Struktur Data";
        t2.jumlahHariKeterlambatanInt = 3;

        TransaksiPeminjaman t3 = new TransaksiPeminjaman();
        t3.idTransaksi = "TRX003";
        t3.namaPeminjam = "Citra";
        t3.judulBuku = "Basis Data";
        t3.jumlahHariKeterlambatanInt = 10;

        t1.tampilData();
        t2.tampilData();
        t3.tampilData();
    }
}