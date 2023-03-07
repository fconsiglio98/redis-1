package co.develhope.redis1.services;

import co.develhope.redis1.entities.jpa.UserJPA;
import co.develhope.redis1.entities.redis.UserRedis;
import co.develhope.redis1.repositories.UserJPARepository;
import co.develhope.redis1.repositories.UserRedisRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserJPARepository userJPARepository;

    @Autowired
    private UserRedisRepository userRedisRepository;


    public UserRedis convertToRedisEntity(UserJPA user){
        UserRedis userRedis = new UserRedis();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public UserJPA convertToJPAEntity(UserRedis user){
        UserJPA userRedis = new UserJPA();
        BeanUtils.copyProperties(user, userRedis);
        return userRedis;
    }

    public UserJPA create(UserJPA user) {
        if(user == null) return null;
        user.setId(null);
        return userJPARepository.save(user);
    }

    public List<UserJPA> readAll() {
        return userJPARepository.findAll();
    }


    public UserJPA readOne(Long id) {

        Optional<UserRedis> userRedis = userRedisRepository.findById(id);
        if(userRedis.isPresent()){
            return convertToJPAEntity(userRedis.get());

        }else {
            Optional<UserJPA> userFromDB = userJPARepository.findById(id);
            if(userFromDB.isEmpty()) return null;
            userRedisRepository.save(convertToRedisEntity(userFromDB.get()));
            return userFromDB.get();
        }
    }

    public UserJPA update(Long id, UserJPA user) {
        if(user == null) return null;
        user.setId(id);

        Optional<UserRedis> userRedis = userRedisRepository.findById(id);
        if(userRedis.isPresent()) {
            userRedisRepository.save(userRedis.get());
        }

        return userJPARepository.saveAndFlush(user);
    }

    public void delete(Long id) {
        userJPARepository.deleteById(id);
        userRedisRepository.deleteById(id);
    }

    public void deleteAll() {
        userJPARepository.deleteAll();
        userRedisRepository.deleteAll();
    }


}
