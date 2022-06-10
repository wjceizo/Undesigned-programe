package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 授课安排
 */
@TableName(value = "teacher_work")
public class TeacherWork extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 班级ID
	 */
	@TableField(value = "grade_id")
	private String gradeId;

	/**
	 * 课程ID
	 */
	@TableField(value = "project_id")
	private String projectId;

	/**
	 * 教师ID
	 */
	@TableField(value = "teacher_id")
	private String teacherId;

	/**
	 * 记录时间
	 */
	@TableField(value = "create_time")
	private String createTime;


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


	public String getProjectId(){
	    
	   return projectId;
	}
	
	public void setProjectId(String projectId){
	    
	   this.projectId = projectId;
	}


	public String getTeacherId(){
	    
	   return teacherId;
	}
	
	public void setTeacherId(String teacherId){
	    
	   this.teacherId = teacherId;
	}


	public String getCreateTime(){
	    
	   return createTime;
	}
	
	public void setCreateTime(String createTime){
	    
	   this.createTime = createTime;
	}

	
     @Override
	public String toString() {
       
             return "TeacherWork [id" + id 
                                             + ", gradeId=" + gradeId
                                            + ", projectId=" + projectId
                                            + ", teacherId=" + teacherId
                                            + ", createTime=" + createTime
                                   + "]";
       }

}
