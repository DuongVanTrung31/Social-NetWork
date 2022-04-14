package com.codegym.backendjavasocialnetwork.entity.dto;

import com.codegym.backendjavasocialnetwork.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostStatusRequest {
    private List<Status> statusInput;
}
