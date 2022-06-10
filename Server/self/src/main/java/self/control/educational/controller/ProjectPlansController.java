package self.control.educational.controller;

import onway.org.cn.comm.utils.id.IDUtils;
import onway.org.cn.comm.utils.str.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.controller.JSONController;
import self.control.educational.comm.UserCode;
import self.control.educational.entity.ProjectPlans;
import self.control.educational.entity.Students;
import self.control.educational.entity.Users;
import self.control.educational.handle.CacheHandle;
import self.control.educational.service.ProjectPlansService;
import self.control.educational.service.StudentsService;
import self.control.educational.service.TeacherWorkService;

/**
 * 系统请求响应控制器
 * 上课安排
 */
@Controller
@RequestMapping("/projectPlans")
public class ProjectPlansController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(ProjectPlansController.class);

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private ProjectPlansService projectPlansService;

    @Autowired
    private CacheHandle cacheHandle;

    @RequestMapping("")
    public String index() {

        return "pages/projectPlans";
    }

    @RequestMapping("/checkTime")
    @ResponseBody
    public String checkTime(String planTime, String workId){

        Log.info("检查指定教室，在上课时间段内是否安排其他课程,授课时间：{}, 工作安排ID：{}");

        Boolean flag = projectPlansService.isAllowPlanInTime(planTime, workId);

        return success(flag);
    }

    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize, String token,
                               String gradeId, String teacherId, String projectId) {

        Users user = cacheHandle.getUserInfoCache(token);

        if(UserCode.USER_TEACHER.equals(user.getType())){

            teacherId = user.getId();
        }else if(UserCode.USER_STUDENT.equals(user.getType())){

            Students student = studentsService.getOne(user.getId());

            if(StringUtils.isNotNullOrEmpty(student.getGradeId())){

                gradeId = student.getGradeId();
            }
        }

        Log.info("分页查找上课安排信息，当前页码：{}，"
                        + "每页数据量：{}, 班级编号：{}, 教师编号：{}, 课程编号：{}", pageIndex,
                pageSize, gradeId, teacherId, projectId);

        Page page = projectPlansService.getPageInfo(pageIndex, pageSize, gradeId, teacherId, projectId);

        return success(page);
    }

    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String id) {

        Log.info("查找指定上课安排信息，ID：{}", id);

        ProjectPlans projectPlans = projectPlansService.getOne(id);

        return success(projectPlans);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInfo(ProjectPlans projectPlans) {

        projectPlans.setId(IDUtils.makeIDByCurrent());

        Log.info("添加上课安排信息，传入参数：{}", projectPlans);

        projectPlansService.add(projectPlans);

        return success();
    }

    @RequestMapping("/upd")
    @ResponseBody
    public String updInfo(ProjectPlans projectPlans) {

        Log.info("修改上课安排信息，传入参数：{}", projectPlans);

        projectPlansService.update(projectPlans);

        return success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delInfo(String id) {

        Log.info("删除上课安排信息, ID:{}", id);

        ProjectPlans projectPlans = projectPlansService.getOne(id);

        projectPlansService.delete(projectPlans);

        return success();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
