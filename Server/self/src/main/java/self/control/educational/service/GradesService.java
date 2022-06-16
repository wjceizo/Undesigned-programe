package self.control.educational.service;

import self.control.educational.entity.Grades;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.util.List;

/**
 * 业务层处理
 * 班级信息
 */
public interface GradesService extends BaseService<Grades, String> {

    /**
     * 获取全部的年级信息
     * @return
     */
    public List<Grades> getAll();

  /**
   * 分页查询班级信息信息
   * @param pageIndex 当前页码
   * @param pageSize 每页数据量
   * @param grades 模糊查询条件
   * @return
   */	
   public Page getPageInfo(Long pageIndex, Long pageSize, Grades grades);
}

