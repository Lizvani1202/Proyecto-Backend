package bo.edu.ucb.sis.BackendProy.service;

import bo.edu.ucb.sis.BackendProy.entity.User;

public interface IUserService {
    public void save(User user);
    public User checkUserLogin(User user);
    public User findById(Long id);
    public User findUser(User user);


}
