package apap.ti.silogistik2106653741.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.repository.BarangDb;
import apap.ti.silogistik2106653741.repository.GudangDb;

@Service
public class BarangService {

    @Autowired
    BarangDb barangDb;

    public List<Barang> getAllBarang() {
        return barangDb.findAll();
    }

    public List<Barang> getAllBarangOrderedByMerk() {
        return barangDb.findAllByOrderByMerkAsc();
    }
    

    public Barang getBarangBySku(String sku){
        for(Barang barang : getAllBarang()) {
            if (barang.getSku().equals(sku)){
                return barang;
            }
        }
        return null;
    }
    
    public void saveBarang(Barang barang) {
        // Mengatur tipe barang sesuai dengan format SKU
        String tipeBarang = "";
        switch (barang.getTipe_barang()) {
            case 1:
                tipeBarang = "ELEC";
                break;
            case 2:
                tipeBarang = "CLOT";
                break;
            case 3:
                tipeBarang = "FOOD";
                break;
            case 4:
                tipeBarang = "COSM";
                break;
            case 5:
                tipeBarang = "TOOL";
                break;
            default:
                // Tipe barang tidak valid, atur tipe barang default atau tangani kesalahan lainnya
                tipeBarang = "NoType";
        }

        // Dapatkan nomor SKU yang baru
        int nomorSKU = barangDb.countByTipe_Barang(barang.getTipe_barang()) + 1;

        // Format nomor SKU menjadi tiga digit
        String nomorSKUFormatted = String.format("%03d", nomorSKU);

        // Gabungkan tipe barang dan nomor SKU
        String sku = tipeBarang + nomorSKUFormatted;

        // Atur SKU barang
        barang.setSku(sku);

        // Simpan barang ke dalam database
        barangDb.save(barang);
    }

    public int getStokBarang(Barang barang) {
        int stok = 0;
        for (GudangBarang gudangBarang : barang.getListGudang()) {
            stok += gudangBarang.getStok();
        }
        return stok;
    }

    public Barang updateBarang(Barang barangFromDTO){
        Barang barang = getBarangBySku(barangFromDTO.getSku());
        if (barang != null) {
            barang.setHarga_barang(barangFromDTO.getHarga_barang());
            barang.setMerk(barangFromDTO.getMerk());
            barangDb.save(barang);
        }
        return barang;
    }
}
