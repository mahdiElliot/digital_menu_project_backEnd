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

    @Column(nullable = false)
    @Getter
    @Setter
    private Integer min;

    @Column(nullable = false)
    @Getter
    @Setter
    private Integer max;

    @Column(nullable = false)
    @Setter
    @Getter
    private Boolean enabled;

    @Setter
    @Getter
    private String image;

    @OneToMany(mappedBy = "option")
    @Setter
    @Getter
    private Set<SubOption> subOptions;

    @ManyToOne
    @JoinColumn(name = "extra_id", nullable = false)
    @Setter
    @Getter
    private Extra extra;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "sproduct_option",
            joinColumns = {@JoinColumn(name = "option_id")},
            inverseJoinColumns = {@JoinColumn(name = "sproduct_id")}
    )
    @Setter
    @Getter
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

        long extraId = 0;
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
