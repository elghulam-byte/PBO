package perpustakaan;

import java.time.LocalDate;

/**
 * Satu transaksi peminjaman koleksi oleh seorang anggota.
 * FILE INI SUDAH JADI. JANGAN DIUBAH.
 */
public class Peminjaman {

    private final Anggota anggota;
    private final KoleksiFisik koleksi;
    private final LocalDate tanggalPinjam;
    private final LocalDate jatuhTempo;
    private LocalDate tanggalKembali; // null selama peminjaman masih aktif
    private long denda;

    public Peminjaman(Anggota anggota, KoleksiFisik koleksi,
                      LocalDate tanggalPinjam, LocalDate jatuhTempo) {
        this.anggota = anggota;
        this.koleksi = koleksi;
        this.tanggalPinjam = tanggalPinjam;
        this.jatuhTempo = jatuhTempo;
    }

    public Anggota getAnggota() { return anggota; }
    public KoleksiFisik getKoleksi() { return koleksi; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getJatuhTempo() { return jatuhTempo; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public long getDenda() { return denda; }

    /** true jika koleksi belum dikembalikan. */
    public boolean isAktif() {
        return tanggalKembali == null;
    }

    /** Menandai peminjaman selesai. Hanya boleh dipanggil sekali. */
    public void selesai(LocalDate tanggalKembali, long denda) {
        if (!isAktif()) {
            throw new IllegalStateException("Peminjaman sudah selesai");
        }
        this.tanggalKembali = tanggalKembali;
        this.denda = denda;
    }
}
