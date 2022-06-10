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
import self.control.educational.entity.Teachers;
import self.control.educational.service.TeachersService;

/**
 * 系统请求响应控制器
 * 教师信息
 */
@Controller
@RequestMapping("/teachers")
public class TeachersController extends JSONController {
   
   protected static final Logger Log = LoggerFactory.getLogger(TeachersController.class);
   
   @Autowired
   private TeachersService teachersService;

   @RequestMapping("")
   public String index() {
		
		return "pages/teachers";
   }
	
	@RequestMapping("/page")
	@ResponseBody
	public String getPageInfos(Long pageIndex, Long pageSize, 
							Teachers teachers) {
		
		Log.info("分页查找教师信息信息，当前页码：{}，"
				+ "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex, 
										pageSize, teachers);
		
		Page page = teachersService.getPageInfo(pageIndex, pageSize, teachers);
		
		return success(page);
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public String getInfo(String id) {
		
		Log.info("查找指定教师信息信息，ID：{}", id);
		
		Teachers teachers = teachersService.getOne(id);
		
		return success(teachers);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addInfo(Teachers teachers) {
		
		Log.info("添加教师信息信息，传入参数：{}", teachers);
		
		teachersService.add(teachers);
		
		return success();
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public String updInfo(Teachers teachers) {
		
		Log.info("修改教师信息信息，传入参数：{}", teachers);
		
		teachersService.update(teachers);
		
		return success();
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public String delInfo(String id) {
		
		Log.info("删除教师信息信息, ID:{}", id);
		
		Teachers teachers = teachersService.getOne(id);
		
		teachersService.delete(teachers);
		
		return success();
	}
	
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
