package com.bussinessIt.api.repository;

import com.bussinessIt.api.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository <Movimiento, Long>{
}
