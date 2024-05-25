package cn.regexp.coding.trainee.anno;

import io.swagger.annotations.ApiModelProperty;

import java.lang.annotation.*;

/**
 * @author Regexpei
 * @date 2024/5/19 15:36
 * @description API 操作日志注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface ApiOprLogAnno {

    @ApiModelProperty(value = "接口类型")
    String apiType() default "";

    @ApiModelProperty(value = "接口说明")
    String apiDetail() default "";

    @ApiModelProperty(value = "是否保存请求参数")
    boolean isSaveRequest() default false;

    @ApiModelProperty(value = "是否保存响应结果")
    boolean isSaveResponse() default false;

}
