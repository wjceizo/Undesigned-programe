package self.control.educational.controller;

import onway.org.cn.comm.utils.id.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import onway.org.cn.comm.web.controller.JSONController;
import self.control.educational.entity.Activitys;
import self.control.educational.entity.Notices;
import self.control.educational.entity.Students;
import self.control.educational.entity.Users;
import self.control.educational.handle.CacheHandle;
import self.control.educational.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController extends JSONController {

    private static final Logger Log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CacheHandle cacheHandle;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ActivitysService activitysService;

    @Autowired
    private NoticesService noticesService;

    @Autowired
    private ClassroomsService classroomsService;

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private TeachersService teachersService;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception e) {

        e.printStackTrace();

        return error();
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String userName, String passWord) {

        Log.info("用户登录，用户名：{}， 用户密码：{}", userName, passWord);

        Users user = usersService.getUserByUserName(userName);

        if (user == null) {

            return error("输入的用户名不存在");
        } else {

            if (passWord.equals(user.getPassWord().trim())) {

                String token = IDUtils.makeIDByUUID();
                cacheHandle.addUserCache(token, user);

                return success("登录成功", token);

            } else {

                return error("输入的密码错误");
            }
        }
    }

    @RequestMapping(value = "exit", method = RequestMethod.GET)
    @ResponseBody
    public String logout(String token) {

        Log.info("用户退出系统并移除登录信息");

        cacheHandle.removeUserCache(token);

        return success();
    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public String info(String token){

        Users user = cacheHandle.getUserInfoCache(token);

        return success(user);
    }

    @RequestMapping(value = "updInfo", method = RequestMethod.POST)
    @ResponseBody
    public String updInfo(String token, Users user){

        Log.info("修改用户信息，{}", user);

        usersService.update(user);

        cacheHandle.addUserCache(token, user);

        return success();
    }

    @RequestMapping(value = "updPwd", method = RequestMethod.POST)
    @ResponseBody
    public String updPwd(String token, String newPwd) {

        Log.info("修改用户密码，{}", newPwd);

        Users user = cacheHandle.getUserInfoCache(token);

        user.setPassWord(newPwd);

        usersService.update(user);

        cacheHandle.addUserCache(token, user);

        return success();
    }

    @RequestMapping("/checkPwd")
    @ResponseBody
    public String rePwd(String oldPwd, String token) {

        Users user = cacheHandle.getUserInfoCache(token);

        if(oldPwd.equals(user.getPassWord())) {

            return success(true);
        }else {

            return success(false);
        }
    }

    @RequestMapping("/show")
    @ResponseBody
    public String getShowInfo(){

        Map<String, Object> resl = new HashMap<String, Object>();

        Integer total_stu = studentsService.getTotalStudents();

        Integer total_tea = teachersService.getTotalTeacher();

        Integer total_rooms = classroomsService.getTotalRooms();

        List<Activitys> list_activity = activitysService.getShowList();

        List<Notices> list_notice = noticesService.getShowList();

        resl.put("total_stu", total_stu);
        resl.put("total_tea", total_tea);
        resl.put("total_rooms", total_rooms);
        resl.put("list_activity", list_activity);
        resl.put("list_notice", list_notice);

        return success(resl);
    }
}
