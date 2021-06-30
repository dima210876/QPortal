package my.project.QPortal.service.impl;

import my.project.QPortal.model.Questionnaire;
import my.project.QPortal.model.Response;
import my.project.QPortal.model.ResponseField;
import my.project.QPortal.repository.QuestionnaireRepository;
import my.project.QPortal.repository.ResponseFieldRepository;
import my.project.QPortal.repository.ResponseRepository;
import my.project.QPortal.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService
{
    private final QuestionnaireRepository questionnaireRepository;
    private final ResponseRepository responseRepository;
    private final ResponseFieldRepository responseFieldRepository;

    @Autowired
    public QuestionnaireServiceImpl(QuestionnaireRepository questionnaireRepository,
                                    ResponseRepository responseRepository,
                                    ResponseFieldRepository responseFieldRepository)
    {
        this.questionnaireRepository = questionnaireRepository;
        this.responseRepository = responseRepository;
        this.responseFieldRepository = responseFieldRepository;
    }

    public Response saveAnswers(int questionnaire_id, Response response)
    {
        response.setQuestionnaire_id(questionnaire_id);
        List<ResponseField> responseFields = response.getResponseFields();
        response.setResponseFields(new ArrayList<>());
        Response savedResponse = responseRepository.save(response);
        responseFields.forEach(responseField ->
        {
            responseField.setResponse_id(savedResponse.getId());
            responseFieldRepository.save(responseField);
        });
        savedResponse.setResponseFields(responseFields);
        return responseRepository.save(savedResponse);
    }

    public Optional<Questionnaire> getById(int id) { return questionnaireRepository.findById(id); }
}
