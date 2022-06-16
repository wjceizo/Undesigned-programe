package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 上课安排
 */
@TableName(value = "project_plans")
public class ProjectPlans extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 工作ID
	 */
	@TableField(value = "work_id")
	private String workId;

	/**
	 * 教室ID
	 */
	@TableField(value = "room_id")
	private String roomId;

	/**
	 * 上课时间
	 */
	@TableField(value = "create_time")
	private String createTime;


	public String getId(){
	    
	   return id;
	}
	
	public void setId(String id){
	    
	   this.id = id;
	}


	public String getWorkId(){
	    
	   return workId;
	}
	
	public void setWorkId(String workId){
	    
	   this.workId = workId;
	}


	public String getRoomId(){
	    
	   return roomId;
	}
	
	public void setRoomId(String roomId){
	    
	   this.roomId = roomId;
	}


	public String getCreateTime(){
	    
	   return createTime;
	}
	
	public void setCreateTime(String createTime){
	    
	   this.createTime = createTime;
	}

	
     @Override
	public String toString() {
       
             return "ProjectPlans [id" + id 
                                             + ", workId=" + workId
                                            + ", roomId=" + roomId
                                            + ", createTime=" + createTime
                                   + "]";
       }

}
