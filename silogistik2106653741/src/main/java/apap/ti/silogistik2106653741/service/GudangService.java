package apap.ti.silogistik2106653741.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.repository.GudangBarangDb;
import apap.ti.silogistik2106653741.repository.GudangDb;

@Service
public class GudangService {

    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    public Gudang createGudang(Gudang gudang) {
        return gudangDb.save(gudang);
    }

    public Gudang saveGudang(Gudang gudangDTO){
        Gudang gudang = getGudangById(gudangDTO.getId());
        if (gudang != null) {
            gudang.setId(gudangDTO.getId());
            gudang.setAlamat_gudang(gudangDTO.getAlamat_gudang());
            gudang.setNama(gudangDTO.getNama());
            gudang.setListBarang(gudangDTO.getListBarang());

            gudangDb.save(gudang);
        }
        return gudang;
    }

    public List<Gudang> getAllGudang() {
        return gudangDb.findAll();
    }

    public Gudang getGudangById(long idGudang){
        for(Gudang gudang : getAllGudang()) {
            if (gudang.getId() == idGudang){
                return gudang;
            }
        }
        return null;
    }

    public List<GudangBarang> findGudangBarangBySKU(String sku) {

        // Dapatkan daftar GudangBarang yang memiliki barang dengan SKU yang sesuai
        List<GudangBarang> gudangBarangs = gudangBarangDb.findByBarangSku(sku);

        return gudangBarangs;
    }
}
