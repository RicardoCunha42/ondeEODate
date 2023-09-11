package com.milliways.ondeEODate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.milliways.ondeEODate.model.Bar;

@Repository
public interface BarRepository extends JpaRepository<Bar, Long>  {

    @Query("select b from Bar b where b.nome like CONCAT('%', :prefix ,'%')")
    public Page<Bar> findByPrefix (@Param("prefix") String prefix, Pageable page);

    public Page<Bar> findByNomeStartsWithIgnoreCase(String prefix, Pageable page);

    public Page<Bar> findByGastoAndClima(String gasto, String clima, Pageable page);

    public Page<Bar> findByGasto(String gasto, Pageable page);
    
    public Page<Bar> findByClima(String clima, Pageable page);

    @Query("select b from Bar b where b.nome like CONCAT(:prefix ,'%') and b.gasto = :gasto and b.clima = :clima")
    public Page<Bar> findByAll (@Param("prefix") String prefix, @Param("gasto") String gasto,
        @Param("clima") String clima, Pageable page);

    public Page<Bar> findByGastoAndClimaAndNomeStartsWithIgnoreCase(
        String gasto, String clima, String prefix, Pageable page);
    
}
