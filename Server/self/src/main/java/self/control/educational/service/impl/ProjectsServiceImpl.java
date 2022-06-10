package self.control.educational.service.impl;

import onway.org.cn.comm.utils.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.dao.ProjectPlansDao;
import self.control.educational.dao.ProjectsDao;
import self.control.educational.dao.TeacherWorkDao;
import self.control.educational.entity.ProjectPlans;
import self.control.educational.entity.Projects;
import self.control.educational.entity.TeacherWork;
import self.control.educational.service.ProjectsService;

import java.util.List;

@Service("projectsService")
public class ProjectsServiceImpl implements ProjectsService {

	@Autowired
	private TeacherWorkDao teacherWorkDao;

	@Autowired
    private ProjectsDao projectsDao;

	@Autowired
	private ProjectPlansDao projectPlansDao;
	
	@Override
	@Transactional
	public void add(Projects projects) {
		
		projectsDao.insert(projects);
	}

	@Override
	@Transactional
	public void update(Projects projects) {
		
		projectsDao.updateById(projects);
	}

	@Override
	@Transactional
	public void delete(Projects projects) {

		// 移除班级相关的课程安排和授课安排
		QueryWrapper<TeacherWork> qw_work = new QueryWrapper<TeacherWork>();
		qw_work.eq("project_id", projects.getId());
		List<TeacherWork> temp_work_list = teacherWorkDao.selectList(qw_work);
		if((temp_work_list != null) && (temp_work_list.size() > 0)){

			for(TeacherWork work : temp_work_list){

				QueryWrapper<ProjectPlans> qw_plan = new QueryWrapper<ProjectPlans>();
				qw_plan.eq("work_id", work.getId());
				projectPlansDao.delete(qw_plan);
				teacherWorkDao.deleteById(work);
			}
		}

		projectsDao.deleteById(projects);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Projects getOne(String id) {
		
		Projects projects = projectsDao.selectById(id);
		
		return projects;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<Projects> getAll(){

		List<Projects> list = projectsDao.selectList(null);

		return list;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public List<Projects> getNoSet(String greadId){

		List<Projects> list = teacherWorkDao.qryNoSetGrade(greadId);

		return list;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Projects projects){
				
		QueryWrapper<Projects> qw = new QueryWrapper<Projects>();

		if(StringUtils.isNotNullOrEmpty(projects.getName())){

			qw.like("name", projects.getName());
		}
		
		Page<Projects> page = 
				projectsDao.selectPage(new Page<Projects>(pageIndex, pageSize), qw);
		
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