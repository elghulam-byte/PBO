package P7.Tugas;

public class MainSegitiga {
    public static void main(String[] args) {
        Segitiga sg = new Segitiga();

        System.out.println("=== TEST OVERLOADING SEGITIGA ===");
        System.out.println("Total Sudut (1 sudut 60): " + sg.totalSudut(60));
        System.out.println("Total Sudut (2 sudut 60, 40): " + sg.totalSudut(60, 40));
        System.out.println("Keliling (3 sisi: 3, 4, 5): " + sg.keliling(3, 4, 5));
        System.out.println("Sisi Miring / Keliling Siku-siku (sisiA 3, sisiB 4): " + sg.keliling(3, 4));
    }
}