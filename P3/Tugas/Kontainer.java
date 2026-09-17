package P3.Tugas;

public class Kontainer {

    private String nomorResi;
    private String namaPemilik;
    private double kapasitasMaksimal;
    private double beratMuatanSaatIni;

    public Kontainer(String nomorResi, String namaPemilik, double kapasitasMaksimal) {
        this.nomorResi = nomorResi;
        this.namaPemilik = namaPemilik;
        this.kapasitasMaksimal = kapasitasMaksimal;
        this.beratMuatanSaatIni = 0;
    }

    public String getNomorResi() {
        return nomorResi;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public double getKapasitasMaksimal() {
        return kapasitasMaksimal;
    }

    public double getBeratMuatanSaatIni() {
        return beratMuatanSaatIni;
    }

    public void tambahMuatan(double berat) {
        if (beratMuatanSaatIni + berat > kapasitasMaksimal) {
            System.out.println("Gagal tambah muatan! Melebihi kapasitas maksimal.");
        } else {
            beratMuatanSaatIni += berat;
        }
    }

    public void turunkanMuatan(double berat) {
        if (beratMuatanSaatIni - berat < 0) {
            System.out.println("Gagal menurunkan muatan! Jumlah yang diturunkan melebihi muatan saat ini.");
        } else {
            beratMuatanSaatIni -= berat;
        }
    }
}