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
import self.control.educational.entity.Notices;
import self.control.educational.service.NoticesService;

/**
 * 系统请求响应控制器
 * 系统通知
 */
@Controller
@RequestMapping("/notices")
public class NoticesController extends JSONController {
   
   protected static final Logger Log = LoggerFactory.getLogger(NoticesController.class);
   
   @Autowired
   private NoticesService noticesService;

   @RequestMapping("")
   public String index() {
		
		return "pages/notices";
   }
	
	@RequestMapping("/page")
	@ResponseBody
	public String getPageInfos(Long pageIndex, Long pageSize, 
							Notices notices) {
		
		Log.info("分页查找系统通知信息，当前页码：{}，"
				+ "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex, 
										pageSize, notices);
		
		Page page = noticesService.getPageInfo(pageIndex, pageSize, notices);
		
		return success(page);
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public String getInfo(String id) {
		
		Log.info("查找指定系统通知信息，ID：{}", id);
		
		Notices notices = noticesService.getOne(id);
		
		return success(notices);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String addInfo(Notices notices) {

		notices.setId(IDUtils.makeIDByCurrent());
		
		Log.info("添加系统通知信息，传入参数：{}", notices);
		
		noticesService.add(notices);
		
		return success();
	}
	
	@RequestMapping("/upd")
	@ResponseBody
	public String updInfo(Notices notices) {
		
		Log.info("修改系统通知信息，传入参数：{}", notices);
		
		noticesService.update(notices);
		
		return success();
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public String delInfo(String id) {
		
		Log.info("删除系统通知信息, ID:{}", id);
		
		Notices notices = noticesService.getOne(id);
		
		noticesService.delete(notices);
		
		return success();
	}
	
	@ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
