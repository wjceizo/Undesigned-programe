package self.control.educational.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import self.control.educational.entity.Check;

@Repository("checkDao")
public interface CheckDao extends BaseMapper<Check> {

}