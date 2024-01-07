package io.keede.travely.core.web.security.dto;


/**
* @author keede
* Created on 2024/01/06
*/
public record LoginUser(
    String email,
    String accessToken

) {
}
