package apap.ti.silogistik2106653741.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106653741.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateGudangBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.response.ReadBarangResponseDTO;
import apap.ti.silogistik2106653741.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;

@Mapper(componentModel = "spring")
public interface GudangBarangMapper {
    GudangBarang CreateGudangBarangRequestDTOToGudangBarang(CreateGudangBarangRequestDTO createGudangBarangRequestDTO);
    
    GudangBarang updateGudangBarangRequestDTOToGudangBarang(UpdateGudangBarangRequestDTO updateGudangBarangRequestDTO);

    UpdateGudangBarangRequestDTO gudangBarangToUpdateGudangBarangRequestDTO(GudangBarang gudangBarang);


}
