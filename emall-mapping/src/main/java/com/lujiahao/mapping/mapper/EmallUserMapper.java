package com.lujiahao.mapping.mapper;

import com.lujiahao.mapping.pojo.EmallUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmallUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmallUser record);

    int insertSelective(EmallUser record);

    EmallUser selectByPrimaryKey(Integer id);

    List<EmallUser> selectAll();

    int updateByPrimaryKey(EmallUser record);

    int updateByPrimaryKeySelective(EmallUser record);

    int checkUsername(String username);

    int checkPhone(String phone);

    int checkEmail(String email);

    EmallUser userLogin(@Param("username") String username, @Param("password") String password);

    String selectQuestionByUsername(String username);

    int validPwdAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePwdByUsername(@Param("username") String username, @Param("password") String passwordNew);
}