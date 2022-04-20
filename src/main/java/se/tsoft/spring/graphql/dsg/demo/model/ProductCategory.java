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
public class ProductCategory {
    private int id;
    private String name;
    private List<Integer> products;
}
