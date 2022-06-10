package self.control.educational.service;

import self.control.educational.entity.ProjectPlans;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.text.ParseException;

/**
 * 业务层处理
 * 上课安排
 */
public interface ProjectPlansService extends BaseService<ProjectPlans, String> {

    /**
     * 检查在指定时间是否可以安排课程
     * true-可以  false-不可以
     * @param planTime 上课时间
     * @param workId 工作ID
     */
    public Boolean isAllowPlanInTime(String planTime, String workId);

    /**
     * 分页查询上课安排信息
     * @param pageIndex    当前页码
     * @param pageSize     每页数据量
     * @param gradeId 班级编号
     * @param teacherId 教师编号
     * @param projectId 课程编号
     * @return
     */
    public Page getPageInfo(Long pageIndex, Long pageSize,
                            String gradeId, String teacherId, String projectId);
}

