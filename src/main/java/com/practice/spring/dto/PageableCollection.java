package com.practice.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableCollection<T> {
    private long totalElements;
    private int totalPages;
    private List<T> content;

}
