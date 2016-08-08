package com.example.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Long> {
//
//    @Query("select a.role from UserRole a, User b where b.userName=?1 and a.userid=b.userId")
//    public List<String> findRoleByUserName(String username);

    public  List<String> findRoleByUser(User user);

}