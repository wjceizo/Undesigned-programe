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
import self.control.educational.entity.Users;
import self.control.educational.service.UsersService;

/**
 * 系统请求响应控制器
 * 系统用户
 */
@Controller
@RequestMapping("/users")
public class UsersController extends JSONController {

    protected static final Logger Log = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UsersService usersService;

    @RequestMapping("")
    public String index() {

        return "pages/users";
    }

    @RequestMapping("/page")
    @ResponseBody
    public String getPageInfos(Long pageIndex, Long pageSize,
                               Users users) {

        Log.info("分页查找系统用户信息，当前页码：{}，"
                        + "每页数据量：{}, 模糊查询，附加参数：{}", pageIndex,
                pageSize, users);

        Page page = usersService.getPageInfo(pageIndex, pageSize, users);

        return success(page);
    }

    @RequestMapping("/info")
    @ResponseBody
    public String getInfo(String id) {

        Log.info("查找指定系统用户信息，ID：{}", id);

        Users users = usersService.getOne(id);

        return success(users);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addInfo(Users users) {

        users.setId(IDUtils.makeIDByCurrent());
        users.setCreateTime(DateUtils.getNowDate());

        Log.info("添加系统用户信息，传入参数：{}", users);

        usersService.add(users);

        return success();
    }

    @RequestMapping("/upd")
    @ResponseBody
    public String updInfo(Users users) {

        Log.info("修改系统用户信息，传入参数：{}", users);

        usersService.update(users);

        return success();
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delInfo(String id) {

        Log.info("删除系统用户信息, ID:{}", id);

        Users users = usersService.getOne(id);

        usersService.delete(users);

        return success();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }
}
