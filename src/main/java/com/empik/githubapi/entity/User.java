package com.empik.githubapi.entity;

//FIXME star import is not allowedimport lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String login;
    private int requestCount;

    public void incrementRequestCount() {
        this.requestCount++;
    }

}
