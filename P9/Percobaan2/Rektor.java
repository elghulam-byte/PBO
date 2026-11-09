package P9.Percobaan2;

public class Rektor {
    public void beriSertifikatCumlaude(ICumlaude mahasiswaCumlaude) {
        System.out.println("Saya Rektor, memberikan sertifikat cumlaude");
        System.out.println("Selamat! Bagaimana anda bisa cumlaude?");
        mahasiswaCumlaude.lulus();
        mahasiswaCumlaude.meraihIPKTinggi();
    }
}
