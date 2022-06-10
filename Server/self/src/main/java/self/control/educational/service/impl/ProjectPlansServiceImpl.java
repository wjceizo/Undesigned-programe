package self.control.educational.service.impl;

import onway.org.cn.comm.utils.date.DateFormatStr;
import onway.org.cn.comm.utils.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.*;
import self.control.educational.entity.ProjectPlans;
import self.control.educational.service.ProjectPlansService;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("projectPlansService")
public class ProjectPlansServiceImpl implements ProjectPlansService {

	@Autowired
    private ProjectPlansDao projectPlansDao;

	@Override
	@Transactional
	public Boolean isAllowPlanInTime(String planTime, String roomId)  {

//		String temp = DateUtils.parseDateForMill(DateUtils.parseDate(planTime,
//								DateFormatStr.DATETIME_DEFAULT_FORMAT).getTime() + (50*60*1000),
//																DateFormatStr.DATETIME_DEFAULT_FORMAT);
//
//		QueryWrapper<ProjectPlans> qw = new QueryWrapper<ProjectPlans>();
//		qw.ge("create_time", planTime);
//		qw.le("create_time", temp);
//
//		qw.eq("room_id", roomId);

		Integer total = projectPlansDao.qryTotalInTimeRang(planTime, roomId);

		return total > 0 ? false : true;
	}

	@Override
	@Transactional
	public void add(ProjectPlans projectPlans) {
		
		projectPlansDao.insert(projectPlans);
	}

	@Override
	@Transactional
	public void update(ProjectPlans projectPlans) {
		
		projectPlansDao.updateById(projectPlans);
	}

	@Override
	@Transactional
	public void delete(ProjectPlans projectPlans) {
		
		projectPlansDao.deleteById(projectPlans);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public ProjectPlans getOne(String id) {
		
		ProjectPlans projectPlans = projectPlansDao.selectById(id);
		
		return projectPlans;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize,
							String gradeId, String teacherId, String projectId){
				

		
		Page<Map<String, Object>> page =
				projectPlansDao.
						qryPageInfos(new Page<Map<String, Object>>(pageIndex, pageSize),
								gradeId, teacherId, projectId);
		
		return parsePage(page);
	}
	
	/**
	 * 转化分页查询的结果
	 */	
	public onway.org.cn.comm.web.msg.Page 
								parsePage(Page p){
		
		onway.org.cn.comm.web.msg.Page page = 
								new onway.org.cn.comm.web.msg.Page();
		
		page.setPageIndex(p.getCurrent());
		page.setPageSize(p.getSize());
		
		Long pageTotal = (p.getTotal() % p.getSize()) == 0 ? 
							(p.getTotal() / p.getSize()) : (p.getTotal() / p.getSize() + 1);
		
		page.setPageTotal(pageTotal);
		page.setCount(p.getTotal());

		page.setData(p.getRecords());
		
		return page;
	}
}