package perpustakaan;

/**
 * Koleksi yang bisa dipinjam dan dibawa pulang.
 * Lihat lembar soal, Bagian 1.2.
 */
public abstract class KoleksiFisik extends Koleksi implements DapatDipinjam {

    protected KoleksiFisik(String kode, String judul, int tahunTerbit) {
        super(kode, judul, tahunTerbit);
    }

    @Override
    public long hitungDenda(int hariTerlambat) {
        // TODO
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    // TODO: tambahkan method dendaMaksimum() sesuai lembar soal
}
