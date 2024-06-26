package art.deerborg.accounting.v1.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Integer quantity;
    private Double totalAmount;
    private Double netAmount;
    private Double taxAmount;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Product product;
}
