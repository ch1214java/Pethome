package cn.xinhang.user.mapper;

import cn.xinhang.user.domain.User;
import cn.xinhang.user.domain.dto.UserDto;

import java.util.List;

public interface UserMapper {
    User findByPhone(String phone);

    void save(UserDto userDto);

    User findByAccount(String username);

    List<User> getAll();
}
