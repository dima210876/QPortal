package my.project.QPortal.service;

import my.project.QPortal.model.Questionnaire;
import my.project.QPortal.model.Response;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface QuestionnaireService
{
    Response saveAnswers(int questionnaire_id, Response response);

    Optional<Questionnaire> getById(int id);
}
