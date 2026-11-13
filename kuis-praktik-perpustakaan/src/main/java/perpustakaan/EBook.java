package perpustakaan;

/**
 * Buku elektronik: bisa diunduh, tidak bisa dipinjam.
 * Lihat lembar soal, Bagian 1.4.
 */
public class EBook extends Koleksi implements DapatDiunduh {

    public EBook(String kode, String judul, int tahunTerbit, double ukuranMB) {
        super(kode, judul, tahunTerbit);
        // TODO
    }

    @Override
    public String jenis() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    @Override
    public int batasUnduhanPerHari() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }
}
