package apap.ti.silogistik2106653741.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import apap.ti.silogistik2106653741.dto.BarangMapper;
import apap.ti.silogistik2106653741.dto.GudangMapper;
import apap.ti.silogistik2106653741.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.service.BarangService;
import apap.ti.silogistik2106653741.service.GudangService;
import jakarta.validation.Valid;

@Controller
public class BarangController {

    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;

    @GetMapping("/barang")
    public String viewAllBarang(Model model){
        List<Barang> listBarang = barangService.getAllBarang();

        model.addAttribute("listBarang", listBarang);
        return "viewall-barang";
    }

     @GetMapping("/barang/{sku}")
     public String viewBarangDetail(@PathVariable("sku") String sku,Model model) {
        var barang = barangService.getBarangBySku(sku);

        var barangDTO = barangMapper.barangToReadBarangResponseDTO(barang);

        model.addAttribute("sku", sku);
        model.addAttribute("barang", barang);

        return "view-detail-barang";
     }

    @GetMapping("/barang/tambah")
    public String formAddBarang(Model model) {
        var barangDTO = new CreateBarangRequestDTO();

        model.addAttribute("barangDTO", barangDTO);

        return "form-create-barang";
    }

    @PostMapping("/barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            var errorMessage = "";
            if(!errors.isEmpty()){
                for (FieldError error : errors ) {
                    errorMessage += (" - " + error.getDefaultMessage() + "\n");
                }
            }
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
    
        var barang = barangMapper.CreateBarangRequestDTOToBarang(barangDTO);
        barangService.saveBarang(barang);


        return "redirect:/barang";
    }

    @GetMapping("/barang/{sku}/ubah")
    public String formUpdateBarang(@PathVariable("sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);

        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        // model.addAttribute("sku", barang.getSku());
        model.addAttribute("barangDTO", barangDTO);

        return "form-update-barang";
    }

    @PostMapping("/barang/{sku}/ubah")
    public String updateBarangSubmit(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            var errorMessage = "";
            if(!errors.isEmpty()){
                for (FieldError error : errors ) {
                    errorMessage += (error.getDefaultMessage() + "\n");
                }
            }
            model.addAttribute("errorMessage", errorMessage);
            return "error-view";
        }
        var barangFromDto = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
        var barang = barangService.updateBarang(barangFromDto);
        model.addAttribute("sku", barangDTO.getSku());
        return "redirect:/barang/" +  barangDTO.getSku();
    }

}
