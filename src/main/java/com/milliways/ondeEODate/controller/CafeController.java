package com.milliways.ondeEODate.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milliways.ondeEODate.model.Cafe;
import com.milliways.ondeEODate.service.CafeService;

@CrossOrigin(origins = {"http://localhost:4200/", "https://onde-e-o-date-front.vercel.app/"})
@RestController
@RequestMapping("cafes")
public class CafeController {
    @Autowired
    private CafeService cafeService;
    
    @GetMapping
    public ResponseEntity<List<Cafe>> pegaTodosCafes(@RequestParam(name = "page") Integer page) {
        List<Cafe> cafes = this.cafeService.getCafes(page);
        return new ResponseEntity<List<Cafe>>(cafes, HttpStatus.OK);
    }
    
    @GetMapping(path = "{id}")
    public ResponseEntity<Cafe> pegaCafe(@PathVariable Long id) {
        Cafe cafe = this.cafeService.getCafe(id);
        return new ResponseEntity<Cafe>(cafe, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<Cafe>> pesquisaCafes(
        @RequestParam(name = "prefix", required = false) Optional<String> prefix,
        @RequestParam(name = "gasto", required = false) Optional<String> gasto,
        @RequestParam(name = "clima", required = false) Optional<String> clima,
        @RequestParam(name = "page") Integer page) {

        List<Cafe> cafes = this.cafeService.searchCafe(prefix, gasto, clima, page);
        return new ResponseEntity<List<Cafe>>(cafes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cafe> cadastraCafe(@RequestBody Cafe cafe) {
        Cafe cafeSalvo = this.cafeService.salvaCafe(cafe);
        return new ResponseEntity<Cafe>(cafeSalvo, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Cafe> pegaCafe(@PathVariable Long id, @RequestBody Cafe cafe) {
        Cafe cafeAtualizado = this.cafeService.atualizaCafe(id, cafe);
        return new ResponseEntity<Cafe>(cafeAtualizado, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deletaCafe(@PathVariable Long id) {
        this.cafeService.deleta(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
