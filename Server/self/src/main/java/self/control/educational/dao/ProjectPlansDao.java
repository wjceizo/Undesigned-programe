package self.control.educational.dao;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.ProjectPlans;

import java.util.Map;

/**
 * 数据层处理接口
 * 上课安排
 */
@Repository("projectPlansDao")
public interface ProjectPlansDao extends BaseMapper<ProjectPlans> {

    /**
     * 查询在指定时间内，安排的课程书面
     * @param planTime 排课时间
     * @return
     */
    @Select("SELECT count(*) FROM project_plans " +
            "WHERE work_id = #{workId} AND #{planTime} BETWEEN create_time AND " +
            "date_add(create_time, interval 50 minute)")
    public Integer qryTotalInTimeRang(@Param("planTime") String planTime, @Param("workId")String workId);

    /**
     * 查看上课安排详情
     * @param page 分页信息
     * @param gradeId 班级编号
     * @param projectId 班级编号
     * @param teacherId 教师编号
     * @return
     */
    @Select("<script>" +
            "SELECT " +
            "p.id id, p.work_id workId, p.create_time createTime, " +
            "c.id roomId, c.name roomName, c.loc roomLoc, " +
            "(SELECT name FROM projects WHERE id = w.project_id) projectName, " +
            "(SELECT name FROM grades WHERE id = w.grade_id) gradeName, " +
            "(SELECT name FROM users WHERE id = w.teacher_id) teacherName " +
            "FROM teacher_work w, project_plans p, classrooms c " +
            "WHERE w.id = p.work_id AND p.room_id = c.id " +
            "<if test='gradeId != null and gradeId.trim() != &quot;&quot;'>" +
            "AND w.grade_id = #{gradeId} " +
            "</if>" +
            "<if test='teacherId != null and teacherId.trim() != &quot;&quot;'>" +
            "AND w.teacher_id = #{teacherId} " +
            "</if>" +
            "<if test='projectId != null and projectId.trim() != &quot;&quot;'>" +
            "AND w.project_id = #{projectId} " +
            "</if>" +
            "ORDER BY p.create_time DESC " +
            "</script>")
    public Page<Map<String, Object>>
        qryPageInfos(Page<Map<String, Object>> page,
                     @Param("gradeId")String gradeId,
                     @Param("teacherId")String teacherId,
                     @Param("projectId")String projectId);
}
