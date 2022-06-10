package self.control.educational.service;

import self.control.educational.entity.Classrooms;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.util.List;

/**
 * 业务层处理
 * 教室信息
 */
public interface ClassroomsService extends BaseService<Classrooms, String> {

    /**
     * 获取学校教室的总数
     * @return
     */
    public Integer getTotalRooms();

    /**
     * 获取教室信息
     * @return
     */
    public List<Classrooms> getAll();

  /**
   * 分页查询教室信息信息
   * @param pageIndex 当前页码
   * @param pageSize 每页数据量
   * @param classrooms 模糊查询条件
   * @return
   */	
   public Page getPageInfo(Long pageIndex, Long pageSize, Classrooms classrooms);
}

