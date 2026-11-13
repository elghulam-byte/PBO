package perpustakaan;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tes Bagian 3: LayananPeminjaman (prinsip SOLID).
 * JANGAN DIUBAH. Dosen akan memakai salinan asli file ini saat menilai.
 */
@DisplayName("Bagian 3 - LayananPeminjaman")
@TestMethodOrder(MethodOrderer.DisplayName.class)
class Bagian3LayananTest {

    /** Repositori palsu: menyimpan di list dan menghitung berapa kali simpan() dipanggil. */
    static class RepoPalsu implements RepositoriPeminjaman {
        final List<Peminjaman> data = new ArrayList<>();
        int jumlahSimpan = 0;
        @Override public void simpan(Peminjaman p) { jumlahSimpan++; data.add(p); }
        @Override public List<Peminjaman> semua() { return new ArrayList<>(data); }
    }

    /** Notifikasi palsu: mencatat pesan, tidak mengirim apa pun. */
    static class NotifikasiPalsu implements Notifikasi {
        final List<String> pesan = new ArrayList<>();
        final List<Anggota> penerima = new ArrayList<>();
        @Override public void kirim(Anggota a, String p) { penerima.add(a); pesan.add(p); }
    }

    static class Majalah extends KoleksiFisik {
        Majalah(String kode, String judul, int tahun) { super(kode, judul, tahun); }
        @Override public String jenis() { return "Majalah"; }
        @Override public int batasHariPinjam() { return 3; }
        @Override public long dendaPerHari() { return 500; }
    }

    static class AnggotaTamu extends Anggota {
        AnggotaTamu(String id, String nama) { super(id, nama); }
        @Override public int maksPinjamanAktif() { return 2; }
        @Override protected double faktorDenda() { return 2.0; }
    }

    static final LocalDate TGL = LocalDate.of(2026, 11, 2);

    RepoPalsu repo;
    NotifikasiPalsu notif;
    LayananPeminjaman layanan;
    Mahasiswa budi;
    Buku cleanCode;
    Jurnal jurnal;

    @BeforeEach
    void siapkan() {
        repo = new RepoPalsu();
        notif = new NotifikasiPalsu();
        layanan = new LayananPeminjaman(repo, notif);
        budi = new Mahasiswa("2341720001", "Budi");
        cleanCode = new Buku("BK-001", "Clean Code", 2008);
        jurnal = new Jurnal("JR-01", "IEEE Access", 2025);
    }

    // ---------------- struktur ----------------

    @Test
    @DisplayName("3.01 Hanya bergantung pada abstraksi: tepat 2 atribut private final bertipe interface")
    void strukturDip() {
        Set<Class<?>> tipe = new HashSet<>();
        for (Field f : LayananPeminjaman.class.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            assertTrue(Modifier.isPrivate(f.getModifiers()) && Modifier.isFinal(f.getModifiers()),
                    "Atribut " + f.getName() + " harus private final");
            tipe.add(f.getType());
        }
        assertEquals(Set.of(RepositoriPeminjaman.class, Notifikasi.class), tipe,
                "LayananPeminjaman hanya boleh menyimpan RepositoriPeminjaman dan Notifikasi");
    }

    @Test
    @DisplayName("3.02 Constructor menolak repositori atau notifikasi null")
    void constructorNull() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new LayananPeminjaman(null, notif)),
            () -> assertThrows(IllegalArgumentException.class, () -> new LayananPeminjaman(repo, null))
        );
    }

    // ---------------- pinjam ----------------

    @Test
    @DisplayName("3.03 pinjam() menghitung jatuh tempo dan menyimpan ke repositori")
    void pinjamNormal() {
        Peminjaman p1 = layanan.pinjam(budi, cleanCode, TGL);
        Peminjaman p2 = layanan.pinjam(budi, jurnal, TGL);
        assertAll(
            () -> assertEquals(LocalDate.of(2026, 11, 16), p1.getJatuhTempo()),
            () -> assertEquals(LocalDate.of(2026, 11, 9), p2.getJatuhTempo()),
            () -> assertEquals(TGL, p1.getTanggalPinjam()),
            () -> assertTrue(p1.isAktif()),
            () -> assertEquals(2, repo.jumlahSimpan),
            () -> assertSame(p1, repo.data.get(0)),
            () -> assertEquals(2, layanan.jumlahPinjamanAktif("2341720001"))
        );
    }

    @Test
    @DisplayName("3.04 Argumen null pada pinjam() ditolak")
    void pinjamNull() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> layanan.pinjam(null, cleanCode, TGL)),
            () -> assertThrows(IllegalArgumentException.class, () -> layanan.pinjam(budi, null, TGL)),
            () -> assertThrows(IllegalArgumentException.class, () -> layanan.pinjam(budi, cleanCode, null))
        );
    }

    @Test
    @DisplayName("3.05 Koleksi yang sedang dipinjam tidak bisa dipinjam lagi (dicek lewat kode)")
    void koleksiSedangDipinjam() {
        layanan.pinjam(budi, cleanCode, TGL);
        Mahasiswa ani = new Mahasiswa("2341720002", "Ani");
        Buku salinanObjek = new Buku("bk-001", "Clean Code", 2008);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> layanan.pinjam(ani, salinanObjek, TGL));
        assertEquals("Koleksi sedang dipinjam", e.getMessage());
        assertEquals(1, repo.jumlahSimpan);
    }

    @Test
    @DisplayName("3.06 Mahasiswa maksimal 3 pinjaman aktif")
    void batasPinjaman() {
        layanan.pinjam(budi, new Buku("BK-1", "A", 2020), TGL);
        layanan.pinjam(budi, new Buku("BK-2", "B", 2020), TGL);
        layanan.pinjam(budi, new Buku("BK-3", "C", 2020), TGL);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> layanan.pinjam(budi, new Buku("BK-4", "D", 2020), TGL));
        assertEquals("Batas pinjaman tercapai", e.getMessage());
        assertEquals(3, layanan.jumlahPinjamanAktif("2341720001"));
    }

    @Test
    @DisplayName("3.07 Urutan pengecekan: koleksi dicek dulu, baru batas pinjaman")
    void urutanPengecekan() {
        Alumni sari = new Alumni("A-01", "Sari");
        layanan.pinjam(sari, cleanCode, TGL);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> layanan.pinjam(sari, cleanCode, TGL));
        assertEquals("Koleksi sedang dipinjam", e.getMessage());
    }

    // ---------------- kembalikan ----------------

    @Test
    @DisplayName("3.08 Kembali tepat waktu: denda 0, tidak ada notifikasi, koleksi bisa dipinjam lagi")
    void kembaliTepatWaktu() {
        layanan.pinjam(budi, cleanCode, TGL);
        long denda = layanan.kembalikan("BK-001", LocalDate.of(2026, 11, 16));
        assertEquals(0, denda);
        assertTrue(notif.pesan.isEmpty(), "Tidak boleh ada notifikasi jika denda 0");
        assertEquals(0, layanan.jumlahPinjamanAktif("2341720001"));
        assertDoesNotThrow(() -> layanan.pinjam(new Mahasiswa("2341720002", "Ani"), cleanCode, TGL));
    }

    @Test
    @DisplayName("3.09 Terlambat 7 hari: denda Rp7.000 dan pesan notifikasi sesuai format")
    void kembaliTerlambat() {
        Peminjaman p = layanan.pinjam(budi, cleanCode, TGL);
        long denda = layanan.kembalikan("BK-001", LocalDate.of(2026, 11, 23));
        assertAll(
            () -> assertEquals(7000, denda),
            () -> assertFalse(p.isAktif()),
            () -> assertEquals(7000, p.getDenda()),
            () -> assertEquals(LocalDate.of(2026, 11, 23), p.getTanggalKembali()),
            () -> assertEquals(List.of("Denda Rp7.000 untuk \"Clean Code\", terlambat 7 hari"), notif.pesan),
            () -> assertSame(budi, notif.penerima.get(0)),
            () -> assertEquals(1, repo.jumlahSimpan, "kembalikan() tidak boleh menyimpan ulang ke repositori")
        );
    }

    @Test
    @DisplayName("3.10 Kode saat pengembalian juga dirapikan (trim + huruf besar)")
    void kembaliKodeDirapikan() {
        layanan.pinjam(budi, cleanCode, TGL);
        assertEquals(0, layanan.kembalikan("  bk-001 ", LocalDate.of(2026, 11, 10)));
    }

    @Test
    @DisplayName("3.11 Alumni + jurnal terlambat 10 hari: 20.000 x 1,5 = Rp30.000")
    void alumniJurnal() {
        Alumni sari = new Alumni("A-01", "Sari");
        layanan.pinjam(sari, jurnal, TGL);
        long denda = layanan.kembalikan("JR-01", LocalDate.of(2026, 11, 19));
        assertEquals(30000, denda);
        assertEquals(List.of("Denda Rp30.000 untuk \"IEEE Access\", terlambat 10 hari"), notif.pesan);
    }

    @Test
    @DisplayName("3.12 Dosen terlambat: denda 0 dan tidak ada notifikasi")
    void dosenTerlambat() {
        Dosen rina = new Dosen("198001012005", "Bu Rina");
        layanan.pinjam(rina, cleanCode, TGL);
        assertEquals(0, layanan.kembalikan("BK-001", LocalDate.of(2026, 12, 20)));
        assertTrue(notif.pesan.isEmpty());
    }

    @Test
    @DisplayName("3.13 Pengembalian tidak valid ditolak dengan IllegalArgumentException")
    void kembaliTidakValid() {
        layanan.pinjam(budi, cleanCode, TGL);
        assertAll(
            () -> assertThrows(IllegalArgumentException.class,
                    () -> layanan.kembalikan("BK-999", LocalDate.of(2026, 11, 5)), "kode tidak sedang dipinjam"),
            () -> assertThrows(IllegalArgumentException.class,
                    () -> layanan.kembalikan("BK-001", LocalDate.of(2026, 11, 1)), "tanggal kembali sebelum tanggal pinjam")
        );
        layanan.kembalikan("BK-001", LocalDate.of(2026, 11, 5));
        assertThrows(IllegalArgumentException.class,
                () -> layanan.kembalikan("BK-001", LocalDate.of(2026, 11, 6)), "sudah dikembalikan");
    }

    // ---------------- laporan ----------------

    @Test
    @DisplayName("3.14 totalDenda() menjumlahkan denda peminjaman yang sudah selesai milik satu anggota")
    void totalDenda() {
        Mahasiswa ani = new Mahasiswa("2341720002", "Ani");
        layanan.pinjam(budi, cleanCode, TGL);
        layanan.pinjam(budi, jurnal, TGL);
        layanan.pinjam(ani, new Buku("BK-002", "Refactoring", 2018), TGL);
        layanan.kembalikan("BK-001", LocalDate.of(2026, 11, 18)); // telat 2 hari -> 2000
        layanan.kembalikan("JR-01", LocalDate.of(2026, 11, 12));  // telat 3 hari -> 6000
        layanan.kembalikan("BK-002", LocalDate.of(2026, 11, 20)); // milik Ani
        assertEquals(8000, layanan.totalDenda("2341720001"));
        assertEquals(4000, layanan.totalDenda("2341720002"));
    }

    @Test
    @DisplayName("3.15 daftarTerlambat(): hanya yang aktif dan lewat jatuh tempo, urut jatuh tempo lalu kode")
    void daftarTerlambat() {
        Dosen rina = new Dosen("D-01", "Bu Rina");
        layanan.pinjam(rina, new Buku("BK-B", "B", 2020), TGL);                     // tempo 16 Nov
        layanan.pinjam(rina, new Jurnal("JR-Z", "Z", 2020), TGL);                   // tempo 9 Nov
        layanan.pinjam(rina, new Buku("BK-A", "A", 2020), TGL);                     // tempo 16 Nov
        layanan.pinjam(rina, new Jurnal("JR-K", "K", 2020), TGL.plusDays(10));      // tempo 19 Nov
        layanan.pinjam(rina, new Jurnal("JR-A", "A", 2020), TGL);                   // tempo 9 Nov, dikembalikan
        layanan.kembalikan("JR-A", TGL.plusDays(1));

        List<String> kode = new ArrayList<>();
        for (Peminjaman p : layanan.daftarTerlambat(LocalDate.of(2026, 11, 19))) {
            kode.add(p.getKoleksi().getKode());
        }
        assertEquals(List.of("JR-Z", "BK-A", "BK-B"), kode,
                "JR-K jatuh tempo tepat 19 Nov, jadi belum terlambat");
    }

    // ---------------- bisa diperluas ----------------

    @Test
    @DisplayName("3.16 Jenis koleksi dan anggota baru langsung didukung tanpa mengubah LayananPeminjaman")
    void jenisBaru() {
        AnggotaTamu tamu = new AnggotaTamu("T-01", "Peserta Seminar");
        Majalah tempo = new Majalah("MJ-01", "Tempo", 2026);
        Peminjaman p = layanan.pinjam(tamu, tempo, TGL);
        assertEquals(LocalDate.of(2026, 11, 5), p.getJatuhTempo());
        long denda = layanan.kembalikan("MJ-01", LocalDate.of(2026, 11, 9)); // telat 4 hari -> 2000 x 2
        assertEquals(4000, denda);
        assertEquals(List.of("Denda Rp4.000 untuk \"Tempo\", terlambat 4 hari"), notif.pesan);
    }
}
