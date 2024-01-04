package fpt.mooc.repository;

import fpt.mooc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    User findByUserName(String userName);

    @Override
    <S extends User> S save(S entity);

}
