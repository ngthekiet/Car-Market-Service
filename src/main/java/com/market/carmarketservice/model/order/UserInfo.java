package com.market.carmarketservice.model.order;

import com.market.carmarketservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private Integer birthyear;
    private String username;

    public UserInfo userMapToUserDTO(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setFirstname(user.getFirstname());
        userInfo.setLastname(user.getLastname());
        userInfo.setEmail(user.getEmail());
        userInfo.setAddress(user.getAddress());
        userInfo.setPhone(user.getPhone());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setBirthyear(user.getBirthyear());
        userInfo.setUsername(user.getUsername());
        return userInfo;
    }
}
