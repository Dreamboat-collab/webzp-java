package com.codeyuaiiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbResume对象", description="")
public class TbResume extends Model<TbResume> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "求职者用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "求职行业")
    @TableField("industry")
    private String industry;

    @ApiModelProperty(value = "工作经验")
    @TableField("work_experience")
    private String workExperience;

    @ApiModelProperty(value = "工作地点")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "期望薪资")
    @TableField("salary")
    private String salary;

    @ApiModelProperty(value = "意向职位")
    @TableField("intention_job")
    private String intentionJob;

    @ApiModelProperty(value = "求职状态")
    @TableField("job_status")
    private String jobStatus;

    @ApiModelProperty(value = "个人介绍")
    @TableField("personal_introduction")
    private String personalIntroduction;

    @ApiModelProperty(value = "发布时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "有效时间")
    @TableField("valid_time")
    private String validTime;

    @ApiModelProperty(value = "简历照片")
    @TableField("image")
    private String image;

    @ApiModelProperty(value = "求职者姓名")
    @TableField("name")
    private String name;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
