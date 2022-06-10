package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 课程信息
 */
@TableName(value = "projects")
public class Projects extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 课程名称
	 */
	@TableField(value = "name")
	private String name;

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


	public String getName(){
	    
	   return name;
	}
	
	public void setName(String name){
	    
	   this.name = name;
	}


	public String getCreateTime(){
	    
	   return createTime;
	}
	
	public void setCreateTime(String createTime){
	    
	   this.createTime = createTime;
	}

	
     @Override
	public String toString() {
       
             return "Projects [id" + id 
                                             + ", name=" + name
                                            + ", createTime=" + createTime
                                   + "]";
       }

}
