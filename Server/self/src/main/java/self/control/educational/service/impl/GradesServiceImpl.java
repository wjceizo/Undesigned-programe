package self.control.educational.service.impl;

import onway.org.cn.comm.utils.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.GradesDao;
import self.control.educational.dao.ProjectPlansDao;
import self.control.educational.dao.StudentsDao;
import self.control.educational.dao.TeacherWorkDao;
import self.control.educational.entity.Grades;
import self.control.educational.entity.ProjectPlans;
import self.control.educational.entity.Students;
import self.control.educational.entity.TeacherWork;
import self.control.educational.service.GradesService;

import java.util.List;

@Service("gradesService")
public class GradesServiceImpl implements GradesService {
	
	@Autowired
    private GradesDao gradesDao;

	@Autowired
	private StudentsDao studentsDao;

	@Autowired
	private TeacherWorkDao teacherWorkDao;

	@Autowired
	private ProjectPlansDao projectPlansDao;
	
	@Override
	@Transactional
	public void add(Grades grades) {
		
		gradesDao.insert(grades);
	}

	@Override
	@Transactional
	public void update(Grades grades) {
		
		gradesDao.updateById(grades);
	}

	@Override
	@Transactional
	public void delete(Grades grades) {

		// 重置学生所属年级
		QueryWrapper<Students> qw_student = new QueryWrapper<Students>();
		qw_student.eq("grade_id", grades.getId());
		List<Students> temp_student_list = studentsDao.selectList(qw_student);
		if((temp_student_list != null) && (temp_student_list.size() > 0)){

			for(Students student : temp_student_list){

				student.setGradeId(null);
				studentsDao.updateById(student);
			}
		}

		// 移除班级相关的课程安排和授课安排
		QueryWrapper<TeacherWork> qw_work = new QueryWrapper<TeacherWork>();
		qw_work.eq("grade_id", grades.getId());
		List<TeacherWork> temp_work_list = teacherWorkDao.selectList(qw_work);
		if((temp_work_list != null) && (temp_work_list.size() > 0)){

			for(TeacherWork work : temp_work_list){

				QueryWrapper<ProjectPlans> qw_plan = new QueryWrapper<ProjectPlans>();
				qw_plan.eq("work_id", work.getId());
				projectPlansDao.delete(qw_plan);
				teacherWorkDao.deleteById(work);
			}
		}
		
		gradesDao.deleteById(grades);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Grades getOne(String id) {
		
		Grades grades = gradesDao.selectById(id);
		
		return grades;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<Grades> getAll(){

		List<Grades> list = gradesDao.selectList(null);

		return list;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Grades grades){
				
		QueryWrapper<Grades> qw = new QueryWrapper<Grades>();

		if(StringUtils.isNotNullOrEmpty(grades.getName())){

			qw.like("name", grades.getName());
		}

		Page<Grades> page = 
				gradesDao.selectPage(new Page<Grades>(pageIndex, pageSize), qw);
		
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