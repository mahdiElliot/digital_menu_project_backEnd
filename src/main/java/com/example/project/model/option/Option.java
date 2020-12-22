package com.example.project.model.option;


import com.example.project.model.menu.Menu;
import com.example.project.model.suboptions.SubOption;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter
    @Getter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Integer min;

    @Getter
    @Setter
    private Integer max;

    @Setter
    @Getter
    private boolean enabled;

    @Setter
    @Getter
    private String image;

    @OneToMany(mappedBy = "option")
    @Setter
    @Getter
    private Set<SubOption> subOptions;

    public Option() {
    }

    public Option(Long id, String name, Integer min, Integer max, boolean enabled, String image, Set<SubOption> subOptions) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.enabled = enabled;
        this.image = image;
        this.subOptions = subOptions;
    }
}
