package apap.ti.silogistik2106653741.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106653741.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106653741.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang CreateBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    
    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);

    @Mapping(target = "sku", ignore = true)
    @Mapping(target = "tipe_barang", ignore = true)
    @Mapping(target = "merk", ignore = true)
    @Mapping(target = "harga_barang", ignore = true)
    @Mapping(target = "listGudang", ignore = true)
    ReadBarangResponseDTO barangToReadBarangResponseDTO(Barang barang);


    @AfterMapping
    default void fillReadBarangResponseDTO(@MappingTarget ReadBarangResponseDTO readBarangResponseDTO, Barang barang) {
        readBarangResponseDTO.setSku(barang.getSku());
        readBarangResponseDTO.setTipe_barang(barang.getTipe_barang());
        readBarangResponseDTO.setMerk(barang.getMerk());
        readBarangResponseDTO.setHarga_barang(barang.getHarga_barang());
        readBarangResponseDTO.setListGudang(barang.getListGudang());

    }
}
