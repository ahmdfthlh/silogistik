package apap.ti.silogistik2106653741.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653741.model.Barang;
import apap.ti.silogistik2106653741.model.Gudang;
import apap.ti.silogistik2106653741.model.GudangBarang;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
 
@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, UUID>{
    @Query("SELECT gb FROM GudangBarang gb WHERE gb.barang.sku = :sku")
    List<GudangBarang> findByBarangSku(@Param("sku")String sku);

    GudangBarang findByGudangAndBarang(Gudang gudang, Barang barang);
}

