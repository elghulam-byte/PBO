package P5.Tugas;

public class MainTugas {
    public static void main(String[] args) {
        Dosen dosen1 = new Dosen("198701", "Dr. Budi Santoso", "Malang");
        dosen1.setSKS(12);

        Dosen dosen2 = new Dosen("198702", "Siti Aminah, M.T.", "Surabaya");
        dosen2.setSKS(16);

        DaftarGaji daftarGaji = new DaftarGaji(2);
        daftarGaji.addPegawai(dosen1);
        daftarGaji.addPegawai(dosen2);

        daftarGaji.printSemuaGaji();
    }
}
