package perpustakaan;

/**
 * Lihat lembar soal, Bagian 2.
 */
public class Alumni extends Anggota {

    public Alumni(String id, String nama) {
        super(id, nama);
    }

    @Override
    public int maksPinjamanAktif() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    @Override
    protected double faktorDenda() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }
}
