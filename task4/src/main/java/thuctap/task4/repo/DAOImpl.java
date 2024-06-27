package thuctap.task4.repo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import thuctap.task4.model.User;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class DAOImpl extends JdbcDaoSupport implements DAO {
    @Autowired
    DataSource dataSource;
    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
    @Override
    public void insertUser(User user) throws Exception {
        if (checkID(user.getId())) {
            throw new Exception("User with id " + user.getId() + " already exists.");
        }
        if (checkFormUser(user)) {
            throw new Exception("Invalid user data.");
        }

        String sql = "INSERT INTO user (id, name, age, address) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, user.getId(), user.getName(), user.getAge(), user.getAddress());
    }

    @Override
    public void updateUser(int id, User user) throws Exception {

        if (checkID(user.getId()) && user.getId() != id) {
            throw new Exception("User with id " + user.getId() + " already exists.");
        }

        if (checkFormUser(user)) {
            throw new Exception("Invalid user data");
        }


        String sql = "UPDATE user SET name = ?, age = ?, address = ? WHERE id = ?";
        getJdbcTemplate().update(sql, user.getName(), user.getAge(), user.getAddress(), id);
        System.out.println("User updated successfully.");
    }

    @Override
    public void deleteUser(int id) throws Exception {
        if(checkID(id)){
            System.out.println("id existed");
            throw new Exception("User existed");
        }
        String sql= "delete from user where id=?";
        getJdbcTemplate().update(sql, id);
    }

    @Override
    public List<User> searchByName(String name) {
        String sql = "SELECT * FROM user WHERE name LIKE ?";

        List<User> users = getJdbcTemplate().query(sql, new Object[]{"%" + name + "%"},
                new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public User searchById(int id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        User user = getJdbcTemplate().queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }


    @Override
    public List<User> searchByAddress(String address) {
        String sql = "SELECT * FROM user WHERE address LIKE ?";

        List<User> users = getJdbcTemplate().query(sql, new Object[]{"%" + address + "%"},
                new BeanPropertyRowMapper<>(User.class));
        return users;
    }

    @Override
    public List<User> arrangeByName() {
        String sql = "SELECT * FROM user order by name desc";
        List<User> users = getJdbcTemplate().query(sql, new Object[]{},
                new BeanPropertyRowMapper<>(User.class));
        return users;
    }
    private boolean checkID(int id) {
        String sql = "SELECT COUNT(*) FROM user WHERE id = ?";
        int count = getJdbcTemplate().queryForObject(sql, Integer.class, id);
        return count > 0;
    }

    private boolean checkFormUser(User user) {
        String name = user.getName();
        int age = user.getAge();
        String address = user.getAddress();
        return name == null || name.isEmpty() || age <= 1 || age >= 100 || address == null || address.isEmpty();
    }
}
