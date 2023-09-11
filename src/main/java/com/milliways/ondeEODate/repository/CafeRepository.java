package com.milliways.ondeEODate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.milliways.ondeEODate.model.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
    public Page<Cafe> findByNomeStartsWithIgnoreCase(String prefix, Pageable page);

    public Page<Cafe> findByGastoAndClima(String gasto, String clima, Pageable page);

    public Page<Cafe> findByGasto(String gasto, Pageable page);
    
    public Page<Cafe> findByClima(String clima, Pageable page);

    public Page<Cafe> findByGastoAndClimaAndNomeStartsWithIgnoreCase(
        String gasto, String clima, String prefix, Pageable page);
}
