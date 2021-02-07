package com.example.project.model.purchase;

import com.example.project.model.extra.Extra;
import com.example.project.model.option.Option;
import com.example.project.model.option.OptionDTO;
import com.example.project.model.order.Order;
import com.example.project.model.product.Product;
import com.example.project.model.suboptions.SubOption;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "purchase")
@TypeDef(typeClass = JsonBinaryType.class, name = "jsonb")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_generator")
    @SequenceGenerator(name = "purchase_generator", sequenceName = "purchase_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String comment;

    @Column(nullable = false)
    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String jsonOptions;

    public Purchase(long id, String name, String comment, int quantity, double price, Product product, Order order) {
        this.id = id;
        this.comment = comment;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.order = order;
        this.name = name;
    }

    public PurchaseDTO convertToDTO() {
        Set<OptionDTO> optionDTOS = new HashSet<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, List<String>> infos = objectMapper.readValue(jsonOptions, Map.class);
            Map<Long, Option> options = new HashMap<>();
            Map<Long, SubOption> subOptions = new HashMap<>();
            for (Extra extra : product.getExtras()) {
                options.putAll(extra.getOptions().stream()
                        .peek(it ->
                                subOptions.putAll(it.getSubOptions().stream().collect(Collectors.toMap(SubOption::getId, e -> e))))
                        .collect(Collectors.toMap(Option::getId, e -> e)));
            }
            for (String info : infos.get("options")) {
                Map<String, Object> jsons = objectMapper.readValue(info, Map.class);
                Integer i = (Integer) jsons.get("option_id");
                List<Integer> subs = (List<Integer>) jsons.get("suboptions");
                OptionDTO optionDTO = options.get((long) i).convertToDTO();
                for (Integer subId : subs)
                    optionDTO.getSubOptions().add(subOptions.get((long) subId).convertToDTO());
                optionDTOS.add(optionDTO);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new PurchaseDTO(id, product.getName(), comment, quantity, price, product.getId(), order.getId(), optionDTOS);
    }
}
