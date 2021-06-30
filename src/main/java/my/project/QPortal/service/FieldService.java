package my.project.QPortal.service;

import my.project.QPortal.model.Field;

import java.util.List;
import java.util.Optional;

public interface FieldService
{
    Optional <Field> getById(int id);

    Field addField(Field field);

    Field editField(int id, Field field);

    void delete(int id);

    List<Field> getAllByQuestionnaireId(int id);

    List<Field> getAll();
}
