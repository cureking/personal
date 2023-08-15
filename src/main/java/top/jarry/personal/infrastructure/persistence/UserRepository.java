package top.jarry.personal.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.jarry.personal.domain.model.entity.UserEntity;

/**
 * @Description 用户仓储
 * @Date 2023/8/6 18:05
 * @Author king
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    UserEntity findByName(String username);

    /**
     * 根据用户编码查找用户
     *
     * @param userCode
     * @return
     */
    UserEntity findByCode(String userCode);

}
