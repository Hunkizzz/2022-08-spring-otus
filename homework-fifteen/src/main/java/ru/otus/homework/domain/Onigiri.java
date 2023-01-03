package ru.otus.homework.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Onigiri {
    boolean bottomRiceLayer;
    boolean topRiceLayer;
    String filling;
    boolean creamCheese;

}
