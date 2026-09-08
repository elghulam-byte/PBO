public class TestBuku {
    public static void main(String[] args) {
        Buku buku1 = new Buku();
        buku1.isbn = "978-979-29-6104-2";
        buku1.judul = "Dasar Pemrograman Berbasis Objek";
        buku1.penulis = "Abdul Kadir";
        buku1.penerbit = "Andi Offset";
        buku1.tahunTerbit = 2021;

        Buku buku2 = new Buku();
        buku2.isbn = "978-623-00-1234-5";
        buku2.judul = "Pemrograman Java";
        buku2.penulis = "Budi Raharjo";
        buku2.penerbit = "Informatika";
        buku2.tahunTerbit = 2022;

        Buku buku3 = new Buku();
        buku3.isbn = "978-602-1234-56-7";
        buku3.judul = "Belajar OOP";
        buku3.penulis = "Andi Setiawan";
        buku3.penerbit = "Gramedia";
        buku3.tahunTerbit = 2023;

        buku1.tampilInfoBuku();
        System.out.println();

        buku2.tampilInfoBuku();
        System.out.println();

        buku3.tampilInfoBuku();
    }
}