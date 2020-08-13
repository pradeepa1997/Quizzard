package com.chatapp.backend.repository;

import com.chatapp.exceptions.EtAuthException;
import com.chatapp.backend.model.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    private static final String SQL_INSERT = "INSERT INTO USER(user_name ,email, password , is_verified , user_type) VALUES(?,?,?,?,?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM USER WHERE EMAIL = ?";

    private static final String SQL_FIND_BY_USER_ID = "SELECT * FROM USER WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM USER WHERE EMAIL = ?";

    private static final String SQL_VERIFY_EMAIL = "UPDATE USER SET is_verified = true WHERE email = ?";

    private static final String SQL_RESET_PASSWORD = "UPDATE USER SET password = ? WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void create(String username, String email, String password) throws EtAuthException {
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));
        System.out.println(hashedPass);
        try{
            jdbcTemplate.update(SQL_INSERT,username,email, hashedPass,0,"user");
            return;
        }catch(Exception e){
            System.out.println(e);
            throw new EtAuthException("invalid details");
        }
    }

    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email} , userRowMapper);
            System.out.println(user.toString());

            if(user.toString() != null){
                if(!BCrypt.checkpw(password, user.getPassword())){
                    throw new EtAuthException("invalid email/password");
                }else{
                    System.out.println(user.getIsVerified());
                    // if(user.getIsVerified()==1){
                        return user;
                    // }else{
                        // throw new EtAuthException("email is not verified , please check your email for verification link");
                    // }    
                }
            }else{
                throw new EtAuthException("invalid credentials");
            }
    }

    public User findByEmail(String email) throws EtAuthException {
        User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email} , userRowMapper);
        if(user.toString() != null){
            return user;
        }else{
            throw new EtAuthException("invalid credentials");
        }
    }

    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email} , Integer.class);
    }

    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_USER_ID, new Object[]{userId} , userRowMapper);
    }

    public void verifyEmail(Integer email) throws EtAuthException {
        jdbcTemplate.update(SQL_VERIFY_EMAIL,email);
    }

    public Integer resetPassword(String email , String password) throws EtAuthException {
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try{
            jdbcTemplate.update(SQL_RESET_PASSWORD,hashedPass,email);
            return 1;
        }
        catch(Exception e){
            return 0 ;
        }
    }
    

    private RowMapper<User> userRowMapper = ((rs , rowNum) -> {
        return new User(
            rs.getInt("userID"),
            rs.getString("user_name"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("user_type"),
            rs.getString("token"),
            rs.getInt("is_verified")
            );
    });
}