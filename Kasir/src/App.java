import java.util.*;
import java.security.SecureRandom;

public class App {
    static Scanner input= new Scanner(System.in);
    static ArrayList<Stok> listBarang = new ArrayList<Stok>();
    static ArrayList<Transaksi> listTransaksi = new ArrayList<Transaksi>();
    static String[] listKodeTransaksi;
    static float totalPendapatan;
    public static void main(String[] args) throws Exception {
    
        int pilihMenu = 0;
        String inputUname, inputPass, auth="";
        Boolean isValid = false;
        String superAdmin = "superadmin";
        String passSA = "admin";

        String kasir = "kasir";
        String passKasir = "kasir";

        generateDummyData();
        
        while(isValid == false){

            System.out.println("-- LOGIN SYSTEM --");

            System.out.print("Username: ");
            inputUname = input.next();

            System.out.print("Password: ");
            inputPass = input.next();
        
            if(inputUname.equals(superAdmin) && inputPass.equals(passSA)){
                auth = "superadmin";
                isValid = true;
            }else if(inputUname.equals(kasir) && inputPass.equals(passKasir)){
                auth = "kasir";
                isValid = true;
            }else{
                isValid = false;
            }
            System.out.println();
            if(isValid == false){
                System.out.println("USERNAME ATAU PASSWORD TIDAK DITEMUKAN");
            }
            System.out.println();
        }

        if(auth.equals("superadmin")){
            while (pilihMenu!=6)
            {
                System.out.println(" ");
                System.out.println("---------- SUPER ADMIN ----------");
                System.out.println(" ");
                System.out.println("-- Menu -- ");
                System.out.println("1. Input Barang");
                System.out.println("2. Update Stok");
                System.out.println("3. Update Harga");
                System.out.println("4. Informasi pendapatan");
                System.out.println("5. Display Data Stok / Barang");
                System.out.println("6. Keluar");
                System.out.println("=====================================");
                System.out.print("Pilihan [1/2/3/4/5/6] = ");
                pilihMenu = input.nextInt();
                System.out.println("=====================================");
    
                switch (pilihMenu) {
                    case 1:{
                        inputBarang();
                        break;
                    }
                    case 2:{
                        updateStok();
                        break;
                    }
                    case 3:{
                        updateHarga();
                        break;
                    }
                    case 4:{
                        informasiPendapatan();
                        break;
                    }
                    case 5:{
                        displayAllBarang();
                        break;
                    }
                    case 6:{
                        System.exit(4);
                    }
                        break;
                }
            }
        }else{
            while (pilihMenu!=6)
            {
                System.out.println(" ");
                System.out.println("---------- KASIR ----------");
                System.out.println(" ");
                System.out.println("-- Menu -- ");
                System.out.println("1. Transaksi");
                System.out.println("2. Informasi pendapatan");
                System.out.println("3. Display Data Stok / Barang");
                System.out.println("4. Riwayat semua transaksi");
                System.out.println("5. Riwayat rincian transaksi");
                System.out.println("6. Keluar");
                System.out.println("=====================================");
                System.out.print("Pilihan [1/2/3/4/5/6] = ");
                pilihMenu = input.nextInt();
                System.out.println("=====================================");
    
                switch (pilihMenu) {
                    case 1:{
                        transaksi();
                        break;
                    }
                    case 2:{
                        informasiPendapatan();
                        break;
                    }
                    case 3:{
                        displayAllBarang();
                        break;
                    }
                    case 4:{
                        riwayatSeluruhTransaksi();
                        break;
                    }
                    case 5:{
                        riwayatRincianTransaksi();
                        break;
                    }
                    case 6:{
                        System.exit(4);
                    }
                        break;
                }
            }
        }
        
    }

    static boolean checkIfKodeBarangExists(String kodeBarang){
        boolean isExists = false;
        for(Stok str: listBarang){
            if(kodeBarang.toUpperCase().equals(str.getKodeBarang()) ){
                isExists = true;
            }
	    }

        return isExists;
    }

    static boolean checkIfKodeTransaksiExists(String kodeTransaksi){
        boolean isExists = false;
        for(Transaksi ltr: listTransaksi){
            if(kodeTransaksi.toUpperCase().equals(ltr.getKodeTransaksi()) ){
                isExists = true;
            }
	    }

        return isExists;
    }
    
    static boolean checkIfStockAvailable(String kodeBarang, int jumlahDibutuhkan){
        boolean isAvaliable = false;
        for(Stok str: listBarang){
            if(kodeBarang.toUpperCase().equals(str.getKodeBarang()) ){
                int stokBarang = str.getStok();
                if(stokBarang > jumlahDibutuhkan)
                    isAvaliable = true;
            }
        }
        
        return isAvaliable;
    }

    static void inputBarang(){
        int jmlInput;
        String kodeBarang, namaBarang;
        float harga;
        int stok;
        Scanner scan = new Scanner(System.in);

        System.out.println("---------- MENU INPUT BARANG ----------");
        System.out.print("Jumlah: ");
        jmlInput = input.nextInt();

        for(int i=0; i < jmlInput;i++){
            do{
                System.out.print("\nMasukkan kode barang: ");
                kodeBarang = input.next();
                if(checkIfKodeBarangExists(kodeBarang)){
                    System.out.println("Kode barang sudah terdaftar!");
                }
            }while(checkIfKodeBarangExists(kodeBarang));

            System.out.print("Nama barang: ");
            namaBarang = scan.nextLine();

            System.out.print("Harga: ");
            harga = input.nextFloat();
            
            System.out.print("Stok: ");
            stok = input.nextInt();

            
            listBarang.add(new Stok(kodeBarang.toUpperCase(), namaBarang, stok, harga));
        }

    }

    static void updateStok(){
        String kodeBarang, namaBarang = "";
        float harga = 0;
        int stok = 0;
        String pil;
        System.out.println("---------- MENU UPDATE STOK ----------");
        do{
            System.out.print("\nKode barang: ");
            kodeBarang = input.next();
            if(!checkIfKodeBarangExists(kodeBarang)){
                System.out.println("Kode barang tidak ditemukan!");
                System.out.print("Keluar dari sub-menu? (Y/N) ");
                pil = input.next();
                if(pil.toUpperCase().equals("Y")){
                    return;
                }
            }
        }while(!checkIfKodeBarangExists(kodeBarang));

        for(Stok str: listBarang){
			if(kodeBarang.toUpperCase().equals(str.getKodeBarang()) ){
                namaBarang = str.getNamaBarang();
                harga      = str.getHarga();
                stok       = str.getStok();
            }
        }

        System.out.println("---- DATA YANG AKAN DIUBAH ----");
        displaySpecificBarang(kodeBarang, namaBarang, stok, harga);

        System.out.println("----------");
        System.out.print("Update stok menjadi: ");
        stok = input.nextInt();

        for(Stok str: listBarang){
			if(kodeBarang.toUpperCase().equals(str.getKodeBarang()) ){
                str.setStok(stok);
            }
        }

        System.out.println("---- DATA YANG TELAH BERUBAH ----");
        
        displaySpecificBarang(kodeBarang, namaBarang, stok, harga);
        
    }

    static void displaySpecificBarang(String kodeBarang, String namaBarang, int stok, float harga){
        System.out.println("Kode Barang: "+kodeBarang);
        System.out.println("Nama Barang: "+namaBarang);
        System.out.println("Harga      : "+harga);
        System.out.println("Stok       : "+stok);
    }

    static void updateHarga(){
        String kodeBarang, namaBarang = "";
        float harga = 0;
        int stok = 0;
        String pil;
        System.out.println("---------- MENU UPDATE HARGA ----------");
        do{
            System.out.print("\nKode barang: ");
            kodeBarang = input.next();
            if(!checkIfKodeBarangExists(kodeBarang)){
                System.out.println("Kode barang tidak ditemukan!");
                System.out.print("Keluar dari sub-menu? (Y/N) ");
                pil = input.next();
                if(pil.toUpperCase().equals("Y")){
                    return;
                }
            }
        }while(!checkIfKodeBarangExists(kodeBarang));

        for(Stok str: listBarang){
			if(kodeBarang.toUpperCase().equals(str.getKodeBarang()) ){
                namaBarang = str.getNamaBarang();
                harga      = str.getHarga();
                stok       = str.getStok();
            }
        }

        System.out.println("---- DATA YANG AKAN DIUBAH ----");
        displaySpecificBarang(kodeBarang, namaBarang, stok, harga);

        System.out.println("----------");
        System.out.print("Update harga menjadi: ");
        harga = input.nextFloat();

        for(Stok str: listBarang){
			if(kodeBarang.toUpperCase().equals(str.getKodeBarang()) ){
                str.setHarga(harga);
            }
        }

        System.out.println("---- DATA YANG TELAH BERUBAH ----");
        
        displaySpecificBarang(kodeBarang, namaBarang, stok, harga);
    }

    static void transaksi(){
        
        int jmlJenisBarang, jmlBarang;
        float hargaSatuan, totalHarga = 0, totalHargaSatuan = 0;
        String kodeBarang, kodeTransaksi;
        System.out.println("---- TRANSAKSI ----");
        System.out.print("Jumlah barang:");
        jmlJenisBarang = input.nextInt();
        
        String [][] dataBarang = new String[jmlJenisBarang][4];
        for(int i = 0; i < jmlJenisBarang; i++){
            do{
                System.out.print("\nKode barang: ");
                kodeBarang = input.next();
                if(!checkIfKodeBarangExists(kodeBarang)){
                    System.out.println("Kode barang tidak ditemukan!");
                    System.out.print("Keluar dari sub-menu? (Y/N) ");
                    String pil = input.next();
                    if(pil.toUpperCase().equals("Y")){
                        return;
                    }
                }
            }while(!checkIfKodeBarangExists(kodeBarang));

            do{
                System.out.print("Jumlah: ");
                jmlBarang = input.nextInt();
                if(!checkIfStockAvailable(kodeBarang, jmlBarang)){
                    System.out.println("Stok tidak mencukupi");
                    System.out.print("Keluar dari sub-menu? (Y/N) ");
                    String pil = input.next();
                    if(pil.toUpperCase().equals("Y")){
                        return;
                    }
                }
            }while(!checkIfStockAvailable(kodeBarang, jmlBarang));

            hargaSatuan = getHargaItem(kodeBarang);
            totalHargaSatuan = (hargaSatuan * jmlBarang);
            totalHarga = totalHarga + totalHargaSatuan;
            dataBarang[i][0] = kodeBarang;
            dataBarang[i][1] = Integer.toString(jmlBarang);
            dataBarang[i][2] = Float.toString(hargaSatuan);
            dataBarang[i][3] = Float.toString(totalHargaSatuan);

            updateStockAfterTransaction(kodeBarang, jmlBarang);
        }
        kodeTransaksi = randomKodeTransaksi();
        
        do{
            kodeTransaksi = randomKodeTransaksi();
        }while(checkIfKodeTransaksiExists(kodeTransaksi));
        
        listTransaksi.add(new Transaksi(kodeTransaksi, dataBarang, totalHarga));
        
        totalPendapatan += totalHarga;
        
        for(Transaksi ltr: listTransaksi){
            ltr.displayTransctionItem();
        }
    }

    static void updateStockAfterTransaction(String kodeBarang, int jmlBarang){
        for(Stok lb: listBarang){
            if(kodeBarang.toUpperCase().equals(lb.getKodeBarang()) ){
                lb.updateStokAfterTransaction(jmlBarang);
            }
        }
    }

    static void informasiPendapatan(){
        System.out.println();
        System.out.println("=== INFORMASI PENDAPATAN ===");
        System.out.println("Total Pendapatan: "+totalPendapatan);
        System.out.println("Total Transaksi: "+listTransaksi.size());
        System.out.println();
    }

    static float getHargaItem(String kodeBarang){
        float hargaSatuan= 0;
        for(Stok lb: listBarang){
            if(kodeBarang.toUpperCase().equals(lb.getKodeBarang()) ){
                hargaSatuan = lb.getHarga();
            }
        }
        return hargaSatuan;
    }

    static void displayAllBarang(){
        System.out.println("--- Data Barang ---");
        System.out.println();
        for(Stok str: listBarang){
            System.out.println("Kode Barang: "+str.getKodeBarang());
            System.out.println("Nama Barang: "+str.getNamaBarang());
            System.out.println("Harga: "+str.getHarga());
            System.out.println("Stok: "+str.getStok());

            System.out.println();
            System.out.println("-----------------");
            System.out.println();
        }
    }

    static void riwayatSeluruhTransaksi(){
        System.out.println("=== RIWAYAT SELURUH TRANSAKSI ===");
        
        for(Transaksi ltr: listTransaksi){
            ltr.historyAllTransaksi();
        }
        
        System.out.println("= END OF RIWAYAT SELURUH TRANSAKSI =");
    }

    static void riwayatRincianTransaksi(){

        System.out.println("=== RIWAYAT RINCIAN TRANSAKSI ===");
        String kodeTransaksi, pil;
        System.out.println("Kode transaksi dapat dilihat pada menu nomor 4 ");
        do{
            System.out.print("\nMasukkan kode transaksi: ");
            kodeTransaksi = input.next();

            if(!checkIfKodeTransaksiExists(kodeTransaksi)){
                System.out.println("Kode transaksi tidak ditemukan!");
                System.out.print("Keluar dari sub-menu? (Y/N) ");
                pil = input.next();
                if(pil.toUpperCase().equals("Y")){
                    return;
                }
            }
        }while(!checkIfKodeTransaksiExists(kodeTransaksi));
        
        for(Transaksi ltr: listTransaksi){
            if(kodeTransaksi.toUpperCase().equals(ltr.getKodeTransaksi()) ){
                ltr.displayTransaksiData();
            }
        }
    }

    static void generateDummyData(){

        System.out.println("--GENERATE DUMMY DATA--");

        listBarang.add(new Stok("A001", "AQUA", 345, 4000));
        listBarang.add(new Stok("A002", "VIT", 500, 3000));
        listBarang.add(new Stok("A003", "Le Minerale", 600, 5000));
        listBarang.add(new Stok("A004", "Indomie Goreng", 1145, 2500));
        listBarang.add(new Stok("A005", "Mie Sedap Goreng", 1245, 3000));
        listBarang.add(new Stok("A006", "Ultra UHT Low Fat 1L", 545, 2000));
        listBarang.add(new Stok("A007", "Ultra UHT Low Fat 250ML", 1555, 4000));
        listBarang.add(new Stok("A008", "Coca-cola 1L", 745, 10000));
        listBarang.add(new Stok("A009", "Coca-cola 400ML", 945, 4000));
        listBarang.add(new Stok("A010", "Listerin 250ML", 945, 9000));
        listBarang.add(new Stok("A011", "Pepsodent", 3453, 9000));
        listBarang.add(new Stok("A012", "Chitato", 4323, 5000));
        listBarang.add(new Stok("A013", "Freshtea", 937, 5000));
        listBarang.add(new Stok("A014", "GoodDay 250ML", 1230, 9000));
        listBarang.add(new Stok("A015", "Kopiko 300ML", 3533, 7000));

        System.out.println("Succesfully add 15 data");
    }

    static String randomKodeTransaksi(){
        int length = 5;
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";

        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
        SecureRandom random = new SecureRandom();

        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            sb.append(rndChar);
        }
        
        return sb.toString().toUpperCase();
    }
}
