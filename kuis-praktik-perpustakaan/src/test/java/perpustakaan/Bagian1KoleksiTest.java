package perpustakaan;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tes Bagian 1: Koleksi (abstract class dan inheritance).
 * JANGAN DIUBAH. Dosen akan memakai salinan asli file ini saat menilai.
 */
@DisplayName("Bagian 1 - Koleksi")
@TestMethodOrder(MethodOrderer.DisplayName.class)
class Bagian1KoleksiTest {

    /** Koleksi fisik baru yang tidak ada di soal. Dipakai untuk mengecek apakah desain Anda bisa diperluas. */
    static class Majalah extends KoleksiFisik {
        Majalah(String kode, String judul, int tahun) { super(kode, judul, tahun); }
        @Override public String jenis() { return "Majalah"; }
        @Override public int batasHariPinjam() { return 3; }
        @Override public long dendaPerHari() { return 500; }
    }

    // ---------------- struktur ----------------

    @Test
    @DisplayName("1.01 Koleksi dan KoleksiFisik abstract, Buku/Jurnal/EBook tidak")
    void strukturAbstract() {
        assertAll(
            () -> assertTrue(Modifier.isAbstract(Koleksi.class.getModifiers()), "Koleksi harus abstract"),
            () -> assertTrue(Modifier.isAbstract(KoleksiFisik.class.getModifiers()), "KoleksiFisik harus abstract"),
            () -> assertFalse(Modifier.isAbstract(Buku.class.getModifiers()), "Buku tidak boleh abstract"),
            () -> assertFalse(Modifier.isAbstract(Jurnal.class.getModifiers()), "Jurnal tidak boleh abstract"),
            () -> assertFalse(Modifier.isAbstract(EBook.class.getModifiers()), "EBook tidak boleh abstract"),
            () -> assertTrue(Modifier.isAbstract(Koleksi.class.getDeclaredMethod("jenis").getModifiers()),
                    "jenis() di Koleksi harus abstract")
        );
    }

    @Test
    @DisplayName("1.02 hitungDenda() final dan hook dendaMaksimum() protected di KoleksiFisik")
    void strukturTemplateMethod() throws Exception {
        Method hitung = KoleksiFisik.class.getDeclaredMethod("hitungDenda", int.class);
        Method maks = KoleksiFisik.class.getDeclaredMethod("dendaMaksimum");
        assertAll(
            () -> assertTrue(Modifier.isFinal(hitung.getModifiers()), "hitungDenda() harus final"),
            () -> assertTrue(Modifier.isProtected(maks.getModifiers()), "dendaMaksimum() harus protected"),
            () -> assertFalse(Modifier.isAbstract(maks.getModifiers()), "dendaMaksimum() harus punya nilai default"),
            () -> assertEquals(long.class, maks.getReturnType(), "dendaMaksimum() harus mengembalikan long")
        );
    }

    @Test
    @DisplayName("1.03 EBook bisa diunduh tetapi tidak bisa dipinjam")
    void strukturInterfaceEBook() {
        assertAll(
            () -> assertTrue(DapatDiunduh.class.isAssignableFrom(EBook.class), "EBook harus implements DapatDiunduh"),
            () -> assertFalse(DapatDipinjam.class.isAssignableFrom(EBook.class), "EBook tidak boleh implements DapatDipinjam"),
            () -> assertTrue(DapatDipinjam.class.isAssignableFrom(KoleksiFisik.class), "KoleksiFisik harus implements DapatDipinjam"),
            () -> assertEquals(Koleksi.class, EBook.class.getSuperclass(), "EBook harus extends Koleksi")
        );
    }

    @Test
    @DisplayName("1.04 Semua atribut Koleksi private final")
    void strukturAtribut() {
        int jumlah = 0;
        for (Field f : Koleksi.class.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            jumlah++;
            assertTrue(Modifier.isPrivate(f.getModifiers()), "Atribut " + f.getName() + " harus private");
            assertTrue(Modifier.isFinal(f.getModifiers()), "Atribut " + f.getName() + " harus final");
        }
        assertTrue(jumlah >= 3, "Koleksi minimal menyimpan kode, judul, dan tahun terbit");
    }

    // ---------------- Koleksi ----------------

    @Test
    @DisplayName("1.05 Kode dirapikan (trim + huruf besar), judul di-trim")
    void normalisasiKodeJudul() {
        Buku b = new Buku("  bk-001 ", "  Clean Code  ", 2008);
        assertEquals("BK-001", b.getKode());
        assertEquals("Clean Code", b.getJudul());
        assertEquals(2008, b.getTahunTerbit());
    }

    @Test
    @DisplayName("1.06 Data koleksi tidak valid ditolak dengan IllegalArgumentException")
    void validasiKoleksi() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Buku("", "Judul", 2020)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Buku("   ", "Judul", 2020)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Jurnal(null, "Judul", 2020)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Buku("BK-1", "  ", 2020)),
            () -> assertThrows(IllegalArgumentException.class, () -> new Buku("BK-1", "Judul", 1899))
        );
    }

    @Test
    @DisplayName("1.07 info() memakai jenis() milik masing-masing subclass")
    void infoPolimorfik() {
        Koleksi buku = new Buku("bk-001", "Clean Code", 2008);
        Koleksi jurnal = new Jurnal("JR-07", "Jurnal Informatika Polinema", 2024);
        Koleksi ebook = new EBook("eb-3", "Head First Java", 2022, 12.5);
        assertEquals("[Buku] BK-001 - Clean Code (2008)", buku.info());
        assertEquals("[Jurnal] JR-07 - Jurnal Informatika Polinema (2024)", jurnal.info());
        assertEquals("[E-Book] EB-3 - Head First Java (2022)", ebook.info());
    }

    @Test
    @DisplayName("1.08 Dua koleksi dengan kode sama dianggap sama, equals/hashCode final")
    void equalsBerdasarkanKode() throws Exception {
        Koleksi a = new Buku("BK-001", "Clean Code", 2008);
        Koleksi b = new Jurnal(" bk-001", "Judul Lain", 2020);
        Koleksi c = new Buku("BK-002", "Clean Code", 2008);
        Set<Koleksi> set = new HashSet<>();
        set.add(a);
        set.add(b);
        set.add(c);
        assertAll(
            () -> assertEquals(a, b),
            () -> assertNotEquals(a, c),
            () -> assertEquals(a.hashCode(), b.hashCode()),
            () -> assertEquals(2, set.size()),
            () -> assertTrue(Modifier.isFinal(Koleksi.class.getDeclaredMethod("equals", Object.class).getModifiers()),
                    "equals() di Koleksi harus final"),
            () -> assertTrue(Modifier.isFinal(Koleksi.class.getDeclaredMethod("hashCode").getModifiers()),
                    "hashCode() di Koleksi harus final")
        );
    }

    // ---------------- Buku & Jurnal ----------------

    @Test
    @DisplayName("1.09 Buku biasa: pinjam 14 hari, denda Rp1.000/hari")
    void bukuBiasa() {
        Buku b = new Buku("BK-1", "Refactoring", 2018);
        assertEquals("Buku", b.jenis());
        assertEquals(14, b.batasHariPinjam());
        assertEquals(1000, b.dendaPerHari());
    }

    @Test
    @DisplayName("1.10 Buku langka (terbit sebelum 2000): pinjam 7 hari, denda Rp3.000/hari")
    void bukuLangka() {
        Buku b = new Buku("BK-2", "The C Programming Language", 1988);
        assertEquals(7, b.batasHariPinjam());
        assertEquals(3000, b.dendaPerHari());
    }

    @Test
    @DisplayName("1.11 hitungDenda(): tidak terlambat = 0, lalu dikali per hari, dibatasi maksimum")
    void hitungDendaBuku() {
        Buku biasa = new Buku("BK-1", "Refactoring", 2018);
        Buku langka = new Buku("BK-2", "The C Programming Language", 1988);
        assertAll(
            () -> assertEquals(0, biasa.hitungDenda(0)),
            () -> assertEquals(0, biasa.hitungDenda(-3)),
            () -> assertEquals(4000, biasa.hitungDenda(4)),
            () -> assertEquals(10000, biasa.hitungDenda(15), "maksimum default = 10 x denda per hari"),
            () -> assertEquals(27000, langka.hitungDenda(9)),
            () -> assertEquals(30000, langka.hitungDenda(40))
        );
    }

    @Test
    @DisplayName("1.12 Jurnal: pinjam 7 hari, Rp2.000/hari, denda maksimum Rp30.000")
    void jurnal() {
        Jurnal j = new Jurnal("JR-1", "IEEE Access", 2025);
        assertAll(
            () -> assertEquals("Jurnal", j.jenis()),
            () -> assertEquals(7, j.batasHariPinjam()),
            () -> assertEquals(2000, j.dendaPerHari()),
            () -> assertEquals(20000, j.hitungDenda(10)),
            () -> assertEquals(30000, j.hitungDenda(20))
        );
    }

    // ---------------- EBook ----------------

    @Test
    @DisplayName("1.13 EBook: ukuran harus > 0, batas unduh 1x jika > 50 MB, selain itu 3x")
    void ebook() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new EBook("EB-1", "Java", 2020, 0)),
            () -> assertThrows(IllegalArgumentException.class, () -> new EBook("EB-1", "Java", 2020, -2.5)),
            () -> assertEquals(1, new EBook("EB-1", "Video Kuliah", 2020, 80).batasUnduhanPerHari()),
            () -> assertEquals(3, new EBook("EB-2", "Modul PBO", 2020, 20).batasUnduhanPerHari()),
            () -> assertEquals("E-Book", new EBook("EB-2", "Modul PBO", 2020, 20).jenis())
        );
    }

    // ---------------- bisa diperluas ----------------

    @Test
    @DisplayName("1.14 Subclass baru (Majalah) langsung bisa memakai aturan denda dari KoleksiFisik")
    void subclassBaru() {
        Majalah m = new Majalah("mj-01", "Tempo", 2026);
        assertAll(
            () -> assertEquals(1500, m.hitungDenda(3)),
            () -> assertEquals(5000, m.hitungDenda(30), "maksimum default = 10 x 500"),
            () -> assertEquals("[Majalah] MJ-01 - Tempo (2026)", m.info())
        );
    }
}
