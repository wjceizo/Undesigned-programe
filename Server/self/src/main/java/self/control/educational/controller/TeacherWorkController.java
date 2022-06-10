package self.control.educational.controller;

import onway.org.cn.comm.utils.date.DateUtils;
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
import self.control.educational.entity.Students;
import self.control.educational.entity.TeacherWork;
import self.control.educational.entity.Users;
import self.control.educational.handle.CacheHandle;
import self.control.educational.service.StudentsService;
import self.control.educational.service.TeacherWorkService;

/**
 * 系统请求响应控制器
 * 授课安排
 */
@Controller
@RequestMapping("/teacherWork")
public class TeacherWorkController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(TeacherWorkController.class);

    @Autowired
    private TeacherWorkService teacherWorkService;

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private CacheHandle cacheHandle;

    @RequestMapping("")
    public String index() {

        return "pages/teacherWork";
    }

    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize, String token,
                               TeacherWork teacherWork) {

        Users user = cacheHandle.getUserInfoCache(token);

        if(UserCode.USER_TEACHER.equals(user.getType())){

            teacherWork.setTeacherId(user.getId());
        }else if(UserCode.USER_STUDENT.equals(user.getType())){

            Students student = studentsService.getOne(user.getId());

            if(StringUtils.isNotNullOrEmpty(student.getGradeId())){

                teacherWork.setGradeId(student.getGradeId());
            }
        }

        Log.info("分页查找授课安排信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, teacherWork);

        Page page = teacherWorkService.getPageInfo(pageIndex, pageSize, teacherWork);

        return success(page);
    }

    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String id) {

        Log.info("查找指定授课安排信息，ID：{}", id);

        TeacherWork teacherWork = teacherWorkService.getOne(id);

        return success(teacherWork);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInfo(TeacherWork teacherWork) {

        teacherWork.setId(IDUtils.makeIDByCurrent());
        teacherWork.setCreateTime(DateUtils.getNowDate());

        Log.info("添加授课安排信息，传入参数：{}", teacherWork);

        teacherWorkService.add(teacherWork);

        return success();
    }

    @RequestMapping("/upd")
    @ResponseBody
    public String updInfo(TeacherWork teacherWork) {

        Log.info("修改授课安排信息，传入参数：{}", teacherWork);

        teacherWorkService.update(teacherWork);

        return success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delInfo(String id) {

        Log.info("删除授课安排信息, ID:{}", id);

        TeacherWork teacherWork = teacherWorkService.getOne(id);

        teacherWorkService.delete(teacherWork);

        return success();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
