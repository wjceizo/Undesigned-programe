package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 活动信息
 */
@TableName(value = "activitys")
public class Activitys extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 活动标题
	 */
	@TableField(value = "title")
	private String title;

	/**
	 * 活动详情
	 */
	@TableField(value = "intro")
	private String intro;

	/**
	 * 活动地点
	 */
	@TableField(value = "loc")
	private String loc;

	/**
	 * 活动要求
	 */
	@TableField(value = "comm")
	private String comm;

	/**
	 * 活动时间
	 */
	@TableField(value = "create_time")
	private String createTime;


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


	public String getIntro(){
	    
	   return intro;
	}
	
	public void setIntro(String intro){
	    
	   this.intro = intro;
	}


	public String getLoc(){
	    
	   return loc;
	}
	
	public void setLoc(String loc){
	    
	   this.loc = loc;
	}


	public String getComm(){
	    
	   return comm;
	}
	
	public void setComm(String comm){
	    
	   this.comm = comm;
	}


	public String getCreateTime(){
	    
	   return createTime;
	}
	
	public void setCreateTime(String createTime){
	    
	   this.createTime = createTime;
	}

	
     @Override
	public String toString() {
       
             return "Activitys [id" + id 
                                             + ", title=" + title
                                            + ", intro=" + intro
                                            + ", loc=" + loc
                                            + ", comm=" + comm
                                            + ", createTime=" + createTime
                                   + "]";
       }

}
