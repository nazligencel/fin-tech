package com.fintech.fin_tech.dto;

import com.fintech.fin_tech.util.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//Kategoriyi ve o kategorideki toplam tutarı tutacak
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySummaryDto {
    private CategoryType category;
    private BigDecimal totalAmount;
}
