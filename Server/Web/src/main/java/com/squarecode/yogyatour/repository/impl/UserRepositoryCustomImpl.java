package com.squarecode.yogyatour.repository.impl;

import com.squarecode.yogyatour.domain.Role;
import com.squarecode.yogyatour.domain.User;
import com.squarecode.yogyatour.repository.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
@Repository
@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    private BCrypt bCrypt;

    @Override
    public String encodePassword(String plain, String salt) {
        return bCrypt.hashpw(plain, salt);
    }

    @Override
    public User create(String username, String plainPassword) {
        User user = new User(username);

        user.setSalt(bCrypt.gensalt(12));
        user.setPassword(bCrypt.hashpw(plainPassword, user.getSalt()));
        user.setEnabled(true);
        entityManager.persist(user);
        return user;
    }

    @Override
    public User create(String username, String plainPassword, List<Role> roles) {
        User user = new User();

        user.setUsername(username);
        user.setEnabled(true);
        user.setSalt(bCrypt.gensalt(12));
        user.setPassword(bCrypt.hashpw(plainPassword, user.getSalt()));
        user.setAuthorities(roles);
        entityManager.persist(user);
        return user;
    }
}
