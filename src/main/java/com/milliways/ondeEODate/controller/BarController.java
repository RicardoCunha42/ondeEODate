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

import com.milliways.ondeEODate.model.Bar;
import com.milliways.ondeEODate.service.BarService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("bares")
public class BarController {
    @Autowired
    private BarService barService;

    @GetMapping
    public ResponseEntity<List<Bar>> pegaTodosBares(@RequestParam(name = "page") Integer page) {
        List<Bar> bares = this.barService.getBares(page);
        return new ResponseEntity<List<Bar>>(bares, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Bar> pegaBar(@PathVariable Long id) {
        Bar bar = this.barService.getBar(id);
        return new ResponseEntity<Bar>(bar, HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<Bar>> pesquisaBares(
        @RequestParam(name = "prefix", required = false) Optional<String> prefix,
        @RequestParam(name = "gasto", required = false) Optional<String> gasto,
        @RequestParam(name = "clima", required = false) Optional<String> clima,
        @RequestParam(name = "page") Integer page) {

        List<Bar> bares = this.barService.searchBar(prefix, gasto, clima, page);
        return new ResponseEntity<List<Bar>>(bares, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bar> cadastraBar(@RequestBody Bar bar) {
        Bar barCadastrado = this.barService.salvaBar(bar);
        return new ResponseEntity<Bar>(barCadastrado, HttpStatus.CREATED);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Bar> atualizaBar(@PathVariable Long id, @RequestBody Bar bar) {
        Bar barCadastrado = this.barService.updataBar(id, bar);
        return new ResponseEntity<Bar>(barCadastrado, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<HttpStatus> deletaBar(@PathVariable Long id) {
        this.barService.deleta(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    
}
