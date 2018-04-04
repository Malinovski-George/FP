package com.malinovski.helpdesk.util.impl;

import com.malinovski.helpdesk.dto.FormFieldForFormExtenderDto;
import com.malinovski.helpdesk.model.Category;
import com.malinovski.helpdesk.model.Urgency;
import com.malinovski.helpdesk.service.CategoryService;
import com.malinovski.helpdesk.util.FormExtender;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public final class FormExtenderImpl implements FormExtender {

    private CategoryService categoryService;

    public FormExtenderImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public FormFieldForFormExtenderDto getFormFieldDto() {
        FormFieldForFormExtenderDto formFieldDto = new FormFieldForFormExtenderDto();
        List categories = new LinkedList();
        List urgencies = new LinkedList();

        for (Category category : categoryService.getCategories()) {
            categories.add(category.getName());
        }
        formFieldDto.setCategory(categories);

        for (Urgency urgency:Arrays.asList(Urgency.values())){
            urgencies.add(urgency.toString());
        }
        formFieldDto.setUrgency(urgencies);
        return formFieldDto;
    }
}
