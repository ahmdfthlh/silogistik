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

import apap.ti.silogistik2106653741.dto.GudangBarangMapper;
import apap.ti.silogistik2106653741.dto.GudangMapper;
import apap.ti.silogistik2106653741.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateGudangBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.service.BarangService;
import apap.ti.silogistik2106653741.service.GudangBarangService;
import apap.ti.silogistik2106653741.service.GudangService;

@Controller
public class GudangController {
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

    @GetMapping("/gudang")
    public String viewAllGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);
        return "viewall-gudang";
    }

    @GetMapping("/gudang/{idGudang}")
    public String viewGudangDetail(@PathVariable("idGudang") long idGudang,Model model) {
        var gudang = gudangService.getGudangById(idGudang);

        var gudangDTO =  gudangMapper.gudangToReadGudangResponseDTO(gudang);

        model.addAttribute("gudang", gudangDTO);
        return "view-detail-gudang";
    }

    @GetMapping("/gudang/cari-barang")
    public String cariBarangDiGudang(@RequestParam(value = "sku", required = false) String sku,Model model) {
        if (sku != null ) {
            List<GudangBarang> listGudangBarang = gudangService.findGudangBarangBySKU(sku);

            var barangCari = barangService.getBarangBySku(sku);

            model.addAttribute("barangCari", barangCari);
            model.addAttribute("listGudangBarang", listGudangBarang);
        }

        List<Barang> listBarang = barangService.getAllBarangOrderedByMerk();

        model.addAttribute("listBarang", listBarang);

        return "search-barang";
    }

    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockBarang(@PathVariable("idGudang") long idGudang,Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        var gudangDTO =  gudangMapper.gudangToUpdateGudangRequestDTO(gudang);
        model.addAttribute("gudangDTO", gudangDTO);

        var listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);
        
        return "form-restock-barang";
    }

    @PostMapping("/gudang/{idGudang}/restock-barang")
    public String simpanRestockBarang(@ModelAttribute UpdateGudangRequestDTO gudangDTO, @PathVariable("idGudang") long idGudang) {
         // Validasi jika listBarang kosong
        if (gudangDTO.getListBarang() == null || gudangDTO.getListBarang().isEmpty()) {
            return "failed-restock-barang"; 
        }

        // Mengambil gudang target
        var gudangTarget = gudangService.getGudangById(idGudang);

        // Mendapatkan list barang yang akan direstock dari DTO
        List<GudangBarang> listBarangRestock = gudangDTO.getListBarang();

        // Mendapatkan list barang yang ada di gudangTarget
        List<GudangBarang> listBarangGudang = gudangTarget.getListBarang();

        // Melakukan iterasi melalui listBarangRestock
        for (GudangBarang barangRestock : listBarangRestock) {
            // Mengecek apakah barangRestock sudah ada dalam listBarangGudang
            boolean barangExistsInGudang = false;
            for (GudangBarang gudangBarang : listBarangGudang) {
                if (gudangBarang.getBarang().getSku().equals(barangRestock.getBarang().getSku())) {
                    // Barang sudah ada dalam gudang, tambahkan stok
                    gudangBarang.setStok(gudangBarang.getStok() + barangRestock.getStok());
                    barangExistsInGudang = true;
                    break;
                }
            }
            // Jika barangRestock belum ada dalam listBarangGudang, tambahkan
            if (!barangExistsInGudang) {
                GudangBarang newGudangBarang = new GudangBarang();
                newGudangBarang.setBarang(barangRestock.getBarang());
                newGudangBarang.setGudang(gudangTarget);
                newGudangBarang.setStok(barangRestock.getStok());
                listBarangGudang.add(newGudangBarang);
            }
        }

        // Simpan perubahan ke database
        gudangService.saveGudang(gudangTarget);

        // Redirect kembali ke halaman gudang
        return "redirect:/gudang/" + gudangTarget.getId();
    }



    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowBarangUpdateGudang(@ModelAttribute UpdateGudangRequestDTO updateGudangRequestDTO, @PathVariable("idGudang") long idGudang,  Model model) {
        if ( updateGudangRequestDTO.getListBarang() == null || updateGudangRequestDTO.getListBarang().size() == 0) {
            updateGudangRequestDTO.setListBarang(new ArrayList<>());
        }

        // Memasukkan Barang baru (kosong) ke list, untuk dirender sebagai row baru
        updateGudangRequestDTO.getListBarang().add(new GudangBarang());
        updateGudangRequestDTO.setNama(gudangService.getGudangById(idGudang).getNama());
        updateGudangRequestDTO.setAlamat_gudang(gudangService.getGudangById(idGudang).getAlamat_gudang());
        updateGudangRequestDTO.setId(gudangService.getGudangById(idGudang).getId());
        
        var listBarangExisting = barangService.getAllBarang();

        model.addAttribute("listBarangExisting", listBarangExisting);
        model.addAttribute("gudangDTO", updateGudangRequestDTO);

        return "form-restock-barang";
    }

}
