package com.tugas.penjualan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tugas.penjualan.entity.PenjualanEntity;

@Repository 
public interface PenjualanRepository extends JpaRepository<PenjualanEntity, Integer> {
    List<PenjualanEntity> findAll();
}
