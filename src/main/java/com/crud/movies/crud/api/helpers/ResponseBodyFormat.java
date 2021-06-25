package com.crud.movies.crud.api.helpers;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbNillable;

import lombok.Data;

@Data
@JsonbNillable(value = true)
public class ResponseBodyFormat {

    private Object data = null;
    private List<String> errors = new ArrayList<String>();

    public void addError(String error) {
        errors.add(error);
    }

}
