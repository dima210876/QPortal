package my.project.QPortal.repository;

import my.project.QPortal.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Integer>
{
    @Query(value = "select * from fields f where f.questionnaire_id = ?1", nativeQuery = true)
    List<Field> findByQuestionnaire_id(int questionnaire_id);
}
