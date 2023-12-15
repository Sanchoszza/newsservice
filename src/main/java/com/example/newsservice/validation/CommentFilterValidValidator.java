package com.example.newsservice.validation;

import com.example.newsservice.web.model.comment.CommentFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class CommentFilterValidValidator implements ConstraintValidator<CommentFilterValid, CommentFilter> {
    @Override
    public boolean isValid(CommentFilter value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getPageNumber(), value.getPageSize())){
            return false;
        }

        return true;
    }
}
