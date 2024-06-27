package thuctap.task4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import thuctap.task4.model.User;
import thuctap.task4.repo.DAO;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private DAO dao;

    public void addNewUser(User user) throws Exception {
        dao.insertUser(user);

    }
    public void updateUser(User user,int id) throws Exception {
        dao.updateUser(id, user);
    }
    public void deleteUser(int id) throws Exception {
        dao.deleteUser(id);
    }

    public List<User> searchByName(String name) {
        List<User> users = dao.searchByName(name);
        return users;
    }
        public List<User> searchByAddr(String addr){
            List<User> users=dao.searchByAddress(addr);
            return users;
    }

    public User searchById(int id){
        User user=dao.searchById(id);
        return user;
    }

    public List<User> sx(){
        return dao.arrangeByName();
    }


}
