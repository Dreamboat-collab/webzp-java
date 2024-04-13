package com.codeyuaiiao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.*;
import com.codeyuaiiao.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  简历前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/resume")
public class TbResumeController {
    @Autowired
    private ResultGenerator generator;
    @Autowired
    private TbResumeService tbResumeService;
    @Autowired
    private TbEnterpriseService tbEnterpriseService;
    @Autowired
    private TbResumeEnterpriseService tbResumeEnterpriseService;
    @Autowired
    private TbInterviewService tbInterviewService;
    @Autowired
    private TbUserService tbUserService;



    //查询企业收到的简历信息
    @PostMapping("/allInfo")
    public RestResult resumeAllInfo(HttpServletRequest request){
        //输入框中输入的求职者用户名
        String username = request.getParameter("username");
        if (username != "" ) {
            return this.likeOneInfo(request,username);
        }

        //获取企业的用户名,展示企业收到的所有简历信息
        String enterusername = request.getParameter("enterusername");
        System.out.println(enterusername);
        //获取企业id
        QueryWrapper<TbEnterprise> wrapper1 = new QueryWrapper<>();
        wrapper1.likeRight("username",enterusername);
        TbEnterprise enterprise = tbEnterpriseService.getOne(wrapper1);
        Integer enterprise_id = enterprise.getId();
        List<TbResumeEnterprise> tbResumeEnterprises = tbResumeEnterpriseService.list1(enterprise_id);
        //获取简历id
        List<Integer> resumeid = new ArrayList<>();
        for(TbResumeEnterprise t:tbResumeEnterprises){
            Integer id = t.getResumeID();
            resumeid.add(id);
        }
        QueryWrapper<TbResume> wrapper2 = new QueryWrapper<>();
        wrapper2.in("id",resumeid);
        List<TbResume> tbResumes = tbResumeService.list(wrapper2);
        if(tbResumes!=null){
            return generator.getSuccessResult(tbResumes);
        }else{
            return generator.getFailResult("没有数据");
        }
    }

    //新建一份简历
    @PostMapping("/register")
    public RestResult register(HttpServletRequest request) {
        TbResume resume = new TbResume();
        //求职者姓名，需要转化为用户名
        String name = request.getParameter("username");
        QueryWrapper<TbUser> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("name", name);
        String username = tbUserService.getOne(wrapper1).getUsername();
        System.out.println(username);

        resume.setUsername(username);
        resume.setIndustry(request.getParameter("industry"));
        resume.setWorkExperience(request.getParameter("workExperience"));
        resume.setAddress(request.getParameter("address"));
        resume.setSalary(request.getParameter("salary"));
        resume.setIntentionJob(request.getParameter("intentionJob"));
        resume.setJobStatus(request.getParameter("JobStatus"));
        resume.setPersonalIntroduction(request.getParameter("personalIntroduction"));
        //此处逻辑：用户只能创建一个简历
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<TbResume> list = tbResumeService.list(wrapper);
        if (!list.isEmpty()) {
            return generator.getFailResult("用户名重复,请重新输入");
        }
        boolean save = tbResumeService.save(resume);
        if (save) {
            return generator.getSuccessResult("注册成功");
        } else {
            return generator.getFailResult("注册失败!!");
        }
    }

    //模糊查询得到一条信息
    @PostMapping("/likeOneInfo")
    public RestResult likeOneInfo(HttpServletRequest request,@RequestParam("username")String username){
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username",username);
        List<TbResume> list = tbResumeService.list(wrapper);
        if(list != null){
            System.out.println("Yes");
            return generator.getSuccessResult(list);
        }else{
            System.out.println("No");
            return generator.getFailResult("没有数据");
        }
    }

    //求职者查看简历信息
    @PostMapping("/getOneInfo")
    public RestResult getUserOneInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        TbResume one = tbResumeService.getOne(wrapper);
        if (one != null) {
            return generator.getSuccessResult(one);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //求职者更新一条简历
    @PutMapping("/editOneInfo")
    public RestResult editUserOneInfo(TbResume resume){
        boolean update = tbResumeService.updateById(resume);
        System.out.println(update);
        if (update) {
            return generator.getSuccessResult();
        } else {
            return generator.getFailResult("更新失败");
        }
    }

    //删除一条数据
    @PostMapping("/deleteInfo")
    public RestResult deleteInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        System.out.println(id);
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean remove = tbResumeService.remove(wrapper);
        if(remove){
            return generator.getSuccessResult("删除成功");
        }else{
            return generator.getFailResult("删除失败");
        }
    }

    //用户投递简历
    @PostMapping("/InsertInfo")
    @Transactional
    public RestResult insertInfo(HttpServletRequest request){
        String username = request.getParameter("username");
        String companyName = request.getParameter("companyName");
        String jobName = request.getParameter("jobName");
        System.out.println(username);
        System.out.println(companyName);
        System.out.println(jobName);
        //wrapper是构建查询结果的对象,此时是以TbResume实体类来构建查询
        //获取简历id
        QueryWrapper<TbResume> wrapper = new QueryWrapper<>();
        wrapper.likeRight("username",username);
        TbResume resume = tbResumeService.getOne(wrapper);
        Integer resume_id = resume.getId();

        //获取企业id
        QueryWrapper<TbEnterprise> wrapper1 = new QueryWrapper<>();
        wrapper1.likeRight("name",companyName);
        TbEnterprise enterprise = tbEnterpriseService.getOne(wrapper1);
        Integer enterprise_id = enterprise.getId();
        System.out.println(enterprise_id);

        //将简历id与企业id插入关系表中
        TbResumeEnterprise relation = new TbResumeEnterprise();
        relation.setEnterpriseID(enterprise_id);
        relation.setResumeID(resume_id);
        relation.setJob(jobName);
        //将数据添加到关系表中
        tbResumeEnterpriseService.save(relation);

        //查询企业关联的简历信息
        List<TbResume> resume_result = search_resume(enterprise_id);
        System.out.println(resume_result);
        return generator.getSuccessResult(resume_result);
    }

    //企业获取简历信息表
    public List<TbResume> search_resume(Integer enterprise_id){
        System.out.println(enterprise_id);
        //查询企业关联的简历信息
        List<TbResumeEnterprise> result = tbResumeEnterpriseService.list1(enterprise_id);
        if (!result.isEmpty()) {
            // 处理查询结果
            System.out.println(result);
        } else {
            // 查询结果为空，处理空结果情况
            System.out.println("查询结果为空");
        }
        //简历id
        List<Integer> id_list = new ArrayList<>();
        for(TbResumeEnterprise t:result){
            Integer resumeid = t.getResumeID();
            System.out.println(resumeid);
            id_list.add(resumeid);
        }
        // 获取简历信息列表
        List<TbResume> resumeList = new ArrayList<>();
        if (!id_list.isEmpty()) {
            for (Object obj : tbResumeService.listByIds(id_list)) {
                if (obj instanceof TbResume) {
                    resumeList.add((TbResume) obj);
                }
            }
        }
        System.out.println(resumeList);
        return resumeList;
    }

    //发起面试邀约
    @PostMapping("/Interview")
    public RestResult interview(HttpServletRequest request){
        //求职者用户名
        String username = request.getParameter("username");
        System.out.println(username);
        //获取求职者id
        QueryWrapper<TbResume> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("username",username);
        Integer userid = tbResumeService.getOne(wrapper1).getId();
        //企业用户名
        String enterusername = request.getParameter("enterusername");
        System.out.println(enterusername);
        //获取企业id
        QueryWrapper<TbEnterprise> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("username",enterusername);
        Integer enterpriseid = tbEnterpriseService.getOne(wrapper2).getId();
        //查询职位名
        QueryWrapper<TbResumeEnterprise> wrapper3 = new QueryWrapper<>();
        wrapper3.eq("resume_id",userid);
        wrapper3.eq("enterprise_id",enterpriseid);
        String jobName = tbResumeEnterpriseService.getOne(wrapper3).getJob();
        System.out.println(jobName);
        //企业中文名
        QueryWrapper<TbEnterprise> wrapper4 = new QueryWrapper<>();
        wrapper4.eq("username",enterusername);
        String enterusername1 = tbEnterpriseService.getOne(wrapper4).getName();
        //将数据插入到面试数据表
        TbInterview tbInterview = new TbInterview();
        tbInterview.setUserName(username);
        tbInterview.setEnterprisesName(enterusername1);
        tbInterview.setJob(jobName);
        boolean save = tbInterviewService.save(tbInterview);
        if (save) {
            return generator.getSuccessResult("插入成功");
        } else {
            return generator.getFailResult("插入失败!");
        }
    }
}
