package com.example.print_manager_final.mapper;

import com.example.print_manager_final.dto.request.RoleRequest;
import com.example.print_manager_final.dto.request.UserCreateRequest;
import com.example.print_manager_final.dto.request.UserUpdateRequest;
import com.example.print_manager_final.dto.response.UserResponse;
import com.example.print_manager_final.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User toUser(UserCreateRequest request);

//    @Mapping(source = "field", target = "other_field")  nếu muốn ánh xạ từ gì sang gì trong th các field tên khác nhau
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
