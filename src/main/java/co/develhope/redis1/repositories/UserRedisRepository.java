package co.develhope.redis1.repositories;

import co.develhope.redis1.entities.redis.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<UserRedis, Long> {
}
