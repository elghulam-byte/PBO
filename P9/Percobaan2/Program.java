package P9.Percobaan2;

public class Program {
    public static void main(String[] args) {
        Sarjana sarjanaCumlaude = new Sarjana("Dini");
        PascaSarjana masterCumlaude = new PascaSarjana("Rani");

        Rektor pakRektor = new Rektor();
        pakRektor.beriSertifikatCumlaude(sarjanaCumlaude);
        pakRektor.beriSertifikatCumlaude(masterCumlaude);
    }
}

class PascaSarjana extends Sarjana {
    public PascaSarjana(String nama) {
        super(nama);
    }
}
