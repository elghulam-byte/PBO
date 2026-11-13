package perpustakaan;

/**
 * Saluran pengiriman pesan ke anggota (email, WhatsApp, dll.).
 * FILE INI SUDAH JADI. JANGAN DIUBAH.
 */
public interface Notifikasi {

    void kirim(Anggota penerima, String pesan);
}
