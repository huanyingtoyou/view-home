package com.lihy.view.user.service.impl;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.entity.User;
import com.lihy.view.common.util.GuidUtil;
import com.lihy.view.common.util.PasswordEncryptor;
import com.lihy.view.common.util.ResponseResult;
import com.lihy.view.user.mapper.UserMapper;
import com.lihy.view.user.service.SysBillRuleService;
import com.lihy.view.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @author lihy
 * @date 2018/04/17
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SysBillRuleService sysBillRuleService;

    /**
     * 根据用户userId获取用户信息
     * @param userId
     * @return
     */
    @Override
    public ResponseResult<User> getUserInfoByUserId(String userId){
        LOGGER.info("-----------------根据用户名获取用户信息start-----------------");
        ResponseResult<User> responseResult = new ResponseResult<>();
        //判断用户userId是否为空
        if (StringUtils.isBlank(userId)) {
            responseResult.setResponseCode(SystemConstant.REQ_PARAMS_ERR.getCode());
            responseResult.setResponseMsg(SystemConstant.REQ_PARAMS_ERR.getName());
            return responseResult;
        }
        //根据用户userId查询用户
        User user = userMapper.getUserInfoByUserId(userId);
        if (null == user) {
            responseResult.setResponseCode(SystemConstant.USER_IS_NULL.getCode());
            responseResult.setResponseMsg(SystemConstant.USER_IS_NULL.getName());
            return responseResult;
        }
        //new ResponseEntity<User>(user, HttpStatus.OK);
        responseResult.setData(user);
        responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
        responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        return responseResult;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Transactional
    @Override
    public ResponseResult<Void> doRegister(User user) {
        LOGGER.info("-----------------用户注册start-----------------");
        ResponseResult<Void> responseResult = new ResponseResult<>();
        String password = user.getPassword();
        String salt = UUID.randomUUID().toString();
        password = PasswordEncryptor.encryptPassword(password, salt);
        ResponseResult<String> result = sysBillRuleService.doBillRuleByRulesCode("USER_ID");
        if (null == result || !StringUtils.isNotBlank(result.getData())) {
            //throw new BusinessException(SystemConstant.BILL_RULE_ERR.getCode(), SystemConstant.BILL_RULE_ERR.getName());
        }
        String userId = result.getData();
        user.setUserId(userId);
        user.setPassword(password);
        user.setSalt(salt);
        int isInsert = userMapper.doRegister(user);
        if (isInsert > 0) {
            responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
            responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        } else {
            responseResult.setResponseCode(SystemConstant.ERROR.getCode());
            responseResult.setResponseMsg(SystemConstant.ERROR.getName());
        }
        LOGGER.info("-----------------用户注册end-----------------");
        return responseResult;
    }

    /**
     * 用户登录
     * @param user
     * username 用户名
     * @return
     */
    @Override
    public ResponseResult<Void> doLogin(User user) {
        LOGGER.info("-----------------用户登录start-----------------");
        ResponseResult<Void> responseResult = new ResponseResult<>();
        String password = user.getPassword();
        User user1 = userMapper.getUserInfoByUsername(user);
        if (null == user1) {
            //用户不存在，返回用户不存在代码
            responseResult.setResponseCode(SystemConstant.USER_IS_NULL.getCode());
            responseResult.setResponseCode("用户名不正确");
        } else {
            String salt = user1.getSalt();
            String newEncryptPassword = PasswordEncryptor.encryptPassword(password, salt);
            String encryptPassword = user1.getPassword();
            if (encryptPassword.equals(newEncryptPassword)) {
                //登录成功
                responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
                responseResult.setResponseMsg("登录成功");
            } else {
                //用户密码错误，返回用户密码错误代码
                responseResult.setResponseCode(SystemConstant.PASSWORD_IS_ERROR.getCode());
                responseResult.setResponseCode(SystemConstant.PASSWORD_IS_ERROR.getName());
            }
        }
        LOGGER.info("-----------------用户登录end-----------------");
        return responseResult;
    }
}
