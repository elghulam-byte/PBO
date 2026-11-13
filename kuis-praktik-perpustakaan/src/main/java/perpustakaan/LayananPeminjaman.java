package perpustakaan;

import java.time.LocalDate;
import java.util.List;

/**
 * Logika peminjaman dan pengembalian.
 * Lihat lembar soal, Bagian 3.
 */
public class LayananPeminjaman {

    public LayananPeminjaman(RepositoriPeminjaman repositori, Notifikasi notifikasi) {
        // TODO
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public Peminjaman pinjam(Anggota anggota, KoleksiFisik koleksi, LocalDate tanggal) {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public long kembalikan(String kodeKoleksi, LocalDate tanggalKembali) {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public int jumlahPinjamanAktif(String idAnggota) {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public long totalDenda(String idAnggota) {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public List<Peminjaman> daftarTerlambat(LocalDate hariIni) {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }
}
