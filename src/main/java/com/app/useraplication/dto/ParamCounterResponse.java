package com.app.useraplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParamCounterResponse {

    private Integer responseCode;
    private String description;
    private RegisterCountDto result;
    private Long elapsedTime;
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setResult(RegisterCountDto result) {
        this.result = result;
    }
    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
    
    
    
    

}
