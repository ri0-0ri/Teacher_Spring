package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.UserDTO;

@Mapper
public interface UserMapper {
	//mapper에서 메소드 호출하면 mapper의 xml로 감
	// > user.xml로 감
	//왜냐면
	//namespace가 com.example.demo.mapper.UserMapper라서
	//동일 namespace가 있는 쪽으로 감
	String getUsernameByUserid(String userid);
	UserDTO getUserByUserid(String userid);
}
