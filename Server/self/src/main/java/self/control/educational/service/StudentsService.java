package self.control.educational.service;

import self.control.educational.entity.Students;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;

import java.util.List;

/**
 * 业务层处理
 * 学生信息
 */
public interface StudentsService extends BaseService<Students, String> {

    /**
     * 获取学生的总人数
     * @return
     */
    public Integer getTotalStudents();
	
  /**
   * 分页查询学生信息信息
   * @param pageIndex 当前页码
   * @param pageSize 每页数据量
   * @param students 模糊查询条件
   * @return
   */	
   public Page getPageInfo(Long pageIndex, Long pageSize, Students students);

   public List<Students> getStudentsBygradeid(String gradeId);
}

