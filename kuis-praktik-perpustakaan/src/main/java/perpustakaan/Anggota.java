package perpustakaan;

/**
 * Induk semua jenis anggota perpustakaan.
 * Lihat lembar soal, Bagian 2.
 */
public abstract class Anggota {

    // TODO: tambahkan atribut id dan nama

    protected Anggota(String id, String nama) {
        // TODO
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public String getId() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public String getNama() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public abstract int maksPinjamanAktif();

    protected abstract double faktorDenda();

    public long hitungDendaAkhir(long dendaDasar) {
        // TODO
        throw new UnsupportedOperationException("Belum dikerjakan");
    }
}
