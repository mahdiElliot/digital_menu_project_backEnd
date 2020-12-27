package com.example.project.model.option;


import com.example.project.model.extra.Extra;
import com.example.project.model.menu.Menu;
import com.example.project.model.specproduct.SpecificProduct;
import com.example.project.model.suboptions.SubOption;
import com.example.project.model.suboptions.SubOptionDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(nullable = false)
    private Integer min;

    @Column(nullable = false)
    private Integer max;

    @Column(nullable = false)
    private Boolean enabled;

    private String image;

    @OneToMany(mappedBy = "option")
    private Set<SubOption> subOptions;

    @ManyToOne
    @JoinColumn(name = "extra_id", nullable = false)
    private Extra extra;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "sproduct_option",
            joinColumns = {@JoinColumn(name = "option_id")},
            inverseJoinColumns = {@JoinColumn(name = "sproduct_id")}
    )
    private Set<SpecificProduct> specificProducts;

    public Option() {
    }

    public Option(long id, String name, int min, int max, boolean enabled, String image, Extra extra) {
        this.id = id;
        this.name = name;
        this.min = min;
        this.max = max;
        this.enabled = enabled;
        this.image = image;
        this.extra = extra;
    }

    public OptionDTO convertToDTO() {
        Set<SubOptionDTO> subOptionDTOS = null;
        if (subOptions != null)
            subOptionDTOS = subOptions.stream().map(SubOption::convertToDTO).collect(Collectors.toSet());

        Long extraId = null;
        if (extra != null)
            extraId = extra.getId();
        return new OptionDTO(
                id,
                name,
                min,
                max,
                enabled,
                image,
                subOptionDTOS,
                extraId
        );
    }
}
