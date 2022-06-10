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

/**
 * 系统请求响应控制器
 * 活动信息
 */
@Controller
@RequestMapping("/activitys")
public class ActivitysController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(ActivitysController.class);

    @Autowired
    private ActivitysService activitysService;

    @RequestMapping("")
    public String index() {

        return "pages/activitys";
    }

    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize,
                               Activitys activitys) {

        Log.info("分页查找活动信息信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, activitys);

        Page page = activitysService.getPageInfo(pageIndex, pageSize, activitys);

        return success(page);
    }

    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String id) {

        Log.info("查找指定活动信息信息，ID：{}", id);

        Activitys activitys = activitysService.getOne(id);

        return success(activitys);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInfo(Activitys activitys) {

        activitys.setId(IDUtils.makeIDByCurrent());

        Log.info("添加活动信息信息，传入参数：{}", activitys);

        activitysService.add(activitys);

        return success();
    }

    @RequestMapping("/upd")
    @ResponseBody
    public String updInfo(Activitys activitys) {

        Log.info("修改活动信息信息，传入参数：{}", activitys);

        activitysService.update(activitys);

        return success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delInfo(String id) {

        Log.info("删除活动信息信息, ID:{}", id);

        Activitys activitys = activitysService.getOne(id);

        activitysService.delete(activitys);

        return success();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
