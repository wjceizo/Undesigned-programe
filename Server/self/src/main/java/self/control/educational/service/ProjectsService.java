package self.control.educational.service;

import self.control.educational.entity.Projects;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.util.List;

/**
 * 业务层处理
 * 课程信息
 */
public interface ProjectsService extends BaseService<Projects, String> {

    /**
     * 获取全部的课程信息
     *
     * @return
     */
    public List<Projects> getAll();

    /**
     * 获取指定班级中没有设置的课程列表
     *
     * @param greadId 指定班级编号
     * @return
     */
    public List<Projects> getNoSet(String greadId);

    /**
     * 分页查询课程信息信息
     *
     * @param pageIndex 当前页码
     * @param pageSize  每页数据量
     * @param projects  模糊查询条件
     * @return
     */
    public Page getPageInfo(Long pageIndex, Long pageSize, Projects projects);
}

