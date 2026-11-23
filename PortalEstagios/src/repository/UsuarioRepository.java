
package repository;

import java.util.List;
import java.util.Optional;

import model.Perfil;
import model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE LOWER(TRIM(u.email)) = LOWER(TRIM(:email))")
    Optional<Usuario> findByEmail(@Param("email") String email);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE LOWER(TRIM(u.email)) = LOWER(TRIM(:email))")
    boolean existsByEmail(@Param("email") String email);

    List<Usuario> findAllByOrderByIdAsc();

    long countByPerfil(Perfil perfil);
}
