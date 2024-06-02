package com.ecommerce.order_managment.domain.mapper;
import com.ecommerce.order_managment.domain.dto.UsersRequestDto;
import com.ecommerce.order_managment.domain.dto.UsersResponseDto;
import com.ecommerce.order_managment.domain.model.Users;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsersMapper {

    public static Users toModel(UsersRequestDto dto) {
        return new Users(
                dto.getName(),
                dto.getLast_name(),
                dto.getType(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getStatus()
        );
    }

    public static Users toModel(Integer id, UsersRequestDto dto) {
        return new Users (
                id,
                dto.getName(),
                dto.getLast_name(),
                dto.getType(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getStatus()
        );
    }

    public static UsersResponseDto toDto(Users model) {
        return new UsersResponseDto(
                model.getId(),
                model.getName(),
                model.getLast_name(),
                model.getType(),
                model.getEmail(),
                model.getPassword(),
                model.getStatus()
        );
    }
}
