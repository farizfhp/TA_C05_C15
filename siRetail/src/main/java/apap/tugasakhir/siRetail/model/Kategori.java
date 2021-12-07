package apap.tugasakhir.siRetail.model;

public enum Kategori {
    BUKU("BUKU"),
    DAPUR("DAPUR"),
    MAKANAN_DAN_MINUMAN("MAKANAN & MINUMAN"),
    ELEKTRONIK("ELEKTRONIK"),
    FASHION("FASHION"),
    KECANTIKAN_DAN_PERAWATAN_DIRI("KECANTIKAN & PERAWATAN DIRI"),
    FILM_DAN_MUSIK("FILM & MUSIK"),
    GAMING("GAMING"),
    GADGET("GADGET"),
    KESEHATAN("'KESEHATAN"),
    RUMAH_TANGGA("RUMAH TANGGA"),
    FURNITURE("FURNITURE"),
    ALAT_DAN_PERANGKAT_KERAS("ALAT & PERANGKAT KERAS"),
    WEDDING("WEDDING");

    public final String label;

    private Kategori(String label) {
        this.label = label;

    }
}
