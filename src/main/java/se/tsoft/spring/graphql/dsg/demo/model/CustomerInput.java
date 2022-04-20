package se.tsoft.spring.graphql.dsg.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerInput {
    private Integer id;
    private String firstName;
    private String lastName;
// private Integer homeAddress
}
