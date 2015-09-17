package com.zhuc.my.httpclient.utils;

import org.springframework.web.bind.annotation.*;

/**
 * restful webservice服务端
 * 客户端调用可使用httpclient  参照HttpProxy4.java中的restPost方法
 * 亦可在jsp中使用ajax post调用  参照restful.html
 * Created by ZHUC on 2015/9/17.
 */
@RestController
public class RestWebservice {

    // headers = "accept=application/json" 可省略
    @RequestMapping(value = "/receive", method = RequestMethod.POST, headers = "accept=application/json")
    @ResponseBody
    public Result receive(@RequestBody String content){ //需要加上@RequestBody即可接收rest post数据
        System.out.println(content); //请求体数据

        Result r = new Result();
        return r;
    }
}
