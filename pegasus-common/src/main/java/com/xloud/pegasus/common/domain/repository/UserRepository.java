package com.xloud.pegasus.common.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.xloud.pegasus.common.domain.repository.model.User;

import feign.Param;

@Mapper
public interface UserRepository {

	List<User> findAll();

	User findById(@Param("id") Long id);

	Integer insert(@Param("user") User user);

	Integer update(@Param("user") User user);

	Integer delete(@Param("id") Long id);

}
