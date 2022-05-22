package bo.edu.ucb.sis.BackendProy.service;

import bo.edu.ucb.sis.BackendProy.dao.IUserDao;
import bo.edu.ucb.sis.BackendProy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User checkUserLogin(User user) {
        return (User) userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDao.findByUser_idSQL(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(User user) {
        return userDao.findByEmail(user.getEmail());
    }
}
