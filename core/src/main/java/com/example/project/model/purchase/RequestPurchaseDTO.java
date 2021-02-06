package com.example.project.model.purchase;


import com.example.project.model.option.RequestOptionDTO;
import com.example.project.model.order.Order;
import com.example.project.model.product.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class RequestPurchaseDTO {
    private long id;

    private String comment;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private Double price;

    @NotNull
    private Long product_id;

    private Set<RequestOptionDTO> options;

    public Purchase convertToPurchaseEntity(Product product, Order order) {
        Purchase purchase = new Purchase(id, comment, quantity, price, product, order);
        if (options != null && !options.isEmpty()) {
            List<Object> infos = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> info = new HashMap<>();
            String json = "";
            try {
                for (RequestOptionDTO option : options) {
                    info.put("option_id", option.getId());
                    info.put("suboptions", option.getSubOptions());
                    json = objectMapper.writeValueAsString(info);
                    infos.add(json);
                    info.clear();
                }
                info.put("options", infos);
                json = objectMapper.writeValueAsString(info);
                purchase.setJsonOptions(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return purchase;
    }
}

//{options: [{option_id: 1, suboptions: [2,3, 4]},{option_id: 2, suboptions: [3, 7 , 8]}]}