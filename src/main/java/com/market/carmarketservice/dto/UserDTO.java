package com.market.carmarketservice.dto;

import com.market.carmarketservice.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private Integer birthyear;
    private String username;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.avatar = user.getAvatar();
        this.birthyear = user.getBirthyear();
        this.username = user.getUsername();
    }
}
