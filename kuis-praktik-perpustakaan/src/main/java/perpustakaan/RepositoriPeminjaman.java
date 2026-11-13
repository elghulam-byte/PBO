package perpustakaan;

import java.util.List;

/**
 * Tempat penyimpanan data peminjaman.
 * FILE INI SUDAH JADI. JANGAN DIUBAH.
 */
public interface RepositoriPeminjaman {

    /** Menyimpan peminjaman baru. */
    void simpan(Peminjaman peminjaman);

    /** Semua peminjaman yang pernah disimpan (aktif maupun selesai), urut sesuai waktu simpan. */
    List<Peminjaman> semua();
}
