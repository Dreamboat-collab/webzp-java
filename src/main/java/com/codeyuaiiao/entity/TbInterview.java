package com.codeyuaiiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbInterview对象", description="")
@TableName(value = "tb_interview")
public class TbInterview extends Model<TbInterview> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业名")
    @TableField(value = "enterprises_name")
    private String enterprisesName;

    @ApiModelProperty(value = "用户名")
    @TableField(value = "user_name")
    private String userName;

    @ApiModelProperty(value = "招聘职位")
    @TableField("job")
    private String job;

    @ApiModelProperty(value = "面试结果")
    @TableField(value = "result")
    private String result;

    @ApiModelProperty(value = "求职者姓名")
    @TableField("name")
    private String name;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}
