package com.hmtmcse.v3base.model.mapper;

import com.hmtmcse.v3base.model.dtos.UsersDto;
import com.hmtmcse.v3base.model.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    public Users map(UsersDto dto) {
        Users entity = new Users();
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public UsersDto map(Users entity) {
        UsersDto dto = new UsersDto();
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        return dto;
    }

}
