# Kuis Praktik PBO: Sistem Peminjaman Perpustakaan JTI

Spesifikasi lengkap ada di lembar soal (file Word). README ini hanya ringkasan cara kerja.

## Yang dibutuhkan

- JDK 17 atau lebih baru
- IDE yang mendukung Maven: IntelliJ IDEA, VS Code (Extension Pack for Java), atau NetBeans
- Internet saat proyek pertama kali dibuka, untuk mengunduh JUnit

## Cara menjalankan tes

| Alat | Caranya |
|---|---|
| IntelliJ IDEA | Open folder ini, tunggu Maven selesai. Klik kanan `src/test/java` > Run 'All Tests' |
| VS Code | Open folder ini, buka panel Testing (ikon labu), klik Run Tests |

Di awal hampir semua tes gagal. Itu normal. Lengkapi kode di `src/main/java/perpustakaan` sampai semua tes lulus.

## File yang dikerjakan

`Koleksi`, `KoleksiFisik`, `Buku`, `Jurnal`, `EBook`, `Anggota`, `Mahasiswa`, `Dosen`, `Alumni`, `LayananPeminjaman`

## File yang tidak boleh diubah

- Semua file di `src/test/java`
- `DapatDipinjam`, `DapatDiunduh`, `Notifikasi`, `NotifikasiKonsol`, `RepositoriPeminjaman`, `RepositoriMemori`, `Peminjaman`

Saat menilai, dosen memakai salinan asli tes dan menambahkan beberapa tes lain yang tidak dibagikan.
