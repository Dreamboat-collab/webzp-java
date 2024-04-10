package com.codeyuaiiao.service.impl;

import com.codeyuaiiao.entity.TbRecruitment;
import com.codeyuaiiao.mapper.TbRecruitmentMapper;
import com.codeyuaiiao.service.TbRecruitmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


//ServiceImpl第一个参数是Mapper，第二个是实体类（数据库表），使用了TbRecruitmentMapper 类型的 Mapper 接口来进行数据库操作。
@Service
public class TbRecruitmentServiceImpl extends ServiceImpl<TbRecruitmentMapper, TbRecruitment> implements TbRecruitmentService {

}
