package propagation.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import propagation.po.Student;

/**
 * StudentRepository
 *
 * @author zhangtao
 * @date 2017年08月18日
 * @reviewer
 * @see
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
