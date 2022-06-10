package self.control.educational.controller;

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
import self.control.educational.entity.Classrooms;
import self.control.educational.service.ClassroomsService;

import java.util.List;

/**
 * 系统请求响应控制器
 * 教室信息
 */
@Controller
@RequestMapping("/classrooms")
public class ClassroomsController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(ClassroomsController.class);

    @Autowired
    private ClassroomsService classroomsService;

    @RequestMapping("")
    public String index() {

        return "pages/classrooms";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String getAll(){

        Log.info("获取全部的教室信息");

        List<Classrooms> list = classroomsService.getAll();

        return success(list);
    }

    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize,
                               Classrooms classrooms) {

        Log.info("分页查找教室信息信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, classrooms);

        Page page = classroomsService.getPageInfo(pageIndex, pageSize, classrooms);

        return success(page);
    }

    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String id) {

        Log.info("查找指定教室信息信息，ID：{}", id);

        Classrooms classrooms = classroomsService.getOne(id);

        return success(classrooms);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInfo(Classrooms classrooms) {

        classrooms.setId(IDUtils.makeIDByCurrent());

        Log.info("添加教室信息信息，传入参数：{}", classrooms);

        classroomsService.add(classrooms);

        return success();
    }

    @RequestMapping("/upd")
    @ResponseBody
    public String updInfo(Classrooms classrooms) {

        Log.info("修改教室信息信息，传入参数：{}", classrooms);

        classroomsService.update(classrooms);

        return success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delInfo(String id) {

        Log.info("删除教室信息信息, ID:{}", id);

        Classrooms classrooms = classroomsService.getOne(id);

        classroomsService.delete(classrooms);

        return success();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
