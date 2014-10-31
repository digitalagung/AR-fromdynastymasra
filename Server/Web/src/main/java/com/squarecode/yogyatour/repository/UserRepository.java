package com.squarecode.yogyatour.repository;

import com.squarecode.yogyatour.domain.User;
import com.squarecode.yogyatour.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long>, UserRepositoryCustom {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    public User findByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u")
    public List<User> findAll();
}
