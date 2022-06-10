package self.control.educational.controller;

import onway.org.cn.comm.utils.date.DateUtils;
import onway.org.cn.comm.utils.id.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.controller.JSONController;
import self.control.educational.entity.Grades;
import self.control.educational.service.GradesService;

import java.util.List;

/**
 * 系统请求响应控制器
 * 班级信息
 */
@Controller
@RequestMapping("/grades")
public class GradesController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(GradesController.class);

    @Autowired
    private GradesService gradesService;

    @RequestMapping("")
    public String index() {

        return "pages/grades";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String getAll(){

        Log.info("获取全部的年级信息");

        List<Grades> list = gradesService.getAll();

        return success(list);
    }

    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize,
                               Grades grades) {

        Log.info("分页查找班级信息信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, grades);

        Page page = gradesService.getPageInfo(pageIndex, pageSize, grades);

        return success(page);
    }

    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String id) {

        Log.info("查找指定班级信息信息，ID：{}", id);

        Grades grades = gradesService.getOne(id);

        return success(grades);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInfo(Grades grades) {

        grades.setId(IDUtils.makeIDByCurrent());
        grades.setCreateTime(DateUtils.getNowDate());

        Log.info("添加班级信息信息，传入参数：{}", grades);

        gradesService.add(grades);

        return success();
    }

    @RequestMapping("/upd")
    @ResponseBody
    public String updInfo(Grades grades) {

        Log.info("修改班级信息信息，传入参数：{}", grades);

        gradesService.update(grades);

        return success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delInfo(String id) {

        Log.info("删除班级信息信息, ID:{}", id);

        Grades grades = gradesService.getOne(id);

        gradesService.delete(grades);

        return success();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
