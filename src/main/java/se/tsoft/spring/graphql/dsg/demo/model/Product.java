package se.tsoft.spring.graphql.dsg.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product {
    private int id;
    private String name;
    private String description;
    private List<Integer> categories;
    private List<Integer> offerings;
}
