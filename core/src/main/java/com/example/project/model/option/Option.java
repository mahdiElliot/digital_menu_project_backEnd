package com.example.project.model.option;


import com.example.project.model.extra.Extra;
import com.example.project.model.specproduct.Purchase;
import com.example.project.model.suboptions.SubOption;
import com.example.project.model.suboptions.SubOptionDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "option")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "option_generator")
    @SequenceGenerator(name = "option_generator", sequenceName = "option_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Column(nullable = false)
    private Integer min;

    @Column(nullable = false)
    private Integer max;

    @Column(nullable = false)
    private Boolean enabled;

    private String image;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    private Set<SubOption> subOptions = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "extra_id", nullable = false)
    private Extra extra;

    @ManyToMany(mappedBy = "options", cascade = CascadeType.ALL)
    private Set<Purchase> purchases = new HashSet<>();

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

        return new OptionDTO(id, name, min, max, enabled, image, subOptionDTOS, extraId);
    }
}
