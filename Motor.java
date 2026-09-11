public class Motor {
    public int kecepatan = 0;
    public boolean kontakOn = false;

    public void printStatus() {
        if (kontakOn == true) {
            System.out.println("kontak on");
        } else {
            System.out.println("kontak off");
        }
        System.out.println("kecepatan " + kecepatan + "\n");
    }
}
