package self.control.educational.dao;


import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Classrooms;

/**
 * 数据层处理接口
 * 教室信息
 */
@Repository("classroomsDao")
public interface ClassroomsDao extends BaseMapper<Classrooms> {
	

}
