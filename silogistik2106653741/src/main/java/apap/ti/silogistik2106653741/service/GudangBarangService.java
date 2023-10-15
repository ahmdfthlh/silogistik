package apap.ti.silogistik2106653741.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.repository.BarangDb;
import apap.ti.silogistik2106653741.repository.GudangBarangDb;
import apap.ti.silogistik2106653741.repository.GudangDb;

@Service
public class GudangBarangService {

    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    public List<GudangBarang> findGudangBarangBySKU(String sku) {

        // Dapatkan daftar GudangBarang yang memiliki barang dengan SKU yang sesuai
        List<GudangBarang> listGudangBarang = gudangBarangDb.findByBarangSku(sku);

        return listGudangBarang;
    }

    public GudangBarang findByGudangAndBarang(Gudang gudang, Barang barang) {
        return gudangBarangDb.findByGudangAndBarang(gudang, barang);
    }

   

}
