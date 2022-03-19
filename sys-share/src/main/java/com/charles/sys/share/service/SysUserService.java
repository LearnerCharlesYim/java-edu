package com.charles.sys.share.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.charles.common.domain.ResultCode;
import com.charles.common.execption.BizException;
import com.charles.common.utils.IpUtil;
import com.charles.sys.share.domain.dto.LoginParam;
import com.charles.sys.share.domain.dto.LoginResult;
import com.charles.sys.share.domain.dto.UserParam;
import com.charles.sys.share.domain.pojo.Department;
import com.charles.sys.share.domain.pojo.SysUser;
import com.charles.sys.share.repository.DeptRepository;
import com.charles.sys.share.repository.SysUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SysUserService {

    private final SysUserRepository sysUserRepository;
    private final DeptRepository deptRepository;

    public LoginResult login(LoginParam loginParam, HttpServletRequest request) {
        SysUser loginUser = sysUserRepository.findByUsername(loginParam.getUsername());
        if (loginUser == null) throw new BizException(ResultCode.USER_CREDENTIALS_ERROR);
        if (!BCrypt.checkpw(loginParam.getPassword(), loginUser.getPassword()))
            throw new BizException(ResultCode.USER_CREDENTIALS_ERROR);
        StpUtil.login(loginUser.getId());
        loginUser.setLastLogin(new Date());
        loginUser.setIp(IpUtil.getIpAddr(request));

        LoginResult loginResult = new LoginResult();
        loginResult.setTokenHead(StpUtil.getTokenName());
        loginResult.setToken(StpUtil.getTokenValue());

        LoginResult.User user = new LoginResult.User();
        BeanUtils.copyProperties(loginUser, user);

        loginResult.setUser(user);
        sysUserRepository.save(loginUser);

        return loginResult;
    }

    public void add(UserParam userParam) {
        SysUser user = sysUserRepository.findByUsername(userParam.getUsername());
        if (user != null) throw new BizException(ResultCode.PARAM_TYPE_ERROR);
        user = new SysUser();
        String newPassword = BCrypt.hashpw(userParam.getPassword());
        BeanUtils.copyProperties(userParam, user);
        user.setPassword(newPassword);

        Integer deptId = userParam.getDeptId();
        if (deptId != null) {
            Optional<Department> opt = deptRepository.findById(deptId);
            if (opt.isPresent()) user.setDept(opt.get());
        }
        sysUserRepository.save(user);
    }
}
