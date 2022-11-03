package com.warehouse.auth.infrastructure.adapter.mapper;

import com.warehouse.auth.domain.model.RegisterRequest;
import com.warehouse.auth.infrastructure.adapter.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RequestToEntityMapper {


    UserEntity map(RegisterRequest registerRequest);

}
