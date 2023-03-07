package co.develhope.redis1.repositories;

import co.develhope.redis1.entities.jpa.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<UserJPA, Long> {
}
