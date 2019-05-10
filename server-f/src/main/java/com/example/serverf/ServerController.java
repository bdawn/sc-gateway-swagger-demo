package com.example.serverf;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "一个用来测试swagger注解的控制器", tags = {"server-f 用户登录接口"})
public class ServerController {

    @ApiOperation(value = "测试接口", notes = "测试描述")
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户编号",required = true, dataType = "int")
    @GetMapping(value = "/getUserName", produces = {"application/json;charset=UTF-8"})
    public String getUserName(Integer userId){
        return userId + "-server-f";
    }
}
