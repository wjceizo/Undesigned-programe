package self.control.educational.service.impl;

import onway.org.cn.comm.utils.id.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import self.control.educational.dao.CheckDao;
import self.control.educational.dao.StudentsDao;
import self.control.educational.entity.Check;
import self.control.educational.entity.Students;
import self.control.educational.service.CheckService;

import java.util.List;

@Service("checkService")
public class CheckServiceImpl implements CheckService {
    @Autowired
    CheckDao checkDao;
    @Autowired
    StudentsDao studentsDao;
    @Override
    public List<Check> getuncheck() {
        return null;
    }

    @Transactional
    @Override
    public void addcheck(String gradeId) {
       List<Students> students=studentsDao.getStudents(gradeId);
      for (int i=0;i<students.size();i++){
          Check check=new Check();
          check.setId(students.get(i).getId());
          check.setId(IDUtils.makeIDByCurrent());
          checkDao.insert(check);
      }
    }
    @Override
    public void check() {

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
