package perpustakaan;

/**
 * Kemampuan sebuah koleksi untuk dipinjam secara fisik.
 * FILE INI SUDAH JADI. JANGAN DIUBAH.
 */
public interface DapatDipinjam {

    /** Lama peminjaman yang diizinkan, dalam hari. */
    int batasHariPinjam();

    /** Besar denda per hari keterlambatan, dalam rupiah. */
    long dendaPerHari();

    /** Denda dasar untuk sejumlah hari keterlambatan, dalam rupiah. */
    long hitungDenda(int hariTerlambat);
}
