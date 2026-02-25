package com.tugas.barang.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tugas.barang.entity.PenjualanEntity;
@Repository
public interface PenjualanRepository  extends JpaRepository<PenjualanEntity, Integer> {
    @Query("SELECT COALESCE(SUM(p.jumlahBeli), 0) FROM PenjualanEntity p WHERE p.barang.idBarang = :idBarang")
    Integer getTotalTerjualByIdBarang(@Param("idBarang") int idBarang);
}
