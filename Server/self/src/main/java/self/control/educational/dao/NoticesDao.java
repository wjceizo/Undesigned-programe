package self.control.educational.dao;


import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Notices;

/**
 * 数据层处理接口
 * 系统通知
 */
@Repository("noticesDao")
public interface NoticesDao extends BaseMapper<Notices> {
	

}
