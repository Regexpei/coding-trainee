package cn.regexp.coding.trainee.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author Regexpei
 * @date 2024/5/19 15:56
 * @description API操作日志实体
 */
@Setter
@Getter
public class ApiOprLog {

    @ApiModelProperty(name = "主键")
    private String id;

    @ApiModelProperty(name = "源IP")
    private String sourceIp;

    @ApiModelProperty(name = "用户名")
    private String username;

    @ApiModelProperty(name = "方法")
    private String method;

    @ApiModelProperty(name = "请求参数")
    private String reqParams;

    @ApiModelProperty(name = "响应结果")
    private String resResult;

    @ApiModelProperty(name = "异常信息")
    private String exMessage;

    @ApiModelProperty(name = "异常详细")
    private String exJson;

    @ApiModelProperty(name = "接口模块")
    private String apiModule;

    @ApiModelProperty(name = "接口类型")
    private String apiType;

    @ApiModelProperty(name = "接口说明")
    private String apiDetail;

    @ApiModelProperty(name = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "更新时间")
    private Date updateTime;
}
