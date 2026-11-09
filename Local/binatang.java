package Local;

public class binatang {
    protected String nama;
    protected int jmlKaki;

    public binatang (String nama, int jmlKaki) {
        this.nama = nama;
        this.jmlKaki = jmlKaki;
    }

    public void setNama (String nama) {
        this.nama = nama;
    }

    public String getNama () {
        return nama;
    }

    public void setJmlKaki (int JmlKaki) {
        this.jmlKaki = jmlKaki;
    }

    public int getJmlKaki () {
        return jmlKaki;
    }
}  