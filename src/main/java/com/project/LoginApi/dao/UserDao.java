package com.project.LoginApi.dao;

import com.project.LoginApi.dto.UserForm;
import com.project.LoginApi.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
public class UserDao {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<User> getAllUsers() {
        Query query = entityManager.createNativeQuery("SELECT * FROM user");
        return query.getResultList();
    }

    @Transactional
    public User findUser(String username, String email) {
        StringBuilder sql = new StringBuilder();
        User user = null;
        try {
            sql.append("SELECT * FROM user WHERE ");

            if (!username.equals(null)) {
                sql.append("username = :username");
            }
            if (!email.equals(null)) {
                sql.append("email = :email");
            }
            Query query = entityManager.createNativeQuery(sql.toString());
            if (!username.equals(null)) {
                query.setParameter("username", username);
            }
            if (!email.equals(null)) {
                query.setParameter("email", email);
            }
            user = (User) query.getSingleResult();
        } catch (Exception e) {
            log.error("findUser Exception: ", e);
        }
        return user;
    }

    @Transactional
    public void saveUser(UserForm userForm) {
        try {
            User user = new User(userForm.getEmail(), passwordEncoder.encode(userForm.getPassword()), userForm.getPhone(), userForm.getFirstname(), userForm.getLastname(), 1,  userForm.getAddress());
            entityManager.persist(user);
        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry")) {
                log.error("username is available");
            }
        }
    }
}
