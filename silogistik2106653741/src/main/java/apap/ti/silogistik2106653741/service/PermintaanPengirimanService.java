package apap.ti.silogistik2106653741.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106653741.dto.BarangMapper;
import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.model.Karyawan;
import apap.ti.silogistik2106653741.model.PermintaanPengiriman;
import apap.ti.silogistik2106653741.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106653741.repository.KaryawanDb;
import apap.ti.silogistik2106653741.repository.PermintaanPengirimanDb;


@Service
public class PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;

     public List<PermintaanPengiriman> getAllPermintaanPengiriman() {
        return permintaanPengirimanDb.findAll();
    }

    public List<PermintaanPengiriman> getAllPermintaanPengirimanOrderedByNewest(){
        return permintaanPengirimanDb.findAllByOrderByWaktuPermintaanDesc();
    }

    public PermintaanPengiriman getPermintaanPengirimanById(Long id){
        for(PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getId() == id){
                return permintaanPengiriman;
            }
        }
        return null;
    }

    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        // set waktu_permintaan
        LocalDateTime waktuSekarang = LocalDateTime.now(ZoneId.of("Asia/Jakarta"));
        permintaanPengiriman.setWaktuPermintaan(Date.from(waktuSekarang.atZone(ZoneId.of("Asia/Jakarta")).toInstant()));

        // set nomor_pengiriman
        String nomorPengiriman = "REQ";

        int jumlahBarang = 0;
        for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengiriman.getListBarang()){
            jumlahBarang += permintaanPengirimanBarang.getKuantitas_pengiriman();
        }

        if (jumlahBarang > 99) {
            jumlahBarang = jumlahBarang % 100;
        }

        nomorPengiriman += String.format("%02d", jumlahBarang);

        int nomorJenisLayanan = permintaanPengiriman.getJenis_layanan();
        if (nomorJenisLayanan == 1) {
            nomorPengiriman += "SAM";
        } else if (nomorJenisLayanan == 2) {
            nomorPengiriman += "KIL";
        } else if (nomorJenisLayanan == 3) {
            nomorPengiriman += "REG";
        } else {
            nomorPengiriman += "HEM";
        }

        nomorPengiriman += waktuSekarang.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        permintaanPengiriman.setNomor_pengiriman(nomorPengiriman);
        permintaanPengiriman.setIs_cancelled(false);

        List<PermintaanPengirimanBarang> barangDikirim = permintaanPengiriman.getListBarang();

        List<PermintaanPengirimanBarang> listBarang = new ArrayList<>();

        for (PermintaanPengirimanBarang barangRequest: barangDikirim){
            Barang barang = barangService.getBarangBySku(barangRequest.getBarang().getSku());

            PermintaanPengirimanBarang permintaanPengirimanBarang = new PermintaanPengirimanBarang();
            permintaanPengirimanBarang.setBarang(barang);
            permintaanPengirimanBarang.setPermintaanPengiriman(permintaanPengiriman);
            permintaanPengirimanBarang.setKuantitas_pengiriman(barangRequest.getKuantitas_pengiriman());
            listBarang.add(permintaanPengirimanBarang);
        }

        permintaanPengiriman.setListBarang(listBarang);
        permintaanPengirimanDb.save(permintaanPengiriman);

        // permintaanPengirimanDb.save(permintaanPengiriman);
    }

    public void deletePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setIs_cancelled(true);

        permintaanPengirimanDb.save(permintaanPengiriman);
    }

}
