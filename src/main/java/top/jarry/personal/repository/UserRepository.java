package top.jarry.personal.repository;

import org.springframework.data.repository.CrudRepository;
import top.jarry.personal.entity.User;

/**
 * @description:
 * @author: 天数
 * @since: 2022/10/14 11:35
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * 根据用户名/账号，查询用户信息
     * @param account 用户名/账号
     * @return 用户信息
     */
    User findUserByAccount(String account);

}
