package com.codeyuaiiao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.codeyuaiiao.entity.TbResumeEnterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
/**
 * <p>
 *  Mapper 接口
 * </p>
 */
public interface TbResumeEnterpriseMapper extends BaseMapper<TbResumeEnterprise> {
    @Select("select * FROM tb_resume_enterprise WHERE enterprise_id = ${enterprise_id}")
    List<TbResumeEnterprise> list1(Integer enterprise_id);

}
