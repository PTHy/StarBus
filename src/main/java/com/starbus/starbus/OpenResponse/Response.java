package com.starbus.starbus.OpenResponse;
import lombok.Data;

@Data
public class Response <T>{
    private Header header;
    private Body<T> body;
}
