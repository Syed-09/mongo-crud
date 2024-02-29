package org.example.model;

import lombok.*;

import javax.xml.namespace.QName;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String role;
    private boolean isEmployee;
}
