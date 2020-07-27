package com.chatapp.backend.repository;

import com.chatapp.backend.exceptions.EtAuthException;
import com.chatapp.backend.model.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    private static final String SQL_INSERT = "INSERT INTO USER(user_name ,email, password , token , is_verified , user_type) VALUES(?,?,?,?,?,?)";

    private static final String SQL_COUNT_BY_EMAIL = "SELECT COUNT(*) FROM USER WHERE EMAIL = ?";

    private static final String SQL_FIND_BY_USER_ID = "SELECT * FROM USER WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_EMAIL = "SELECT user_id,username , password , email , token , is_verified FROM USER WHERE EMAIL = ?";

    private static final String SQL_VERIFY_EMAIL = "UPDATE USER SET is_verified = true WHERE email = ?";

    private static final String SQL_FORGOT_PASSWORD = "UPDATE USER SET token = ? WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public void create(String username, String email, String password) throws EtAuthException {
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try{
            System.out.println(9000);
            jdbcTemplate.update(SQL_INSERT,username,email, hashedPass,null,false,"user");
            return;
        }catch(Exception e){
            throw new EtAuthException("invalid details");
        }
    }

    public User findByEmailAndPassword(String email, String password) throws EtAuthException {
        try{
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email} , userRowMapper);
            if(!BCrypt.checkpw(password, user.getPassword())){
                throw new EtAuthException("invalid email/password");
            }else{
                if(user.getIsVerified()){
                    return user;
                }else{
                    throw new EtAuthException("email not verified");
                }    
            }
        }catch(EmptyResultDataAccessException e){
            throw new EtAuthException("invalid email/password");
        }
    }

    public Integer getCountByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_COUNT_BY_EMAIL, new Object[]{email} , Integer.class);
    }

    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_USER_ID, new Object[]{userId} , userRowMapper);
    }

    public void verifyEmail(String email) throws EtAuthException {
        jdbcTemplate.update(SQL_VERIFY_EMAIL,email);
    }

    public void forgotPassword(String email , String token) throws EtAuthException {
        jdbcTemplate.update(SQL_FORGOT_PASSWORD,token,email);

    }

    private RowMapper<User> userRowMapper = ((rs , rowNum) -> {
        return new User(
            rs.getInt("user_id"),
            rs.getString("userName"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("userType"),
            rs.getString("token"),
            rs.getBoolean("is_verified")
            );
    });
}