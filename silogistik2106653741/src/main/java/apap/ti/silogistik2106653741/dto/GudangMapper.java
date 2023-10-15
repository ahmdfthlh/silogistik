package apap.ti.silogistik2106653741.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106653741.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106653741.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106653741.dto.response.ReadGudangResponseDTO;
import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);

    Gudang updateGudangRequestDTOToGudang(UpdateGudangRequestDTO updateGudangRequestDTO);

    UpdateGudangRequestDTO gudangToUpdateGudangRequestDTO(Gudang gudang);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nama", ignore = true)
    @Mapping(target = "alamat_gudang", ignore = true)
    @Mapping(target = "listBarang", ignore = true)
    ReadGudangResponseDTO gudangToReadGudangResponseDTO(Gudang gudang);

    @AfterMapping
    default void fillReadGudangResponseDTO(@MappingTarget ReadGudangResponseDTO readGudangResponseDTO, Gudang gudang) {
        readGudangResponseDTO.setId(gudang.getId());
        readGudangResponseDTO.setNama(gudang.getNama());
        readGudangResponseDTO.setAlamat_gudang(gudang.getAlamat_gudang());
        readGudangResponseDTO.setListBarang(gudang.getListBarang());
    }
}
