package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class MyUser
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique=true)
    private String email;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
    private List<MyRole> roles;

    public String getRolesWithFormattedString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (MyRole role : roles) {
            joiner.add(role.getRolename());
        }
        return joiner.toString();
    }
}