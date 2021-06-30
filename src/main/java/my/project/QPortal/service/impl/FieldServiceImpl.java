package my.project.QPortal.service.impl;

import my.project.QPortal.model.Field;
import my.project.QPortal.model.Option;
import my.project.QPortal.repository.FieldRepository;
import my.project.QPortal.repository.OptionRepository;
import my.project.QPortal.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService
{
    private final FieldRepository fieldRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public FieldServiceImpl(FieldRepository fieldRepository, OptionRepository optionRepository)
    {
        this.fieldRepository = fieldRepository;
        this.optionRepository = optionRepository;
    }

    public Optional<Field> getById(int id)
    {
        return fieldRepository.findById(id);
    }

    public Field addField(Field field)
    {
        List <Option> options = field.getOptions();
        List <Option> newOptions = new ArrayList<>();
        field.setOptions(newOptions);
        field = fieldRepository.save(field);
        int newFieldId = field.getId();
        options.forEach(option ->
        {
            option.setField_id(newFieldId);
            newOptions.add(optionRepository.save(option));
        });
        field.setOptions(newOptions);
        return fieldRepository.save(field);
    }

    public Field editField(int id, Field field)
    {
        Field currentField = fieldRepository.getById(id);
        currentField.setLabel(field.getLabel());
        currentField.setType(field.getType());
        currentField.setRequired(field.getRequired());
        currentField.setIsactive(field.getIsactive());
        if (!field.getOptions().isEmpty())
        {
            List <Option> previousOptions = currentField.getOptions();
            previousOptions.forEach(option -> optionRepository.delete(option));
            List <Option> newOptions = new ArrayList<>();
            field.getOptions().forEach(option ->
            {
                option.setField_id(id);
                newOptions.add(optionRepository.save(option));
            });
            currentField.setOptions(newOptions);
        }
        return currentField;
    }

    public void delete(int id)
    {
        Optional <Field> field = getById(id);
        if(field.isPresent())
        {
            List <Option> options = field.get().getOptions();
            options.forEach(option -> optionRepository.delete(option));
            fieldRepository.delete(field.get());
        }
        return;
    }

    public List<Field> getAllByQuestionnaireId(int questionnaireId)
    {
        return fieldRepository.findByQuestionnaire_id(questionnaireId);
    }

    public List<Field> getAll()
    {
        return fieldRepository.findAll();
    }
}
