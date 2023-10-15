package apap.ti.silogistik2106653741.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import apap.ti.silogistik2106653741.model.GudangBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadGudangResponseDTO {
    private long id;

    private String nama;

    private String alamat_gudang;

    private List<GudangBarang> listBarang;
}
