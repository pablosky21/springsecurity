package com.app.useraplication.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCountDto {

    private List<ItemsDto> items;
    private Integer registerCount;
    public void setItems(List<ItemsDto> items) {
        this.items = items;
    }
    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }
    
    
}
