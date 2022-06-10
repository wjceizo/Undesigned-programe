package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 学生信息
 */
@TableName(value = "students")
public class Students extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 所在班级
	 */
	@TableField(value = "grade_id")
	private String gradeId;

	/**
	 * 入学时间
	 */
	@TableField(value = "into_time")
	private String intoTime;


	public String getId(){
	    
	   return id;
	}
	
	public void setId(String id){
	    
	   this.id = id;
	}


	public String getGradeId(){
	    
	   return gradeId;
	}
	
	public void setGradeId(String gradeId){
	    
	   this.gradeId = gradeId;
	}


	public String getIntoTime(){
	    
	   return intoTime;
	}
	
	public void setIntoTime(String intoTime){
	    
	   this.intoTime = intoTime;
	}

	
     @Override
	public String toString() {
       
             return "Students [id" + id 
                                             + ", gradeId=" + gradeId
                                            + ", intoTime=" + intoTime
                                   + "]";
       }

}
