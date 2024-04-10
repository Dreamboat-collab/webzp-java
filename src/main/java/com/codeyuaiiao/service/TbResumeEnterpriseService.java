package com.codeyuaiiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.codeyuaiiao.entity.TbResumeEnterprise;

import java.util.List;

public interface TbResumeEnterpriseService extends IService<TbResumeEnterprise> {
    List<TbResumeEnterprise> list1(Integer enterprise_id);
}
