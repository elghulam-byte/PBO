package P5.Tugas;

public class Dosen extends Pegawai {
    public int jumlahSKS;
    public int TARIF_SKS = 100000;

    public Dosen(String nip, String nama, String alamat) {
        super(nip, nama, alamat);
    }

    public void setSKS(int SKS) {
        this.jumlahSKS = SKS;
    }

    @Override
    public int getGaji() {
        return this.jumlahSKS * TARIF_SKS;
    }
}
