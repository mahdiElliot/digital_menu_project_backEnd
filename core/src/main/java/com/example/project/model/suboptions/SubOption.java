package com.example.project.model.suboptions;

import com.example.project.model.option.Option;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "suboptions")

public class SubOption {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Boolean enabled;

    private String image;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    public SubOption() {
    }

    public SubOption(long id, int price, String name, String description, boolean enabled, String image, Option option) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.image = image;
        this.option = option;
    }

    public SubOptionDTO convertToDTO() {
        Long optionId = null;
        if (option != null)
            optionId = option.getId();
        return new SubOptionDTO(
                id,
                price,
                name,
                description,
                enabled,
                image,
                optionId
        );
    }
}
