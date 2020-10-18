package propagation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import propagation.po.Teacher;

/**
 * TeacherRepository
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
