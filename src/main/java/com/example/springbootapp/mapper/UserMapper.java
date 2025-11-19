package com.example.springbootapp.mapper;

import com.example.springbootapp.dto.UserRequest;
import com.example.springbootapp.dto.UserResponse;
import com.example.springbootapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse toResponse(User user);

    User toEntity(UserRequest request);

    void updateEntity(UserRequest request, @MappingTarget User user);
}

