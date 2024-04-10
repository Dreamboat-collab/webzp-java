package com.codeyuaiiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codeyuaiiao.entity.TbResumeEnterprise;
import com.codeyuaiiao.mapper.TbResumeEnterpriseMapper;
import com.codeyuaiiao.service.TbResumeEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbResumeEnterpriseServiceImpl extends ServiceImpl<TbResumeEnterpriseMapper, TbResumeEnterprise> implements TbResumeEnterpriseService {
    @Autowired
    private TbResumeEnterpriseMapper tbResumeEnterpriseMapper;
    @Override
    public List<TbResumeEnterprise> list1(Integer enterprise_id){
        List<TbResumeEnterprise> tbResumeEnterprises = tbResumeEnterpriseMapper.list1(enterprise_id);
        return tbResumeEnterprises;
    }
}
