package top.jarry.personal.domain.repository;

import org.springframework.stereotype.Repository;
import top.jarry.personal.domain.model.entity.User;

/**
 * @Description 用户仓储
 * @Date 2023/8/6 18:05
 * @Author king
 */
@Repository
public interface UserRepository {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户编码查找用户
     * @param userCode
     * @return
     */
    User findByUserCode(String userCode);

    /**
     * 保存用户
     * @param user
     */
    void save(User user);
}
