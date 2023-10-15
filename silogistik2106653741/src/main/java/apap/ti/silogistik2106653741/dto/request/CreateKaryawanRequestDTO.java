package apap.ti.silogistik2106653741.dto.request;

import java.util.Date;
import java.util.List;

import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.model.PermintaanPengiriman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    private String name;

    private int jenis_kelamin;

    private Date tanggal_lahir;

    private List<PermintaanPengiriman> listPermintaanPengiriman;
}
