package com.den.we.transform;

import com.den.we.CommonEnum;
import com.den.we.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 已验证用户信息
 * @author fatKarin
 * @date 2019/10/25 11:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthidUserInfo implements Serializable {

    //id
    private Long id;

    //用户账号
    private String userName;

    //昵称
    private String nickName;

    //邮箱
    private String email;

    //手机号
    private String mobilePhone;

    /**
     * user 转换
     * @param user user
     * @return
     */
    public static AuthidUserInfo build(User user) {
        return AuthidUserInfo.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .mobilePhone(user.getMobilePhone())
                .build();
    }

}
