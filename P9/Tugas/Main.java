package P9.Tugas;

public class Main {
    public static void main(String[] args) {
        Keledai keledai = new Keledai("Keledai", 4, "Hehehehe", "Abu-Abu");
        Gorilla gorilla = new Gorilla("Gulali", 4, "Haaum Hauum", "Hitam Manis");
        Singa singa = new Singa("CingaCing", 4, "Roaar Roaaar", "Coklat");

        keledai.displayData();
        System.out.println();
        gorilla.displayData();
        System.out.println();
        singa.displayData();
    }
}