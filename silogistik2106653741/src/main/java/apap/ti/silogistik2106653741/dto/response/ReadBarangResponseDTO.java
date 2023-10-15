package apap.ti.silogistik2106653741.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.model.PermintaanPengirimanBarang;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private String sku;

    private int tipe_barang;

    private String merk;

    private BigInteger harga_barang;

    private List<GudangBarang> listGudang;

    // private List<PermintaanPengirimanBarang> listPermintaanPengiriman;
}
