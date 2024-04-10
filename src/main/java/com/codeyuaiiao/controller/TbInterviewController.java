package com.codeyuaiiao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.TbInterview;
import com.codeyuaiiao.service.TbEnterpriseService;
import com.codeyuaiiao.service.TbInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  面试邀约前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/interview")
public class TbInterviewController {
    @Autowired
    private ResultGenerator generator;
    @Autowired
    private TbEnterpriseService tbEnterpriseService;
    @Autowired
    private TbInterviewService tbInterviewService;


    //面试信息
    @PostMapping("/Info")
    public RestResult interviewInfo(HttpServletRequest request) {
        //求职者用户名
        String username = request.getParameter("username");
        QueryWrapper<TbInterview> wrapper1 = new QueryWrapper<>();
        //name LIKE '%penghu%'
        wrapper1.like("user_name",username);
        List<TbInterview> result= tbInterviewService.list(wrapper1);
        System.out.println(result);
        if (!result.isEmpty()) {
            return generator.getSuccessResult(result);
        } else {
            return generator.getFailResult("面试信息获取失败!");
        }
    }
}
