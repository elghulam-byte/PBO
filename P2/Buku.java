public class Buku {
    public String isbn;
    public String judul;
    public String penulis;
    public String penerbit;
    public int tahunTerbit;

    public void tampilInfoBuku() {
        System.out.println("ISBN : " + isbn);
        System.out.println("Judul : " + judul);
        System.out.println("Penulis : " + penulis);
        System.out.println("Penerbit : " + penerbit);
        System.out.println("Tahun Terbit : " + tahunTerbit);
    }
}
