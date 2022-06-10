package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 教师信息
 */
@TableName(value = "teachers")
public class Teachers extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 教师信息
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 教师学历
	 */
	@TableField(value = "record")
	private String record;

	/**
	 * 教师职称
	 */
	@TableField(value = "job")
	private String job;

	/**
	 * 入职时间
	 */
	@TableField(value = "into_time")
	private String intoTime;


	public String getId(){
	    
	   return id;
	}
	
	public void setId(String id){
	    
	   this.id = id;
	}


	public String getRecord(){
	    
	   return record;
	}
	
	public void setRecord(String record){
	    
	   this.record = record;
	}


	public String getJob(){
	    
	   return job;
	}
	
	public void setJob(String job){
	    
	   this.job = job;
	}


	public String getIntoTime(){
	    
	   return intoTime;
	}
	
	public void setIntoTime(String intoTime){
	    
	   this.intoTime = intoTime;
	}

	
     @Override
	public String toString() {
       
             return "Teachers [id" + id 
                                             + ", record=" + record
                                            + ", job=" + job
                                            + ", intoTime=" + intoTime
                                   + "]";
       }

}
