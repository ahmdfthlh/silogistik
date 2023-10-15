package apap.ti.silogistik2106653741.dto.request;

import java.util.List;

import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangBarangRequestDTO {
    private int stok;

    private Barang barang;

    private Gudang gudang;
}
