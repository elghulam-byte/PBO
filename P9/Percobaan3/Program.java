package P9.Percobaan3;

public class Program {
    public static void main(String[] args) {
        Rektor pakRektor = new Rektor();
        Sarjana sarjanaCumlaude = new Sarjana("Dini");
        Pascasarjana masterCumlaude = new Pascasarjana("Elok");

        // pakRektor.beriSertifikatCumlaude(sarjanaCumlaude);
        // pakRektor.beriSertifikatCumlaude(masterCumlaude);
        
        pakRektor.beriSertifikatMawapres(sarjanaCumlaude); // Catatan: Memerlukan penyesuaian pada Sarjana jika ingin berjalan (lihat Pertanyaan 3)
        pakRektor.beriSertifikatMawapres(masterCumlaude);
    }
}
