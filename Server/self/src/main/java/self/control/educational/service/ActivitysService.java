package self.control.educational.service;

import self.control.educational.entity.Activitys;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.util.List;

/**
 * 业务层处理
 * 活动信息
 */
public interface ActivitysService extends BaseService<Activitys, String> {

    /**
     * 获取未过期可以展示的活动列表
     * @return
     */
    public List<Activitys> getShowList();
	
  /**
   * 分页查询活动信息信息
   * @param pageIndex 当前页码
   * @param pageSize 每页数据量
   * @param activitys 模糊查询条件
   * @return
   */	
   public Page getPageInfo(Long pageIndex, Long pageSize, Activitys activitys);
}

