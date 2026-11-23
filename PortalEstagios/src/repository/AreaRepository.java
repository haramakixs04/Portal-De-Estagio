
package repository;

import java.util.List;

import model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findAllByOrderByNomeAsc();

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Area a WHERE LOWER(TRIM(a.nome)) = LOWER(TRIM(:nome))")
    boolean existsByNome(@Param("nome") String nome);

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Area a WHERE LOWER(TRIM(a.nome)) = LOWER(TRIM(:nome)) AND a.id <> :id")
    boolean existsByNomeAndIdNot(
        @Param("nome") String nome,
        @Param("id") long id
    );
}
