package top.jarry.personal.old.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.jarry.personal.infrastructure.persistence.entity.User;
import top.jarry.personal.domain.model.valueObject.UserDetailsBO;
import top.jarry.personal.infrastructure.persistence.UserRepository;

/**
 * @Description 用户服务
 * @Date 2023/3/2 01:12
 * @Author king
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByAccount(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }

        UserDetailsBO userDetailsBO = new UserDetailsBO(user);
        return userDetailsBO;
    }
}
