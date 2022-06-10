package self.control.educational.dao;


import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Teachers;

/**
 * 数据层处理接口
 * 教师信息
 */
@Repository("teachersDao")
public interface TeachersDao extends BaseMapper<Teachers> {
	

}
