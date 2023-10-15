package apap.ti.silogistik2106653741.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import apap.ti.silogistik2106653741.dto.BarangMapper;
import apap.ti.silogistik2106653741.dto.GudangBarangMapper;
import apap.ti.silogistik2106653741.dto.GudangMapper;
import apap.ti.silogistik2106653741.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106653741.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateGudangBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.model.Karyawan;
import apap.ti.silogistik2106653741.model.PermintaanPengiriman;
import apap.ti.silogistik2106653741.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106653741.service.BarangService;
import apap.ti.silogistik2106653741.service.GudangBarangService;
import apap.ti.silogistik2106653741.service.GudangService;
import apap.ti.silogistik2106653741.service.KaryawanService;
import apap.ti.silogistik2106653741.service.PermintaanPengirimanService;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    BarangService barangService;

    @Autowired
    KaryawanService karyawanService;


    @GetMapping("/permintaan-pengiriman")
    public String viewallPermintaanPengiriman(Model model) {
        List<PermintaanPengiriman> listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengirimanOrderedByNewest();
        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);

        return "viewall-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
    public String viewPermintaanPengirimanDetail(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);

        model.addAttribute("idPermintaanPengiriman", idPermintaanPengiriman);
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);

        return "view-detail-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String deletePermintaanPengiriman(@PathVariable("idPermintaanPengiriman") long idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);

        permintaanPengirimanService.deletePermintaanPengiriman(permintaanPengiriman);

        model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomor_pengiriman());

        return "success-delete-permintaan-pengiriman";
    }

    @GetMapping("/permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model) {
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();

        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();

        var listBarangExisting = barangService.getAllBarangOrderedByMerk();

        model.addAttribute("listBarangExisting", listBarangExisting);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        return "form-create-permintaan-pengiriman";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String simpanPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO) {

        var permintaanPengirimanFromDTO = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);

        permintaanPengirimanService.savePermintaanPengiriman(permintaanPengirimanFromDTO);

        // Redirect kembali ke halaman gudang
        return "redirect:/permintaan-pengiriman";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowBarangCreatePermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model) {
        if ( createPermintaanPengirimanRequestDTO.getListBarang() == null || createPermintaanPengirimanRequestDTO.getListBarang().size() == 0) {
            createPermintaanPengirimanRequestDTO.setListBarang(new ArrayList<>());
        }

        // Memasukkan Barang baru (kosong) ke list, untuk dirender sebagai row baru
        createPermintaanPengirimanRequestDTO.getListBarang().add(new PermintaanPengirimanBarang());

        var listBarangExisting = barangService.getAllBarangOrderedByMerk();

        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();

        model.addAttribute("listBarangExisting", listBarangExisting);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("permintaanPengirimanDTO", createPermintaanPengirimanRequestDTO);

        return "form-create-permintaan-pengiriman";
    }


    // @GetMapping("/filter-permintaan-pengiriman")
    // public String filterPermintaanPengirimanByTanggal(@RequestParam(value = "sku") String sku,Model model)
}
