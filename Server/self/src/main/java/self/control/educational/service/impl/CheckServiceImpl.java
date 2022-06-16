package self.control.educational.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import onway.org.cn.comm.utils.id.IDUtils;
import onway.org.cn.comm.utils.str.StringUtils;
import onway.org.cn.comm.web.msg.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import self.control.educational.dao.*;
import self.control.educational.entity.*;
import self.control.educational.service.CheckService;
import self.control.educational.service.TeacherWorkService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("checkService")
public class CheckServiceImpl implements CheckService {
    @Autowired
    CheckDao checkDao;
    @Autowired
    StudentsDao studentsDao;
    @Autowired
    TeacherWorkDao teacherWorkDao;
    @Autowired
    ProjectsDao projectsDao;
    @Autowired
    UsersDao usersDao;
    @Autowired
    GradesDao gradesDao;
    @Override
    public List<Check> getuncheck() {
        return null;
    }

    @Transactional
    @Override
    public void addcheck(String id) {
        TeacherWork teacherWork=teacherWorkDao.selectById(id);
       List<Students> students=studentsDao.getStudents(teacherWork.getGradeId());
      for (int i=0;i<students.size();i++){
          Check check=new Check();
          check.setStuId(students.get(i).getId());
          check.setId(IDUtils.makeIDByCurrent());
          check.setProjectId(teacherWork.getProjectId());
          check.setTeacherId(teacherWork.getTeacherId());
          check.setGradesId(teacherWork.getGradeId());
          check.setIsdelete(false);
          check.setIscheck(false);
          checkDao.insert(check);
      }
    }
    @Override
    public void qiandao(Boolean isdelete,Boolean ischeck,String stuId,Boolean deleted,String checktime) {
         checkDao.check(isdelete,ischeck,stuId,deleted,checktime);
    }

    @Override
    public Check ischeck(String stuId,Boolean isdelete) {
        return checkDao.ischeck(stuId,isdelete);
    }

    @Override
    public Check outtime(Boolean isdelete, String stuId) {
        return checkDao.outtime(isdelete,stuId);
    }


    @Override
    @Transactional(readOnly=true, propagation= Propagation.SUPPORTS)
    public onway.org.cn.comm.web.msg.Page
    getPageInfo(Long pageIndex, Long pageSize, Check check){

        QueryWrapper<Check> qw = new QueryWrapper<Check>();

        if(StringUtils.isNotNullOrEmpty(check.getTeacherId())){

            qw.eq("teacher_id", check.getTeacherId());
        }

        if(StringUtils.isNotNullOrEmpty(check.getGradesId())){

            qw.eq("grades_id", check.getGradesId());
        }

        if(StringUtils.isNotNullOrEmpty(check.getProjectId())){

            qw.eq("project_id", check.getProjectId());
        }
        if(StringUtils.isNotNullOrEmpty(check.getStuId())){

            qw.eq("stu_id", check.getStuId());
        }
        qw.orderByDesc("addtime");

        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Check> page =
                checkDao.selectPage(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<Check>(pageIndex, pageSize), qw);

        return parsePage(page);
    }

    /**
     * 转化分页查询的结果
     */
    public onway.org.cn.comm.web.msg.Page
    parsePage(com.baomidou.mybatisplus.extension.plugins.pagination.Page<Check> p){

        onway.org.cn.comm.web.msg.Page page =
                new onway.org.cn.comm.web.msg.Page();

        page.setPageIndex(p.getCurrent());
        page.setPageSize(p.getSize());

        Long pageTotal = (p.getTotal() % p.getSize()) == 0 ?
                (p.getTotal() / p.getSize()) : (p.getTotal() / p.getSize() + 1);

        page.setPageTotal(pageTotal);
        page.setCount(p.getTotal());

        List<Map<String, Object>> resl = new ArrayList<Map<String, Object>>();

        for(Check check : p.getRecords()){

            Map<String, Object> temp = new HashMap<String, Object>();
            temp.put("id",check.getId());
            temp.put("addTime", check.getAddTime());
            temp.put("checkTime", check.getCheckTime());
            Projects project = projectsDao.selectById(check.getProjectId());
            temp.put("projectId", project.getId());
            temp.put("projectName", project.getName());
            temp.put("isCheck", check.getIscheck());
            Users user = usersDao.selectById(check.getTeacherId());
            temp.put("teacherId", user.getId());
            temp.put("teacherName", user.getName());
            Users user1 = usersDao.selectById(check.getStuId());
            temp.put("studentId", user1.getId());
            temp.put("studentName", user1.getName());
            Grades grade = gradesDao.selectById(check.getGradesId());
            temp.put("gradeId", grade.getId());
            temp.put("gradeName", grade.getName());

            resl.add(temp);
        }

        page.setData(resl);

        return page;
    }

    @Override
    public void add(Check check) {

    }

    @Override
    public void update(Check check) {

    }

    @Override
    public void delete(Check check) {

    }

    @Override
    public Check getOne(String s) {
        return null;
    }
}
