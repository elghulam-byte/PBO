package perpustakaan;

/**
 * Induk semua koleksi perpustakaan.
 * Lihat lembar soal, Bagian 1.1.
 */
public abstract class Koleksi {

    // TODO: tambahkan atribut kode, judul, dan tahunTerbit

    protected Koleksi(String kode, String judul, int tahunTerbit) {
        // TODO: validasi, rapikan, lalu simpan data
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public String getKode() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public String getJudul() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    public int getTahunTerbit() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    /** Nama jenis koleksi, misalnya "Buku". */
    public abstract String jenis();

    public String info() {
        throw new UnsupportedOperationException("Belum dikerjakan");
    }

    // TODO: override equals() dan hashCode() sesuai lembar soal
}
