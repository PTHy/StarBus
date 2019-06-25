package com.starbus.starbus.OpenResponse;

import lombok.Data;

@Data
public class Body<T> {
    private Item<T> items;
}
