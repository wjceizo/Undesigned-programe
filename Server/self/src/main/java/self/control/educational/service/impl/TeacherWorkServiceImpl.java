package self.control.educational.service.impl;

import onway.org.cn.comm.utils.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.*;
import self.control.educational.entity.Grades;
import self.control.educational.entity.Projects;
import self.control.educational.entity.TeacherWork;
import self.control.educational.entity.Users;
import self.control.educational.service.TeacherWorkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("teacherWorkService")
public class TeacherWorkServiceImpl implements TeacherWorkService {

	@Autowired
	private ProjectsDao projectsDao;

	@Autowired
	private TeachersDao teachersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private GradesDao gradesDao;

	@Autowired
    private TeacherWorkDao teacherWorkDao;
	
	@Override
	@Transactional
	public void add(TeacherWork teacherWork) {
		
		teacherWorkDao.insert(teacherWork);
	}

	@Override
	@Transactional
	public void update(TeacherWork teacherWork) {
		
		teacherWorkDao.updateById(teacherWork);
	}

	@Override
	@Transactional
	public void delete(TeacherWork teacherWork) {
		
		teacherWorkDao.deleteById(teacherWork);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public TeacherWork getOne(String id) {
		
		TeacherWork teacherWork = teacherWorkDao.selectById(id);
		
		return teacherWork;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, TeacherWork teacherWork){
				
		QueryWrapper<TeacherWork> qw = new QueryWrapper<TeacherWork>();

		if(StringUtils.isNotNullOrEmpty(teacherWork.getTeacherId())){

			qw.eq("teacher_id", teacherWork.getTeacherId());
		}

		if(StringUtils.isNotNullOrEmpty(teacherWork.getGradeId())){

			qw.eq("grade_id", teacherWork.getGradeId());
		}

		if(StringUtils.isNotNullOrEmpty(teacherWork.getProjectId())){

			qw.eq("project_id", teacherWork.getProjectId());
		}

		qw.orderByDesc("create_time");

		Page<TeacherWork> page = 
				teacherWorkDao.selectPage(new Page<TeacherWork>(pageIndex, pageSize), qw);
		
		return parsePage(page);
	}
	
	/**
	 * 转化分页查询的结果
	 */	
	public onway.org.cn.comm.web.msg.Page 
								parsePage(Page<TeacherWork> p){
		
		onway.org.cn.comm.web.msg.Page page = 
								new onway.org.cn.comm.web.msg.Page();
		
		page.setPageIndex(p.getCurrent());
		page.setPageSize(p.getSize());
		
		Long pageTotal = (p.getTotal() % p.getSize()) == 0 ? 
							(p.getTotal() / p.getSize()) : (p.getTotal() / p.getSize() + 1);
		
		page.setPageTotal(pageTotal);
		page.setCount(p.getTotal());

		List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

		for(TeacherWork work : p.getRecords()){

			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", work.getId());
			temp.put("createTime", work.getCreateTime());

			Projects project = projectsDao.selectById(work.getProjectId());
			temp.put("projectId", project.getId());
			temp.put("projectName", project.getName());

			Users user = usersDao.selectById(work.getTeacherId());
			temp.put("teacherId", user.getId());
			temp.put("teacherName", user.getName());

			Grades grade = gradesDao.selectById(work.getGradeId());
			temp.put("gradeId", grade.getId());
			temp.put("gradeName", grade.getName());

			resl.add(temp);
		}

		page.setData(resl);
		
		return page;
	}
}