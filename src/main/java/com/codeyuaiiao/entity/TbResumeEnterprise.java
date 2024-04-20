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

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TbResumeEnterprise对象", description="")
public class TbResumeEnterprise extends Model<TbResumeEnterprise>  {
    private static final long serialVersionUID = 1L;

    //@TableId中的value指明实体类属性映射到数据库的id字段
    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "企业表id")
    @TableField(value = "enterprise_id")
    private Integer enterpriseID;

    @ApiModelProperty(value = "简历id")
    @TableField(value = "resume_id")
    private Integer resumeID;

    @ApiModelProperty(value = "招聘职位")
    @TableField("job")
    private String job;



    //实体类的主键值
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
