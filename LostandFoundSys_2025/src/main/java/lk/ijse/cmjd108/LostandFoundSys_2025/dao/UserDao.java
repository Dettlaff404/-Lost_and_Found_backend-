package lk.ijse.cmjd108.LostandFoundSys_2025.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.cmjd108.LostandFoundSys_2025.entities.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, String> {

}
