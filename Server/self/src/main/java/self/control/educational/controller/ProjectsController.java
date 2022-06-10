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
import self.control.educational.entity.Projects;
import self.control.educational.service.ProjectsService;

import java.util.List;

/**
 * 系统请求响应控制器
 * 课程信息
 */
@Controller
@RequestMapping("/projects")
public class ProjectsController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
    private ProjectsService projectsService;

    @RequestMapping("")
    public String index() {

        return "pages/projects";
    }

	@RequestMapping("/all")
	@ResponseBody
    public String getAll(){

		Log.info("获取全部的年级信息");

		List<Projects> list = projectsService.getAll();

		return success(list);
	}

    @RequestMapping("/allow")
    @ResponseBody
	public String getAllowList(String gradeId){

        Log.info("获取当前班级可以选择的课程列表");

        List<Projects> list = projectsService.getNoSet(gradeId);

        return success(list);
    }

    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize,
                               Projects projects) {

        Log.info("分页查找课程信息信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, projects);

        Page page = projectsService.getPageInfo(pageIndex, pageSize, projects);

        return success(page);
    }

    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String id) {

        Log.info("查找指定课程信息信息，ID：{}", id);

        Projects projects = projectsService.getOne(id);

        return success(projects);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInfo(Projects projects) {

        projects.setId(IDUtils.makeIDByCurrent());
        projects.setCreateTime(DateUtils.getNowDate());

        Log.info("添加课程信息信息，传入参数：{}", projects);

        projectsService.add(projects);

        return success();
    }

    @RequestMapping("/upd")
    @ResponseBody
    public String updInfo(Projects projects) {

        Log.info("修改课程信息信息，传入参数：{}", projects);

        projectsService.update(projects);

        return success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delInfo(String id) {

        Log.info("删除课程信息信息, ID:{}", id);

        Projects projects = projectsService.getOne(id);

        projectsService.delete(projects);

        return success();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
