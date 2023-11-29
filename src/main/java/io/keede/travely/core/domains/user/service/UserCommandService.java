package io.keede.travely.core.domains.user.service;

import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.entity.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kyh
 * Created on 2023/11/29
 */
@Service
public class UserCommandService {

    private final UserRepository userRepository;

    public UserCommandService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        User user1 = new User(
                "test1@naver.com", "1212"
        );
        User user2 = new User(
                "test2@naver.com", "1212"
        );

        userRepository.saveAll(
                List.of(user1, user2)
        );
    }

}
