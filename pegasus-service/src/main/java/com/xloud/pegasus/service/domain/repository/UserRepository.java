package com.xloud.pegasus.service.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.xloud.pegasus.service.domain.repository.model.User;

@Mapper
public interface UserRepository {

	List<User> findAll();

	User findById(@Param("id") Long id);

	Integer insert(@Param("user") User user);

	Integer update(@Param("user") User user);

	Integer delete(@Param("id") Long id);

}
