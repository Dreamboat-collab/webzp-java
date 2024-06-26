package com.codeyuaiiao.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codeyuaiiao.config.RestResult;
import com.codeyuaiiao.config.ResultGenerator;
import com.codeyuaiiao.entity.TbRecruitment;
import com.codeyuaiiao.service.TbRecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  企业招聘前端控制器
 * </p>
 */
@RestController
@RequestMapping("/api/recruitment")
public class TbRecruitmentController {
    //响应的bean对象
    @Autowired
    private ResultGenerator generator;
    @Autowired
    private TbRecruitmentService tbRecruitmentService;

    //查询企业招聘的所有信息
    @PostMapping("/allInfo")
    public RestResult recruitmentAllInfo(HttpServletRequest request){
        String name = request.getParameter("name");
        //System.out.println(name);
        if(name != ""){
            return this.likeAllInfo(request, name);
        }
        //用户未搜索时，招聘信息表的内容
        List<TbRecruitment> list = tbRecruitmentService.list();
        if(list!=null){
            return generator.getSuccessResult(list);
        }else{
            return generator.getFailResult("没有数据");
        }
    }

//    招聘信息注册
    @PostMapping("/register")
    public RestResult register(HttpServletRequest request) {
        TbRecruitment recruitment = new TbRecruitment();
        recruitment.setUsername(request.getParameter("username"));
        recruitment.setName(request.getParameter("name"));
        recruitment.setIndustry(request.getParameter("industry"));
        recruitment.setJob(request.getParameter("job"));
        recruitment.setSalary(request.getParameter("salary"));
        recruitment.setAddress(request.getParameter("address"));
        recruitment.setValidTime(request.getParameter("validTime"));
        recruitment.setEducation(request.getParameter("education"));
        recruitment.setExperience(request.getParameter("experience"));
        recruitment.setNumber(request.getParameter("number"));
        recruitment.setDescription(request.getParameter("description"));
        //实际上会调用 BaseMapper 中的 insert 方法，这个方法会将业务对象保存到数据库中。这是因为 MyBatis Plus 提供了默认的实现，使得服务层方法调用后，底层会自动执行对应的数据库操作
        boolean save = tbRecruitmentService.save(recruitment);
        if (save) {
            return generator.getSuccessResult("注册成功");
        } else {
            return generator.getFailResult("注册失败!!");
        }
    }

    //模糊查询得到一组数据
    @PostMapping("/byselfAllInfo")
    public RestResult likeAllInfo(HttpServletRequest request, @RequestParam("name") String name){
        System.out.println(name);
        //查询条件的包装器对象 QueryWrapper，使用实体类TbRecruitment中定义的属性名来指定查询条件
        QueryWrapper<TbRecruitment> wrapper = new QueryWrapper<>();
        //左边的参数是数据库表中的字段名，右边的参数是用于匹配的值，这里字段名为企业名
        wrapper.likeRight("name",name);
        List<TbRecruitment> list = tbRecruitmentService.list(wrapper);
        System.out.println(list);
        if(list!=null){
            System.out.println("我进来了1");
            return generator.getSuccessResult(list);
        }else{
            System.out.println("我进来了2");
            return generator.getFailResult("没有数据");
        }
    }

    //得到一条用户数据
    @PostMapping("/getOneInfo")
    public RestResult getUserOneInfo(HttpServletRequest request) {
        String username = request.getParameter("username");
        QueryWrapper<TbRecruitment> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        TbRecruitment one = tbRecruitmentService.getOne(wrapper);
        if (one != null) {
            return generator.getSuccessResult(one);
        } else {
            return generator.getFailResult("没有数据");
        }
    }

    //更新一条用户数据
    @PutMapping("/editOneInfo")
    public RestResult editUserOneInfo(TbRecruitment recruitment){
        boolean update = tbRecruitmentService.updateById(recruitment);
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
        QueryWrapper<TbRecruitment> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean remove = tbRecruitmentService.remove(wrapper);
        if(remove){
            return generator.getSuccessResult("删除成功");
        }else{
            return generator.getFailResult("删除失败");
        }
    }
}
