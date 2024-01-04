package fpt.mooc.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    @Column(name = "id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "equity")
    private Integer equity;

    @Column(name = "debt")
    private Integer debt;

    public Company(String name, String address, Integer equity, Integer debt) {
        this.name = name;
        this.address = address;
        this.equity = equity;
        this.debt = debt;
    }
}
