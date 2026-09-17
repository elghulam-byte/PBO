package P3.Tugas;

public class TestLogistik {

    public static void main(String[] args) {
        Kontainer kontainerAlfa = new Kontainer("KNT-9088", "PT Maju Bersama", 5000);

        System.out.println("Nama Pemilik Kontainer : " + kontainerAlfa.getNamaPemilik());
        System.out.println("Kapasitas Maksimal     : " + kontainerAlfa.getKapasitasMaksimal() + " kg\n");

        System.out.println("-> Memasukkan muatan baru sebesar 6.000 kg ...");
        kontainerAlfa.tambahMuatan(6000);
        System.out.println("Berat muatan saat ini   : " + kontainerAlfa.getBeratMuatanSaatIni() + " kg\n");

        System.out.println("-> Memasukkan muatan baru sebesar 2.000 kg ...");
        kontainerAlfa.tambahMuatan(2000);
        System.out.println("Berat muatan saat ini   : " + kontainerAlfa.getBeratMuatanSaatIni() + " kg\n");

        System.out.println("-> Membongkar/menurunkan barang sebesar 500 kg ...");
        kontainerAlfa.turunkanMuatan(500);
        System.out.println("Berat muatan saat ini   : " + kontainerAlfa.getBeratMuatanSaatIni() + " kg\n");

        System.out.println("-> Membongkar/menurunkan barang sebesar 1.500 kg ...");
        kontainerAlfa.turunkanMuatan(1500);
        System.out.println("Berat muatan saat ini   : " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");
    }
}