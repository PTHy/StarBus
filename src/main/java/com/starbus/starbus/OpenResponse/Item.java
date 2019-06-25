package com.starbus.starbus.OpenResponse;

import lombok.Data;

import java.util.List;

@Data
public class Item<T> {
    private List<T> item;
}
