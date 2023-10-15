package apap.ti.silogistik2106653741.dto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import apap.ti.silogistik2106653741.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106653741.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106653741.model.Karyawan;
import apap.ti.silogistik2106653741.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);


}
