package self.control.educational.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import self.control.educational.entity.Check;
import self.control.educational.entity.Projects;
import self.control.educational.entity.Students;

import java.util.List;
import java.util.Map;

@Repository("checkDao")
public interface CheckDao extends BaseMapper<Check> {
    @Select("SELECT * FROM qiandao WHERE stu_id = #{stuId} AND isdelete=#{isdelete}")
    public Check ischeck(String stuId,Boolean isdelete);
    @Select("UPDATE qiandao set isdelete=#{isdelete},ischeck=#{ischeck},checktime=#{checktime} WHERE stu_id = #{stuId} AND isdelete=#{deleted}")
    public Check check(Boolean isdelete,Boolean ischeck,String stuId,Boolean deleted,String checktime);
    @Select("UPDATE qiandao set isdelete=#{isdelete} WHERE stu_id = #{stuId}")
    public Check outtime(Boolean isdelete,String stuId);

}