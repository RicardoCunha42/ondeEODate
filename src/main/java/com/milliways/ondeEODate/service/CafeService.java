package com.milliways.ondeEODate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.milliways.ondeEODate.model.Cafe;
import com.milliways.ondeEODate.repository.CafeRepository;

@Service
public class CafeService {
    @Autowired
    private CafeRepository cafeRepository;

    public List<Cafe> getCafes(Integer pageNumb) {
        Integer size = 6;
        Pageable page = PageRequest.of(pageNumb, size);
        Page<Cafe> cafes = this.cafeRepository.findAll(page);
        return cafes.toList();
    }

    public Cafe getCafe(Long id) {
        Optional<Cafe> maybeCafe = this.cafeRepository.findById(id);
        Cafe cafe = maybeCafe.get();
        return cafe;
    }
    
    public List<Cafe> searchCafe(Optional<String> prefix, Optional<String> gasto, 
        Optional<String> clima, Integer pageNumb) {

        Pageable page = PageRequest.of(pageNumb, 6);
        Page<Cafe> cafes;
        if(prefix.isPresent() && gasto.isEmpty() && clima.isEmpty()) {
            cafes = this.cafeRepository
                .findByNomeStartsWithIgnoreCase(prefix.get(), page);

        } else if(prefix.isEmpty() && (gasto.isPresent() || clima.isPresent())) {
            if (gasto.isPresent() && clima.isEmpty()) {
                cafes = this.cafeRepository.findByGasto(gasto.get(), page);

            } else if (gasto.isEmpty() && clima.isPresent()) {
                cafes = this.cafeRepository.findByClima(clima.get(), page);

            } else {
                cafes = this.cafeRepository
                    .findByGastoAndClima(gasto.get(), clima.get(), page);
            }

        } else if(prefix.isPresent() && gasto.isPresent() && clima.isPresent()) {
            cafes = this.cafeRepository
                .findByGastoAndClimaAndNomeStartsWithIgnoreCase(
                    gasto.get(), clima.get(), prefix.get(), page);
        } else {
            cafes = this.cafeRepository.findAll(page);
        }
        
        return cafes.toList();
    }


    public Cafe salvaCafe(Cafe cafe) {
        Cafe cafeSalvo = this.cafeRepository.save(cafe);
        return cafeSalvo;
    }

    public Cafe atualizaCafe(Long id, Cafe cafe) {
        cafe.setId(id);
        Cafe cafeAtualizado = this.cafeRepository.save(cafe);
        return cafeAtualizado;
    }

    public void deleta(Long id) {
        this.cafeRepository.deleteById(id);
    }


}
