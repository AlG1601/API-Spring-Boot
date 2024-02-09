package com.example.teste.controller;

import com.example.teste.model.Usuario;
import com.example.teste.service.UsuarioService;
import com.example.teste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService testeServices;

    @Autowired
    UsuarioRepository testeBd;

    @CrossOrigin
    @PostMapping(value = "/create", produces = "application/json")
    public Usuario create(@RequestBody Usuario example){
        testeBd.save(example);
        return example;
    }

    @CrossOrigin
    @GetMapping("/list")
    public List<Usuario> findAllRecords() { return testeBd.findAll(); }

    @RequestMapping(value = "search/name/{name}", method = RequestMethod.GET)
    public Optional<Usuario> searchBynome(@PathVariable String name) { return testeBd.findBynome(name); }

    @CrossOrigin(origins = "*")
    @PutMapping("/update/cpf/{cpf}")
    public ResponseEntity<Usuario> updateByCpf(@PathVariable String cpf, @RequestBody Usuario movie) {
        Usuario usuario_pf = testeBd.findBycpf(cpf).orElse(null);

        if (usuario_pf != null) {
            usuario_pf.setNome(movie.getNome());
            usuario_pf.setSenha(movie.getSenha());
            usuario_pf.setEmail(movie.getEmail());
            testeBd.save(usuario_pf);
            return ResponseEntity.ok(usuario_pf);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/update/cpf/{cpf}", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptions() {
        return ResponseEntity.ok().allow(HttpMethod.PUT, HttpMethod.OPTIONS).build();
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/cpf/{cpf}")
    public String deleteById(@PathVariable String cpf) {
        Usuario teste = testeBd.findBycpf(cpf).get();
        testeBd.delete(teste);
        return "{deleted:"+cpf+"}";
    }


}
