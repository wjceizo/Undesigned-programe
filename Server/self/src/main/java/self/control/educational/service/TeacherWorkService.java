package self.control.educational.service;

import self.control.educational.entity.Projects;
import self.control.educational.entity.TeacherWork;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.util.List;

/**
 * 业务层处理
 * 授课安排
 */
public interface TeacherWorkService extends BaseService<TeacherWork, String> {

    /**
     * 分页查询授课安排信息
     *
     * @param pageIndex   当前页码
     * @param pageSize    每页数据量
     * @param teacherWork 模糊查询条件
     * @return
     */
    public Page getPageInfo(Long pageIndex, Long pageSize, TeacherWork teacherWork);
}

