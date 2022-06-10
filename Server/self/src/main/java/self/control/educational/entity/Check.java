package self.control.educational.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import onway.org.cn.comm.web.entity.BaseEntity;

/**
 * 数据实体类
 * 班级信息
 */
@TableName(value = "check")
public class Check extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;


    @TableField(value = "project_id")
    private String projectId;

    @TableField(value = "teacher_id")
    private String teacherId;


    @TableField(value = "stu_id")
    private String stuId;

    @TableField(value = "grades_id")
    private String gradesId;


    @TableField(value = "addtime")
    private String addTime;

    @TableField(value = "checktime")
    private String checkTime;

    @TableField(value = "isdelete")
    private Boolean isdelete;

    @TableField(value = "ischeck")
    private Boolean ischeck;


    public String getId(){


        return id;
    }

    public void setId(String id){

        this.id = id;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getGradesId() {
        return gradesId;
    }

    public void setGradesId(String gradesId) {
        this.gradesId = gradesId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public Boolean getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Boolean isdelete) {
        this.isdelete = isdelete;
    }

    public Boolean getIscheck() {
        return ischeck;
    }

    public void setIscheck(Boolean ischeck) {
        this.ischeck = ischeck;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id='" + id + '\'' +
                ", projectId='" + projectId + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", gradesId='" + gradesId + '\'' +
                ", addTime='" + addTime + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", isdelete=" + isdelete +
                ", ischeck=" + ischeck +
                '}';
    }
}
