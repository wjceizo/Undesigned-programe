package self.control.educational.dao;


import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Projects;

/**
 * 数据层处理接口
 * 课程信息
 */
@Repository("projectsDao")
public interface ProjectsDao extends BaseMapper<Projects> {
	

}
