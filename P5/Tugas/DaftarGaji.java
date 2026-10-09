package P5.Tugas;

public class DaftarGaji {
    public Pegawai[] listPegawai;
    private int jumlahPegawaiSekarang = 0;

    public DaftarGaji(int jumlah) {
        this.listPegawai = new Pegawai[jumlah];
    }

    public void addPegawai(Pegawai pegawai) {
        if (jumlahPegawaiSekarang < listPegawai.length) {
            this.listPegawai[jumlahPegawaiSekarang] = pegawai;
            jumlahPegawaiSekarang++;
        }
    }

    public void printSemuaGaji() {
        for (int i = 0; i < jumlahPegawaiSekarang; i++) {
            System.out.println("Nama  : " + listPegawai[i].getNama());
            System.out.println("Gaji  : Rp " + listPegawai[i].getGaji());
            System.out.println("---------------------------");
        }
    }
}
