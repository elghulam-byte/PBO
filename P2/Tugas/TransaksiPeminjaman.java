package Tugas;

public class TransaksiPeminjaman {
    public String idTransaksi;
    public String namaPeminjam;
    public String judulBuku;
    public String jumlahHariKeterlambatan; // Tipe int sesuai spesifikasi
    public int jumlahHariKeterlambatanInt;
    public int denda;

    public int hitungDenda() {
        denda = jumlahHariKeterlambatanInt * 1000;
        return denda;
    }

    public void tampilData() {
        System.out.println("ID Transaksi           : " + idTransaksi);
        System.out.println("Nama Peminjam          : " + namaPeminjam);
        System.out.println("Judul Buku             : " + judulBuku);
        System.out.println("Hari Keterlambatan     : " + jumlahHariKeterlambatanInt + " hari");
        System.out.println("Besar Denda            : Rp" + hitungDenda());
        System.out.println("----------------------------------------");
    }
}