package apap.ti.silogistik2106653741.dto.request;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import apap.ti.silogistik2106653741.model.GudangBarang;
import apap.ti.silogistik2106653741.model.Karyawan;
import apap.ti.silogistik2106653741.model.PermintaanPengirimanBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private String nama_penerima;
    private String alamat_penerima;
    @DateTimeFormat(iso = ISO.DATE)
    private Date tanggal_pengiriman;
    private int biaya_pengiriman;
    private int jenis_layanan;
    private Karyawan karyawan;
    private List<PermintaanPengirimanBarang> listBarang;
}
