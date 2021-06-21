package my.project.QPortal.repository;

import my.project.QPortal.model.ResponseField;
import my.project.QPortal.model.ResponseFieldId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseFieldRepository extends JpaRepository<ResponseField, ResponseFieldId> { }

