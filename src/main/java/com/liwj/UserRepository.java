package com.liwj;

import com.oracle.deploy.update.UpdateCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> ,JpaSpecificationExecutor<User> {

    User findById(Integer i);

    User findByUserName(String user4);

    @Query("UPDATE User u set u.password=?2 WHERE u.userName =?1")
    @Modifying
    @Transactional
    void updatePwd(String user3, String s);

    @Query("update User u set u.email= :email where u.userName = :user")
    @Modifying
    @Transactional
    void updateEmail(@Param("user") String userName, @Param("email") String email);

    @Query(value = "select count(id) from User ",nativeQuery = true)
    public long getTotalCount();

    @Query(value = "select u.* from t_User u JOIN t_dept d WHERE u.user_name=d.dept_name",nativeQuery = true)
    User findQuery();
}