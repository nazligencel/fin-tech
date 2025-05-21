package com.fintech.fin_tech.model;

import com.fintech.fin_tech.util.CategoryType;
import com.fintech.fin_tech.util.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data// Getter, Setter, toString, equals, hashCode metodlarını otomatik oluşturur
@NoArgsConstructor // Parametresiz constructor
@AllArgsConstructor
@Entity // Bu sınıfın bir JPA entity'si olduğunu belirtir
@Table(name = "transactions")
public class Transaction {
    @Id //  alanın primary key olduğunu belirtir
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING) // Enum'ın veritabanında String olarak saklanmasını sağlar
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false, precision = 19, scale = 4) // BigDecimal için precision ve scale belirtilmeli
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryType category;

    @Column(length = 255)
    private String description;
}
