package com.honour.server.web;

import com.honour.server.constant.Constant;
import com.honour.server.dao.UserMapper;
import com.honour.server.entity.pojo.User;
import com.honour.server.entity.vo.BaseResponse;
import com.honour.server.entity.vo.ExOpenIdVO;
import com.honour.server.entity.vo.WeXinUserInfo;
import com.honour.server.entity.vo.WxJscodeResult;
import com.honour.server.utils.IpUtil;
import com.honour.server.utils.JsonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/weixin")
public class WeiXinController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "微信code换取openId", notes = "微信code换取openId")
    @ApiImplicitParam(name = "code", value = "微信info", required = true, dataTypeClass = ExOpenIdVO.class)
    @PostMapping("/exchangeOpenId")
    public Object exchangeOpenId(@RequestBody ExOpenIdVO exOpenIdVO, HttpServletRequest httpServletRequest) {
        WeXinUserInfo weXinUserInfo = exOpenIdVO.getWeXinUserInfo();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("appid", Constant.wx_appid);
        map.add("secret", Constant.wx_appsecret);
        map.add("js_code", exOpenIdVO.getCode());
        map.add("grant_type", "authorization_code");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(Constant.wx_appurl, request, String.class);
        System.out.println(response.getBody());
        WxJscodeResult jscodeResult = JsonUtil.jsonToPojo(response.getBody(), WxJscodeResult.class);
        if (jscodeResult == null) return BaseResponse.error();

        User user = null;
        // User user = userMapper.selectByOpenId(jscodeResult.getOpenid());
        if (user == null) {
            user = new User();
            user.setAccount(jscodeResult.getOpenid());
            user.setPassword(jscodeResult.getOpenid());
            user.setNickName(weXinUserInfo.getNickName());
            user.setRealName(weXinUserInfo.getNickName());
            user.setGender(weXinUserInfo.getGender());
            user.setAvatar(weXinUserInfo.getAvatarUrl());
            user.setOpenId(jscodeResult.getOpenid());
            user.setLoginIp(IpUtil.client(httpServletRequest));
            user.setLoginTime(new Date());
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);
        } else {
            user.setLoginIp(IpUtil.client(httpServletRequest));
            user.setLoginTime(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        }
        return BaseResponse.successOfData(response.getBody());
    }
}
