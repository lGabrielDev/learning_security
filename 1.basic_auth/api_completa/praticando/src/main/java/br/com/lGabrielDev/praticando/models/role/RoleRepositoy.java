package br.com.lGabrielDev.praticando.models.role;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoy extends JpaRepository<Role, Long> {
    
    //queries
    @Query(nativeQuery = false, value = "SELECT r FROM Role r WHERE r.authority = :cargo")
    public Optional<Role> findByAuthority(@Param(value = "cargo") String authority);
}
