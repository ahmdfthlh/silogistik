package apap.ti.silogistik2106653741.dto.request;

import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106653741.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String merk;

    private int tipe_barang;

    private BigInteger harga_barang;

    private List<GudangBarang> listGudang;

}
