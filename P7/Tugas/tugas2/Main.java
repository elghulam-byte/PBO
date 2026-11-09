package P7.Tugas.tugas2;

public class Main {
    public static void main(String[] args) {

        Manusia manusia;

        manusia = new Dosen();
        manusia.bernafas();
        manusia.makan();

        manusia = new Mahasiswa();
        manusia.bernafas();
        manusia.makan();
    }
}