package apap.ti.silogistik2106653741.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang_barang")
public class GudangBarang {

    @Id
    @NotNull
    // @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_gudang", referencedColumnName = "id", nullable = false)
    private Gudang gudang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku", nullable = false)
    private Barang barang;

    @NotNull
    @Column(name = "stok", nullable = false)
    private int stok;
}
