package com.example.project.model.suboptions;

import com.example.project.model.business.Business;
import com.example.project.model.option.Option;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "suboptions")

public class SubOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private Integer price;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private boolean enabled;

    @Setter
    @Getter
    private String image;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    @Setter
    @Getter
    private Option option;


}
