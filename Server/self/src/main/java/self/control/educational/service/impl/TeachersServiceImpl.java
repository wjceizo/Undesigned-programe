package self.control.educational.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.TeachersDao;
import self.control.educational.entity.Teachers;
import self.control.educational.service.TeachersService;

@Service("teachersService")
public class TeachersServiceImpl implements TeachersService {
	
	@Autowired
    private TeachersDao teachersDao;
	
	@Override
	@Transactional
	public void add(Teachers teachers) {
		
		teachersDao.insert(teachers);
	}

	@Override
	@Transactional
	public void update(Teachers teachers) {
		
		teachersDao.updateById(teachers);
	}

	@Override
	@Transactional
	public void delete(Teachers teachers) {
		
		teachersDao.deleteById(teachers);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Integer getTotalTeacher(){

		Integer total = teachersDao.selectCount(null);

		return total;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Teachers getOne(String id) {
		
		Teachers teachers = teachersDao.selectById(id);
		
		return teachers;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Teachers teachers){
				
		QueryWrapper<Teachers> qw = new QueryWrapper<Teachers>();
		
		Page<Teachers> page = 
				teachersDao.selectPage(new Page<Teachers>(pageIndex, pageSize), qw);
		
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