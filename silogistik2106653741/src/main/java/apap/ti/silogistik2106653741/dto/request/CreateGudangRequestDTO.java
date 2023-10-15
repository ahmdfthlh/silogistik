package apap.ti.silogistik2106653741.dto.request;

import java.util.List;

import apap.ti.silogistik2106653741.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    private String nama;

    private String alamat_gudang;

    private List<GudangBarang> listBarang;
}
