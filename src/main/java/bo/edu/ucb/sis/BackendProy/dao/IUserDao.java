package bo.edu.ucb.sis.BackendProy.dao;

import bo.edu.ucb.sis.BackendProy.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User,Long> {
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);

    @Query("select u from User u where u.user_id=?1")
    public User findByUser_idSQL(Long user_id);
}
