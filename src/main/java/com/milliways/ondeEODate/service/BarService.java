package com.milliways.ondeEODate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.milliways.ondeEODate.model.Bar;
import com.milliways.ondeEODate.repository.BarRepository;

@Service
public class BarService {
    @Autowired
    private BarRepository barRepository;

    public List<Bar> getBares(Integer pageNumb) {
        Integer size = 6;
        Pageable page = PageRequest.of(pageNumb, size);
        Page<Bar> bares = this.barRepository.findAll(page);
        return bares.toList();
    }

    public Bar getBar(Long id) {
        Optional<Bar> maybeBar = this.barRepository.findById(id);
        if(maybeBar.isPresent()) {
            Bar bar = maybeBar.get();
            return bar;
        } else {
            return null;
        }
    }

    public List<Bar> searchBar(Optional<String> prefix, Optional<String> gasto, 
        Optional<String> clima, Integer pageNumb) {

        Pageable page = PageRequest.of(pageNumb, 6);
        Page<Bar> bares;
        if(prefix.isPresent() && gasto.isEmpty() && clima.isEmpty()) {
            bares = this.barRepository
                .findByNomeStartsWithIgnoreCase(prefix.get(), page);

        } else if(prefix.isEmpty() && (gasto.isPresent() || clima.isPresent())) {
            if (gasto.isPresent() && clima.isEmpty()) {
                bares = this.barRepository.findByGasto(gasto.get(), page);

            } else if (gasto.isEmpty() && clima.isPresent()) {
                bares = this.barRepository.findByClima(clima.get(), page);

            } else {
                bares = this.barRepository
                    .findByGastoAndClima(gasto.get(), clima.get(), page);
            }

        } else if(prefix.isPresent() && gasto.isPresent() && clima.isPresent()) {
            bares = this.barRepository
                .findByGastoAndClimaAndNomeStartsWithIgnoreCase(
                    gasto.get(), clima.get(), prefix.get(), page);
        } else {
            bares = this.barRepository.findAll(page);
        }
        
        return bares.toList();
    }

    public Bar salvaBar(Bar bar) {
        Bar barSalvo = this.barRepository.save(bar);
        return barSalvo;
    }

    public Bar updataBar(Long id, Bar bar) {
        bar.setId(id);
        Bar barSalvo = this.barRepository.save(bar);
        return barSalvo;
    }

    public void deleta(Long id) {
        this.barRepository.deleteById(id);
    }
}
