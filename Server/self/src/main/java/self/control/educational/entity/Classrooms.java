package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 教室信息
 */
@TableName(value = "classrooms")
public class Classrooms extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 记录ID
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 教室名称
	 */
	@TableField(value = "name")
	private String name;

	/**
	 * 教室位置
	 */
	@TableField(value = "loc")
	private String loc;

	/**
	 * 备注说明
	 */
	@TableField(value = "comm")
	private String comm;


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

	
     @Override
	public String toString() {
       
             return "Classrooms [id" + id 
                                             + ", name=" + name
                                            + ", loc=" + loc
                                            + ", comm=" + comm
                                   + "]";
       }

}
