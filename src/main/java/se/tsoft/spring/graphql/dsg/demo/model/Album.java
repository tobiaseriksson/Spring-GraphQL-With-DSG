package se.tsoft.spring.graphql.dsg.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Album {
    private final String title;
    private final String artist;
    private final Integer recordNo;

}