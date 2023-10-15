package apap.ti.silogistik2106653741.model;

// Import dependencies
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Import java module
import java.util.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 16)
    @Column(name = "nomor_pengiriman", nullable = false)
    private String nomor_pengiriman;

    @NotNull
    @Column(name = "is_cancelled", nullable = false)
    private Boolean is_cancelled = false;

    @NotNull
    @Column(name = "nama_penerima", nullable = false)
    private String nama_penerima;

    @NotNull
    @Column(name = "alamat_penerima", nullable = false)
    private String alamat_penerima;

    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggal_pengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private int biaya_pengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private int jenis_layanan;

    @NotNull
    @Column(name = "waktu_permintaan", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuPermintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id")
    Karyawan karyawan;

    @OneToMany(mappedBy = "permintaanPengiriman", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PermintaanPengirimanBarang> listBarang = new ArrayList<>();
}