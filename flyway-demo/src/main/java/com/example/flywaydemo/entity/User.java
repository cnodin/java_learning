package com.example.flywaydemo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 01/12/2017
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 100)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;
}
