package perpustakaan;

/**
 * Lihat lembar soal, Bagian 1.3.
 */
public class Buku extends KoleksiFisik {

    public Buku(String kode, String judul, int tahunTerbit) {
        super(kode, judul, tahunTerbit);
    }

    @Override
    public String jenis() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    @Override
    public int batasHariPinjam() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    @Override
    public long dendaPerHari() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }
}
