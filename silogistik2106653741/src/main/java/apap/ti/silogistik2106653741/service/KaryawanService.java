package apap.ti.silogistik2106653741.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.Karyawan;
import apap.ti.silogistik2106653741.repository.KaryawanDb;


@Service
public class KaryawanService {
    @Autowired
    KaryawanDb karyawanDb;

    public Karyawan createKaryawan(Karyawan karyawan) {
        return karyawanDb.save(karyawan);
    }

    public List<Karyawan> getAllKaryawan() {
        return karyawanDb.findAll();
    }
}
