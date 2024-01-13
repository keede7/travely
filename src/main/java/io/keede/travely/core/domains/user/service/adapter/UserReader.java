package io.keede.travely.core.domains.user.service.adapter;

import io.keede.travely.core.domains.user.entity.User;
import io.keede.travely.core.domains.user.entity.UserRepository;
import io.keede.travely.core.exception.service.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author keede
* Created on 2024/01/13
*/
@Service
@Transactional(readOnly = true)
public class UserReader {

    private final UserRepository userRepository;

    public UserReader(
            final UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User findById(Long userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(BusinessException::new);
    }
}
