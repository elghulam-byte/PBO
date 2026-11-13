package perpustakaan;

/**
 * Implementasi sederhana yang hanya mencetak pesan ke layar.
 * FILE INI SUDAH JADI. JANGAN DIUBAH.
 */
public class NotifikasiKonsol implements Notifikasi {

    @Override
    public void kirim(Anggota penerima, String pesan) {
        System.out.println("[Notifikasi ke " + penerima.getId() + "] " + pesan);
    }
}
