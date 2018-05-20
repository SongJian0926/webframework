package cn.wycode.web.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Hello 实体
 * Created by wayne on 2017/10/14.
 */
@ApiModel(value = "Hello 消息",description = "Hello接口返回的消息实体")
public class Hello {

    @ApiModelProperty("消息")
    private String message;


    public Hello() {
    }

    public Hello(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
