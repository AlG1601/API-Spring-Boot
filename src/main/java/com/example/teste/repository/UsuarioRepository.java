package com.example.teste.repository;

import com.example.teste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findBynome(String nome);

    Optional<Usuario> findBycpf(String cpf);

    Optional<Usuario> findByemail(String email);
}
