package apap.ti.silogistik2106653741.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import apap.ti.silogistik2106653741.dto.GudangBarangMapper;
import apap.ti.silogistik2106653741.dto.GudangMapper;
import apap.ti.silogistik2106653741.repository.BarangDb;
import apap.ti.silogistik2106653741.repository.GudangDb;
import apap.ti.silogistik2106653741.repository.KaryawanDb;
import apap.ti.silogistik2106653741.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106653741.service.BarangService;
import apap.ti.silogistik2106653741.service.GudangBarangService;
import apap.ti.silogistik2106653741.service.GudangService;

@Controller
public class BerandaController {
    @Autowired
    GudangService gudangService;

    @Autowired
    BarangService barangService;

    @Autowired
    GudangBarangService gudangBarangService;

    @Autowired
    GudangBarangMapper gudangBarangMapper;

    @Autowired
    GudangMapper gudangMapper;

    @Autowired
    GudangDb gudangDb;

    @Autowired
    BarangDb barangDb;

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    KaryawanDb karyawanDb;


    @GetMapping("/")
    public String home(Model model) {
        var jumlahGudang = gudangDb.count();
        var jumlahBarang = barangDb.count();
        var jumlahPermintaanPengiriman = permintaanPengirimanDb.count();
        var jumlahKaryawan = karyawanDb.count();

        model.addAttribute("jumlahGudang", jumlahGudang);
        model.addAttribute("jumlahBarang", jumlahBarang);
        model.addAttribute("jumlahPermintaanPengiriman", jumlahPermintaanPengiriman);
        model.addAttribute("jumlahKaryawan", jumlahKaryawan);
        return "home";
    }

}
