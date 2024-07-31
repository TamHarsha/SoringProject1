package com.example.demo.UserMapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeData;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper instance = Mappers.getMapper(UserMapper.class);
	
	EmployeeData EmployeeBoToEmployee(EmployeeBo employeeBo);
	
	EmployeeBo EmployeeToEmployeeBo(EmployeeData employee);
	
}
