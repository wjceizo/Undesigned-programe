package self.control.educational.service;

import self.control.educational.entity.Teachers;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

/**
 * 业务层处理
 * 教师信息
 */
public interface TeachersService extends BaseService<Teachers, String> {

    /**
     * 获取学校教师的总数
     * @return
     */
    public Integer getTotalTeacher();
	
  /**
   * 分页查询教师信息信息
   * @param pageIndex 当前页码
   * @param pageSize 每页数据量
   * @param teachers 模糊查询条件
   * @return
   */	
   public Page getPageInfo(Long pageIndex, Long pageSize, Teachers teachers);
}

