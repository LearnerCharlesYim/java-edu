package com.charles.guli.edu.service;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.common.domain.ResultCode;
import com.charles.common.execption.BizAssert;
import com.charles.common.execption.BizException;
import com.charles.guli.edu.domain.pojo.User;
import com.charles.guli.edu.domain.pojo.UserRole;
import com.charles.guli.edu.domain.vo.LoginParam;
import com.charles.guli.edu.domain.vo.UserVo;
import com.charles.guli.edu.repository.UserRepository;
import com.charles.guli.edu.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public void addUser(UserVo userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        User newUser = userRepository.save(user);
        Integer userId = newUser.getId();

        List<Integer> rolesId = userParam.getRolesId();
        if (rolesId != null) {
            rolesId.forEach(roleId -> {
                UserRole relation = new UserRole();
                relation.setRoleId(roleId);
                relation.setUserId(userId);
                userRoleRepository.save(relation);
            });
        }
    }

    public String login(LoginParam loginParam) {
        User user = userRepository.findByUsername(loginParam.getUsername());
        BizAssert.notNull(user, ResultCode.USER_CREDENTIALS_ERROR);
        BizAssert.equals(loginParam.getPassword(), user.getPassword(), ResultCode.USER_CREDENTIALS_ERROR);
        StpUtil.login(user.getId());
        StpUtil.getSession().set("loginUser", user.getUsername());
        return StpUtil.getTokenValue();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
