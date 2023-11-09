package com.amazigh.hettal.springusers.dtomapper;

import com.amazigh.hettal.springusers.domain.User;
import com.amazigh.hettal.springusers.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDTOMapper {
   public static UserDTO fromUser(User user) {
       UserDTO userDTO = new UserDTO();
       BeanUtils.copyProperties(user, userDTO);
       return userDTO;
   }

   public static User fromUserDTO(UserDTO userDTO) {
       User user = new User();
       BeanUtils.copyProperties(userDTO, user);
       return user;
   }
}
