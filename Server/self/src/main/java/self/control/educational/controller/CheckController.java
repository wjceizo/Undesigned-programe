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
import self.control.educational.entity.Activitys;
import self.control.educational.service.ActivitysService;
import self.control.educational.service.CheckService;

/**
 * 系统请求响应控制器
 * 活动信息
 */
@Controller
@RequestMapping("/check")
public class CheckController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(ActivitysController.class);

    @Autowired
    private CheckService checkService;



    @RequestMapping("/addcheck")
    @ResponseBody
    public String addCheck(String gradeId) {
        checkService.addcheck(gradeId);
        return success("成功发起签到");
    }

}
