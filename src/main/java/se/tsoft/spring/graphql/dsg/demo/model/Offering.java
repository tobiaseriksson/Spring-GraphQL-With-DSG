package se.tsoft.spring.graphql.dsg.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Offering {
    private Integer id;
    private Integer price;
    private String name;
    private Integer product;
}
