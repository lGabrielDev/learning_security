package br.com.lGabrielDev.praticando.models.usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long>{
    
    //queries

    //LIST ALL usuarios
    @Query(nativeQuery = false, value = "SELECT u FROM Usuario u ORDER BY u.id ASC")
    public List<Usuario> findAll();

    //LIST ALL apenas dos usuarios que possuem o cargo de "ADMIN"
    @Query(nativeQuery = false, value = "SELECT u FROM Usuario u JOIN u.authorities r WHERE r.authority = :cargo")
    public List<Usuario> findAll(@Param(value = "cargo") String cargo);


    //Buscando um Usuario pelo "username"
    @Query(nativeQuery = false, value = "SELECT u FROM Usuario u WHERE u.username = :username")
    public Optional<Usuario> findByUsername(@Param(value = "username") String username);
}
