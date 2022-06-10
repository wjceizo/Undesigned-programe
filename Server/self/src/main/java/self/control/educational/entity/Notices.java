package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 系统通知
 */
@TableName(value = "notices")
public class Notices extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 通知标题
	 */
	@TableField(value = "title")
	private String title;

	/**
	 * 通知内容
	 */
	@TableField(value = "cont")
	private String cont;

	/**
	 * 开始时间
	 */
	@TableField(value = "start_time")
	private String startTime;

	/**
	 * 结束时间
	 */
	@TableField(value = "end_time")
	private String endTime;


	public String getId(){
	    
	   return id;
	}
	
	public void setId(String id){
	    
	   this.id = id;
	}


	public String getTitle(){
	    
	   return title;
	}
	
	public void setTitle(String title){
	    
	   this.title = title;
	}


	public String getCont(){
	    
	   return cont;
	}
	
	public void setCont(String cont){
	    
	   this.cont = cont;
	}


	public String getStartTime(){
	    
	   return startTime;
	}
	
	public void setStartTime(String startTime){
	    
	   this.startTime = startTime;
	}


	public String getEndTime(){
	    
	   return endTime;
	}
	
	public void setEndTime(String endTime){
	    
	   this.endTime = endTime;
	}

	
     @Override
	public String toString() {
       
             return "Notices [id" + id 
                                             + ", title=" + title
                                            + ", cont=" + cont
                                            + ", startTime=" + startTime
                                            + ", endTime=" + endTime
                                   + "]";
       }

}
