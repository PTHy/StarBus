package com.starbus.starbus.OpenResponse;

import lombok.Data;

@Data
public class OpenModel<T> {
    private Response<T> response;
}
