package self.control.educational.dao;


import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Activitys;

/**
 * 数据层处理接口
 * 活动信息
 */
@Repository("activitysDao")
public interface ActivitysDao extends BaseMapper<Activitys> {
	

}
