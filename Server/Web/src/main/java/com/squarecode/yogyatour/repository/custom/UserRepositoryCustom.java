package com.squarecode.yogyatour.repository.custom;

import com.squarecode.yogyatour.domain.Role;
import com.squarecode.yogyatour.domain.User;

import java.util.List;

/**
 * Author   : @dynastymasra
 * User     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */
public interface UserRepositoryCustom {

    public String encodePassword(String plain, String salt);

    public User create(String username, String plainPassword);

    public User create(String username, String plainPassword, List<Role> roles);
}
