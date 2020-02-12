package com.den.we.Vo;

import com.den.we.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 头像地址
     */
    private String headUrl;

    /**
     * 国家
     */
    private String country;

    /**
     * 城市
     */
    private String city;

    public static UserInfoVo buildByUser(User user) {
        return UserInfoVo.builder()
                .city(user.getCity())
                .country(user.getCounty())
                .userId(user.getId())
                .headUrl(user.getPicture())
                .nickName(user.getNickName())
                .userName(user.getUserName())
                .mobilePhone(user.getMobilePhone())
                .build();
    }
}
