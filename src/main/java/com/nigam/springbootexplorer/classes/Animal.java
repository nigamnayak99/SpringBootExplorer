package com.nigam.springbootexplorer.classes;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    private boolean run = true;
    private boolean walks = true;
    private short numberOfEyes = 2;
}
