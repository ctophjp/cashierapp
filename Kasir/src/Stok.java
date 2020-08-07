public class Stok {
    private String kodeBarang;
    private String namaBarang;
    private int stok;
    private float harga;

    public Stok(String kodeBarang, String namaBarang, int stok, float harga){
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.stok = stok;
        this.harga = harga;
    }

    public String getKodeBarang(){
        return kodeBarang;
    }

    public String getNamaBarang(){
        return namaBarang;
    }
    
    public int getStok(){
        return stok;
    }
    public void setStok(int stok){
        this.stok = stok;
    }
    public void updateStokAfterTransaction(int jmlPembelian){
        this.stok = this.stok - jmlPembelian;
    }
    public Float getHarga(){
        return harga;
    }

    public void setHarga(float harga){
        this.harga = harga;
    }
    
}