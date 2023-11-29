package io.keede.travely.core.domains.user.service;

import io.keede.travely.core.domains.user.entity.UserRepository;
import org.springframework.stereotype.Service;

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

}
