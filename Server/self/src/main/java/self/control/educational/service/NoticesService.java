package self.control.educational.service;

import self.control.educational.entity.Activitys;
import self.control.educational.entity.Notices;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.util.List;

/**
 * 业务层处理
 * 系统通知
 */
public interface NoticesService extends BaseService<Notices, String> {

    /**
     * 获取可以展示的通知信息列表
     * @return
     */
    public List<Notices> getShowList();

  /**
   * 分页查询系统通知信息
   * @param pageIndex 当前页码
   * @param pageSize 每页数据量
   * @param notices 模糊查询条件
   * @return
   */	
   public Page getPageInfo(Long pageIndex, Long pageSize, Notices notices);
}

