package self.control.educational.service;

import self.control.educational.entity.Check;
import self.control.educational.entity.Classrooms;
import onway.org.cn.comm.web.msg.Page;
import onway.org.cn.comm.web.service.BaseService;
import self.control.educational.entity.Teachers;

import java.util.List;


public interface CheckService extends BaseService<Check, String> {

    public List<Check> getuncheck();

    public void addcheck(String id);

    public void qiandao(Boolean isdelete, Boolean ischeck, String stuId, Boolean deleted, String checktime);

    public Check ischeck(String stuId,Boolean isdelete);

    public Check outtime(Boolean isdelete,String stuId);

    public Page getPageInfo(Long pageIndex, Long pageSize, Check check);
}

