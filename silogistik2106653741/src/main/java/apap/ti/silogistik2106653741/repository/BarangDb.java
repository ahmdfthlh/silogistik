package apap.ti.silogistik2106653741.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653741.model.Barang;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
 
@Repository
public interface BarangDb extends JpaRepository<Barang, UUID>{
    @Query(value = "SELECT COUNT(*) FROM barang WHERE tipe_barang = :tipeBarang", nativeQuery = true)
    int countByTipe_Barang(@Param("tipeBarang") int tipeBarang);

    List<Barang> findAllByOrderByMerkAsc();
}

