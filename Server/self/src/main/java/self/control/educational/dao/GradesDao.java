package self.control.educational.dao;


import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Grades;

/**
 * 数据层处理接口
 * 班级信息
 */
@Repository("gradesDao")
public interface GradesDao extends BaseMapper<Grades> {
	

}
