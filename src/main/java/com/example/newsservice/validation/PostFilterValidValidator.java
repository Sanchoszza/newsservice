package com.example.newsservice.validation;

import com.example.newsservice.web.model.post.PostFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class PostFilterValidValidator implements ConstraintValidator<PostFilterValid, PostFilter> {
    @Override
    public boolean isValid(PostFilter value, ConstraintValidatorContext context) {
        if (ObjectUtils.anyNull(value.getPageNumber(), value.getPageSize())){
            return false;
        }

        return true;
    }
}
