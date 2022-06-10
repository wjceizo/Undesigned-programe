package self.control.educational.dao;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Projects;
import self.control.educational.entity.Students;

import java.util.List;

/**
 * 数据层处理接口
 * 学生信息
 */
@Repository("studentsDao")
public interface StudentsDao extends BaseMapper<Students> {
    @Select("SELECT * FROM students WHERE grade_id = #{gradeId}")
    public List<Students> getStudents(String gradeId);

}
