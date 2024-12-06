package com.koreait.store.mapper;

import com.koreait.store.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDTO selectUserById(String id);
    void insertUser(UserDTO user);
}
