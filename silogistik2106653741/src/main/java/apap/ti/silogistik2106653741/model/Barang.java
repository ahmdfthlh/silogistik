package apap.ti.silogistik2106653741.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @NotNull
    @Size(max = 7)
    @Column(name = "sku", nullable = false)
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private int tipe_barang;

    @NotNull
    @Column(name = "merk", nullable = false)
    private String merk;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private BigInteger harga_barang;

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<GudangBarang> listGudang = new ArrayList<>();

    @OneToMany(mappedBy = "barang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PermintaanPengirimanBarang> listPermintaanPengiriman = new ArrayList<>();
}