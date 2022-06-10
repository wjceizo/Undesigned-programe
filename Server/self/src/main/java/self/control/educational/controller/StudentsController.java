package self.control.educational.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.controller.JSONController;
import self.control.educational.entity.Students;
import self.control.educational.service.StudentsService;

/**
 * 系统请求响应控制器
 * 学生信息
 */
@Controller
@RequestMapping("/students")
public class StudentsController extends JSONController {
   
   protected static final Logger Log = LoggerFactory.getLogger(StudentsController.class);
   
   @Autowired
   private StudentsService studentsService;

   @RequestMapping("")
   public String index() {
		
		return "pages/students";
   }
	
	@RequestMapping("/page")
	@ResponseBody
	public String getPageInfos(Long pageIndex, Long pageSize, 
							Students students) {
		
		Log.info("分页查找学生信息信息，当前页码：{}，"
				+ "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex, 
										pageSize, students);
		
		Page page = studentsService.getPageInfo(pageIndex, pageSize, students);
		
		return success(page);
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public String getInfo(String id) {
		
		Log.info("查找指定学生信息信息，ID：{}", id);
		
		Students students = studentsService.getOne(id);
		
		return success(students);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addInfo(Students students) {
		
		Log.info("添加学生信息信息，传入参数：{}", students);
		
		studentsService.add(students);
		
		return success();
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public String updInfo(Students students) {
		
		Log.info("修改学生信息信息，传入参数：{}", students);
		
		studentsService.update(students);
		
		return success();
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public String delInfo(String id) {
		
		Log.info("删除学生信息信息, ID:{}", id);
		
		Students students = studentsService.getOne(id);
		
		studentsService.delete(students);
		
		return success();
	}
	
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
