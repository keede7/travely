package io.keede.travely.core.domains.user.api;


import io.keede.travely.core.web.security.dto.LoginUser;
import io.keede.travely.core.web.security.dto.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author keede
* Created on 2024/01/07
*/
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @GetMapping("")
    public void status(@Session LoginUser loginUser) {
        System.out.println("loginUser = " + loginUser);
    }

}
