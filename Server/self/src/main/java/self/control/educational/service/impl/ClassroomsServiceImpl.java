package self.control.educational.service.impl;

import onway.org.cn.comm.utils.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.ClassroomsDao;
import self.control.educational.dao.ProjectPlansDao;
import self.control.educational.entity.Classrooms;
import self.control.educational.entity.ProjectPlans;
import self.control.educational.service.ClassroomsService;

import java.util.List;

@Service("classroomsService")
public class ClassroomsServiceImpl implements ClassroomsService {
	
	@Autowired
    private ClassroomsDao classroomsDao;

	@Autowired
	private ProjectPlansDao projectPlansDao;
	
	@Override
	@Transactional
	public void add(Classrooms classrooms) {
		
		classroomsDao.insert(classrooms);
	}

	@Override
	@Transactional
	public void update(Classrooms classrooms) {
		
		classroomsDao.updateById(classrooms);
	}

	@Override
	@Transactional
	public void delete(Classrooms classrooms) {

		QueryWrapper<ProjectPlans> qw_plan = new QueryWrapper<ProjectPlans>();
		qw_plan.eq("room_id", classrooms.getId());
		projectPlansDao.delete(qw_plan);

		classroomsDao.deleteById(classrooms);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Integer getTotalRooms(){

		Integer total = classroomsDao.selectCount(null);

		return total;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Classrooms getOne(String id) {
		
		Classrooms classrooms = classroomsDao.selectById(id);
		
		return classrooms;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<Classrooms> getAll(){

		List<Classrooms> list = classroomsDao.selectList(null);

		return list;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Classrooms classrooms){
				
		QueryWrapper<Classrooms> qw = new QueryWrapper<Classrooms>();

		if(StringUtils.isNotNullOrEmpty(classrooms.getName())){

			qw.like("name", classrooms.getName());
		}

		if(StringUtils.isNotNullOrEmpty(classrooms.getLoc())){

			qw.like("loc", classrooms.getLoc());
		}
		
		Page<Classrooms> page = 
				classroomsDao.selectPage(new Page<Classrooms>(pageIndex, pageSize), qw);
		
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