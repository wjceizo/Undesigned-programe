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
import self.control.educational.entity.*;
import self.control.educational.handle.CacheHandle;
import self.control.educational.service.ActivitysService;
import self.control.educational.service.CheckService;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Autowired
    private CacheHandle cacheHandle;


    @RequestMapping("/addcheck")
    @ResponseBody
    public String addCheck(String id) {
        checkService.addcheck(id);
        return success("成功发起签到");
    }
    @RequestMapping("/ischeck")
    @ResponseBody
    public String isCheck(String stuId) {
        System.out.println(stuId);
       Check check= checkService.ischeck(stuId,false);
        return success(check);
    }
    @RequestMapping("/qiandao")
    @ResponseBody
    public String qiandao(String stuId) {

        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        checkService.qiandao(true,true,stuId,false,df.format(day));
        return success("签到成功");
    }
    @RequestMapping("/outtime")
    @ResponseBody
    public String outtime(String stuId) {
        System.out.println(1111);
        checkService.outtime(true,stuId);
        return success("签到超时");
    }


    @RequestMapping("/getrecord")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize, String token,
                               Check check) {

        Users user = cacheHandle.getUserInfoCache(token);

        if(UserCode.USER_TEACHER.equals(user.getType())){

            check.setTeacherId(user.getId());
        }

        Log.info("分页查找授课安排信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, check);

        Page page = checkService.getPageInfo(pageIndex, pageSize, check);

        return success(page);
    }

}
