package perpustakaan;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Tes Bagian 2: Anggota (polymorphism).
 * JANGAN DIUBAH. Dosen akan memakai salinan asli file ini saat menilai.
 */
@DisplayName("Bagian 2 - Anggota")
@TestMethodOrder(MethodOrderer.DisplayName.class)
class Bagian2AnggotaTest {

    /** Jenis anggota baru yang tidak ada di soal. */
    static class AnggotaTamu extends Anggota {
        AnggotaTamu(String id, String nama) { super(id, nama); }
        @Override public int maksPinjamanAktif() { return 2; }
        @Override protected double faktorDenda() { return 1.1; }
    }

    @Test
    @DisplayName("2.01 Anggota abstract, hitungDendaAkhir() final, faktorDenda() protected abstract")
    void struktur() throws Exception {
        Method akhir = Anggota.class.getDeclaredMethod("hitungDendaAkhir", long.class);
        Method faktor = Anggota.class.getDeclaredMethod("faktorDenda");
        Method maks = Anggota.class.getDeclaredMethod("maksPinjamanAktif");
        assertAll(
            () -> assertTrue(Modifier.isAbstract(Anggota.class.getModifiers()), "Anggota harus abstract"),
            () -> assertTrue(Modifier.isFinal(akhir.getModifiers()), "hitungDendaAkhir() harus final"),
            () -> assertTrue(Modifier.isProtected(faktor.getModifiers()), "faktorDenda() harus protected"),
            () -> assertTrue(Modifier.isAbstract(faktor.getModifiers()), "faktorDenda() harus abstract"),
            () -> assertTrue(Modifier.isAbstract(maks.getModifiers()), "maksPinjamanAktif() harus abstract")
        );
    }

    @Test
    @DisplayName("2.02 ID dan nama wajib diisi; ID di-trim")
    void validasi() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Mahasiswa("", "Budi")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Dosen("198001", "   ")),
            () -> assertThrows(IllegalArgumentException.class, () -> new Alumni(null, "Sari")),
            () -> assertEquals("2341720001", new Mahasiswa(" 2341720001 ", "Budi").getId()),
            () -> assertEquals("Budi", new Mahasiswa("2341720001", "Budi").getNama())
        );
    }

    @Test
    @DisplayName("2.03 Batas pinjaman aktif: Mahasiswa 3, Dosen 10, Alumni 1")
    void maksPinjaman() {
        Anggota[] semua = {
            new Mahasiswa("M1", "Budi"), new Dosen("D1", "Bu Rina"), new Alumni("A1", "Sari")
        };
        int[] harapan = {3, 10, 1};
        for (int i = 0; i < semua.length; i++) {
            assertEquals(harapan[i], semua[i].maksPinjamanAktif(), semua[i].getClass().getSimpleName());
        }
    }

    @Test
    @DisplayName("2.04 Mahasiswa membayar denda penuh; denda dasar 0 atau negatif menjadi 0")
    void dendaMahasiswa() {
        Anggota m = new Mahasiswa("M1", "Budi");
        assertAll(
            () -> assertEquals(4000, m.hitungDendaAkhir(4000)),
            () -> assertEquals(0, m.hitungDendaAkhir(0)),
            () -> assertEquals(0, m.hitungDendaAkhir(-500))
        );
    }

    @Test
    @DisplayName("2.05 Dosen bebas denda")
    void dendaDosen() {
        assertEquals(0, new Dosen("D1", "Bu Rina").hitungDendaAkhir(30000));
    }

    @Test
    @DisplayName("2.06 Alumni 1,5x denda, dibulatkan ke atas ke kelipatan Rp100")
    void dendaAlumni() {
        Anggota a = new Alumni("A1", "Sari");
        assertAll(
            () -> assertEquals(6000, a.hitungDendaAkhir(4000)),
            () -> assertEquals(5300, a.hitungDendaAkhir(3500), "3500 x 1,5 = 5250 -> 5300"),
            () -> assertEquals(45000, a.hitungDendaAkhir(30000))
        );
    }

    @Test
    @DisplayName("2.07 Jenis anggota baru memakai aturan pembulatan yang sama")
    void anggotaBaru() {
        Anggota t = new AnggotaTamu("T1", "Peserta Seminar");
        assertAll(
            () -> assertEquals(3300, t.hitungDendaAkhir(3000), "3000 x 1,1 harus tetap 3300 (hati-hati dengan double)"),
            () -> assertEquals(1400, t.hitungDendaAkhir(1234), "1234 x 1,1 = 1357,4 -> 1357 -> 1400"),
            () -> assertEquals(2, t.maksPinjamanAktif())
        );
    }
}
