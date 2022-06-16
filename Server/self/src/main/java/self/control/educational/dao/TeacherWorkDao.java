package self.control.educational.dao;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import self.control.educational.entity.Projects;
import self.control.educational.entity.TeacherWork;

import java.util.List;
import java.util.Map;

/**
 * 数据层处理接口
 * 授课安排
 */
@Repository("teacherWorkDao")
public interface TeacherWorkDao extends BaseMapper<TeacherWork> {

    /**
     * 获取没有设置班级的课程列表
     * @param gradeId 班级编号
     * @return
     */
    @Select("SELECT * FROM projects " +
            "WHERE id NOT IN " +
            " (SELECT project_id FROM teacher_work WHERE grade_id = #{gradeId}) ")
    public List<Projects> qryNoSetGrade(String gradeId);

    @Select("<script>" +
            "SELECT " +
            "g.id gradeId, g.name gradeName, " +
            "p.id projectId, p.name projectName, w.create_time, " +
            "t.id teacherId, (SELECT name FROM users WHERE id = t.id) teacherName " +
            "FROM grades g, projects p, teachers t, teacher_work w " +
            "WHERE g.id = w.grade_id AND p.id = w.project_id AND t.id = w.teacher_id " +
            "<if test='teacherId != null and teacherId.trim() != &quot;&quot;'>" +
            "AND t.id = #{teacherId} " +
            "</if>" +
            "<if test='gradeId != null and gradeId.trim() != &quot;&quot;'>" +
            "AND g.id = #{gradeId} " +
            "</if>" +
//            "<if test='teacherName != null and teacherName.trim() != &quot;&quot;'>" +
//            "AND teacherName LIKE CONCAT('%', #{teacherName}, '%') " +
//            "</if>" +
//            "<if test='projectName != null and projectName.trim() != &quot;&quot;'>" +
//            "AND projectName LIKE CONCAT('%', #{projectName}, '%') " +
//            "</if>" +
//            "<if test='gradeName != null and gradeName.trim() != &quot;&quot;'>" +
//            "AND gradeName LIKE CONCAT('%', #{gradeName}, '%') " +
//            "</if>" +
            "</script>")
    public Page<Map<String, Object>> qryPageWorkInfos(Page<Map<String, Object>> page,
                         @Param("teacherId")String teacherId,
                         @Param("gradeId")String gradeId
//                        ,@Param("projectName")String projectName,
//                         @Param("gradeName")String gradeName
                         );
}
