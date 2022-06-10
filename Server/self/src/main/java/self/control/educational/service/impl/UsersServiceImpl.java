package self.control.educational.service.impl;

import onway.org.cn.comm.utils.str.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import self.control.educational.comm.UserCode;
import self.control.educational.dao.*;
import self.control.educational.entity.*;
import self.control.educational.service.UsersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	
	@Autowired
    private UsersDao usersDao;

	@Autowired
	private GradesDao gradesDao;

	@Autowired
	private TeachersDao teachersDao;

	@Autowired
	private StudentsDao studentsDao;

	@Autowired
	private TeacherWorkDao teacherWorkDao;

	@Autowired
	private ProjectPlansDao projectPlansDao;
	
	@Override
	@Transactional
	public void add(Users users) {
		
		usersDao.insert(users);
	}

	@Override
	@Transactional
	public void update(Users users) {
		
		usersDao.updateById(users);
	}

	@Override
	@Transactional
	public void delete(Users users) {

		if(UserCode.USER_TEACHER.equals(users.getType())){

			QueryWrapper<TeacherWork> qw_work = new QueryWrapper<TeacherWork>();
			qw_work.eq("teacher_id", users.getId());
			List<TeacherWork> temp_work_list = teacherWorkDao.selectList(qw_work);
			if((temp_work_list != null) && (temp_work_list.size() > 0)){

				for(TeacherWork work : temp_work_list){

					QueryWrapper<ProjectPlans> qw_plan = new QueryWrapper<ProjectPlans>();
					qw_plan.eq("work_id", work.getId());
					projectPlansDao.delete(qw_plan);
					teacherWorkDao.deleteById(work);
				}
			}
			teachersDao.deleteById(users.getId());
		}else if(UserCode.USER_STUDENT.equals(users.getType())){

			studentsDao.deleteById(users.getId());
		}

		usersDao.deleteById(users);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Users getOne(String id) {

		Users users = usersDao.selectById(id);
		
		return users;
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public Users getUserByUserName(String userName){

		QueryWrapper<Users> qw = new QueryWrapper<Users>();
		qw.eq("user_name", userName);
		Users users = usersDao.selectOne(qw);

		return users;
	}
	
	@Override
	@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
	public onway.org.cn.comm.web.msg.Page 
				getPageInfo(Long pageIndex, Long pageSize, Users users){
				
		QueryWrapper<Users> qw = new QueryWrapper<Users>();

		if(StringUtils.isNotNullOrEmpty(users.getUserName())){

			qw.like("user_name", users.getUserName());
		}

		if(StringUtils.isNotNullOrEmpty(users.getName())){

			qw.like("name", users.getName());
		}

		Page<Users> page = 
				usersDao.selectPage(new Page<Users>(pageIndex, pageSize), qw);
		
		return parsePage(page);
	}
	
	/**
	 * 转化分页查询的结果
	 */	
	public onway.org.cn.comm.web.msg.Page 
								parsePage(Page<Users> p){
		
		onway.org.cn.comm.web.msg.Page page = 
								new onway.org.cn.comm.web.msg.Page();
		
		page.setPageIndex(p.getCurrent());
		page.setPageSize(p.getSize());
		
		Long pageTotal = (p.getTotal() % p.getSize()) == 0 ? 
							(p.getTotal() / p.getSize()) : (p.getTotal() / p.getSize() + 1);
		
		page.setPageTotal(pageTotal);
		page.setCount(p.getTotal());

		List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

		for(Users user : p.getRecords()){

			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("id", user.getId());
			temp.put("userName", user.getUserName());
			temp.put("passWord", user.getPassWord());
			temp.put("name", user.getName());
			temp.put("age", user.getAge());
			temp.put("type", user.getType());
			temp.put("gender", user.getGender());
			temp.put("createTime", user.getCreateTime());

			if(UserCode.USER_TEACHER.equals(user.getType())){

				Teachers teacher = teachersDao.selectById(user.getId());
				if(teacher != null){

					Map<String, Object> temp_teacher = new HashMap<String, Object>();
					temp_teacher.put("record", teacher.getRecord());
					temp_teacher.put("job", teacher.getJob());
					temp_teacher.put("intoTime", teacher.getIntoTime());

					temp.put("teacher", temp_teacher);
				}
			}else if(UserCode.USER_STUDENT.equals(user.getType())){

				Students student = studentsDao.selectById(user.getId());
				if(student != null){

					Map<String, Object> temp_student = new HashMap<String, Object>();
					if(StringUtils.isNotNullOrEmpty(student.getGradeId())){
						Grades grade = gradesDao.selectById(student.getGradeId());
						temp_student.put("gradeId", grade.getId());
						temp_student.put("gradeName", grade.getName());
					}
					temp_student.put("intoTime", student.getIntoTime());

					temp.put("student", temp_student);
				}
			}

			resl.add(temp);
		}

		page.setData(resl);
		
		return page;
	}
}