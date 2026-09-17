package P3.Tugas;

import java.util.Scanner;

public class TestLogistik {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Kontainer kontainerAlfa = new Kontainer("KNT-9088", "PT Maju Bersama", 5000);

        System.out.println("=== MANAJEMEN CONTAINER LOGISTIK ===");
        System.out.println("Nama Pemilik Kontainer : " + kontainerAlfa.getNamaPemilik());
        System.out.println("Kapasitas Maksimal     : " + kontainerAlfa.getKapasitasMaksimal() + " kg");
        System.out.println("------------------------------------\n");

        boolean selesai = false;

        while (!selesai) {
            System.out.println("Berat Muatan Saat Ini: " + kontainerAlfa.getBeratMuatanSaatIni() + " kg");
            System.out.println("Pilih Aksi:");
            System.out.println("1. Tambah Muatan");
            System.out.println("2. Turunkan/Bongkar Muatan");
            System.out.println("3. Selesai / Keluar");
            System.out.print("Pilihan Anda (1-3): ");
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan berat muatan yang akan ditambah (kg): ");
                    double beratTambah = scanner.nextDouble();
                    kontainerAlfa.tambahMuatan(beratTambah);
                    break;

                case 2:
                    System.out.print("Masukkan berat muatan yang akan diturunkan (kg): ");
                    double beratTurun = scanner.nextDouble();
                    kontainerAlfa.turunkanMuatan(beratTurun);
                    break;

                case 3:
                    selesai = true;
                    System.out.println("\nProses selesai. Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Silakan coba lagi.");
            }
            System.out.println("------------------------------------");
        }

        scanner.close();
    }
}
