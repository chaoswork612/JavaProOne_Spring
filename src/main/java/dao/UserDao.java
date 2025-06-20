package dao;

import com.zaxxer.hikari.HikariDataSource;
import model.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    private final HikariDataSource dataSource;


    public UserDao(HikariDataSource dataSource) {
        this.dataSource = new HikariDataSource(dataSource);
    }

    public User createUser(User user) {
        String sql = String.format("INSERT INTO spring.users (username) VALUES ('%s') RETURNING *", user.getUsername());
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(rs.getLong("id"), rs.getString("username"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(Long id) {
        String sql = String.format("DELETE FROM spring.users WHERE id = '%s'", id);
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(Long id) {
        String sql = String.format("SELECT * FROM spring.users WHERE id = '%s'", id);
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new User(rs.getLong("id"), rs.getString("username"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM spring.users";
        try(PreparedStatement ps = dataSource.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(rs.getLong("id"), rs.getString("username")));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearUsers() {
        String clearUsersTbl = "TRUNCATE TABLE spring.users";
        String restartPrmKeySeq = "ALTER SEQUENCE spring.users_id_seq RESTART WITH 1";
        try(PreparedStatement clearUsersTblStatement = dataSource.getConnection().prepareStatement(clearUsersTbl);
        PreparedStatement restartPrmKeySeqStatement = dataSource.getConnection().prepareStatement(restartPrmKeySeq)) {
            clearUsersTblStatement.execute();
            restartPrmKeySeqStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
