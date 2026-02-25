package com.tugas.barang.repository;
import com.tugas.barang.entity.BarangEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangRepository extends JpaRepository<BarangEntity, Integer> {
    public List<BarangEntity> findAll();
}
