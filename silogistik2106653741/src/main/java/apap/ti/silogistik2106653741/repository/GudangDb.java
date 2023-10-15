package apap.ti.silogistik2106653741.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import apap.ti.silogistik2106653741.model.Gudang;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
 
@Repository
public interface GudangDb extends JpaRepository<Gudang, UUID>{
    
}

