public class Transaksi {

    private String kodeTransaksi;
    private float totalTransaksi;
    private String[][] dataBarang;

    public Transaksi(String kodeTransaksi, String[][] dataBarang, float totalTransaksi){
        this.kodeTransaksi = kodeTransaksi;
        this.dataBarang = dataBarang;
        this.totalTransaksi = totalTransaksi;
    }

    public void hitungTotalTransaksi(){

    }

    public void displayTransctionItem(){
        System.out.println();
        System.out.println("========== FAKTUR ==========");
        displayTransaksiData();
        System.out.println("======= END OF FAKTUR =======");
    }

    public void historyAllTransaksi(){
        System.out.println();
        System.out.println("Kode Transaksi  : "+this.kodeTransaksi);
        System.out.println("Total Transaksi : "+this.totalTransaksi);
        System.out.println();
    }

    public void rincianTransaksi(){

        System.out.println();
        System.out.println("======== RIWAYAT RINCIAN TRANSAKSI ========");
        displayTransaksiData();
        System.out.println("======= END OF RINCIAN TRANSAKSI =======");

    }

    public void displayTransaksiData(){
        System.out.println("Kode Transaksi:"+this.kodeTransaksi);
        System.out.println("=================");
        for (String[] data: dataBarang) {
            System.out.println();
            System.out.println("Kode Barang: "+data[0]);
            System.out.println("Jumlah : "+data[1]);
            System.out.println("Harga Satuan : "+data[2]);
            System.out.println("Total harga : "+data[3]);
            System.out.println();
        }

        System.out.println("TOTAL HARGA = "+this.totalTransaksi);
    }

    public String getKodeTransaksi(){
        return this.kodeTransaksi;
    }





    
}