package self.control.educational.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.StudentsDao;
import self.control.educational.entity.Students;
import self.control.educational.service.StudentsService;

import java.util.List;

@Service("studentsService")
public class StudentsServiceImpl implements StudentsService {
	
	@Autowired
    private StudentsDao studentsDao;
	
	@Override
	@Transactional
	public void add(Students students) {
		
		studentsDao.insert(students);
	}

	@Override
	@Transactional
	public void update(Students students) {
		
		studentsDao.updateById(students);
	}

	@Override
	@Transactional
	public void delete(Students students) {
		
		studentsDao.deleteById(students);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Students getOne(String id) {
		
		Students students = studentsDao.selectById(id);
		
		return students;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Integer getTotalStudents(){

		Integer total = studentsDao.selectCount(null);

		return total;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Students students){
				
		QueryWrapper<Students> qw = new QueryWrapper<Students>();
		
		Page<Students> page = 
				studentsDao.selectPage(new Page<Students>(pageIndex, pageSize), qw);
		
		return parsePage(page);
	}

	@Override
	public List<Students> getStudentsBygradeid(String gradeId) {
		return studentsDao.getStudents(gradeId);
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