package thuctap.task4.repo;

import thuctap.task4.model.User;

import java.util.List;

public interface DAO {
    void insertUser(User user) throws Exception;
    void updateUser(int id,User user) throws  Exception;
    void deleteUser(int id) throws Exception;
    List<User> searchByName(String name);
    User searchById(int id);
    List<User> searchByAddress(String address);
    List<User> arrangeByName();
}
