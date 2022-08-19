package com.example.cardealer.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Usersrole", schema = "dbo", catalog = "CarDealer")
public class UsersRole {
    private Long id;
    private Long roleId;
    private Long userId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Role_id")
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "User_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersRole usersRole = (UsersRole) o;
        return id == usersRole.id && Objects.equals(roleId, usersRole.roleId) && Objects.equals(userId, usersRole.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, userId);
    }
}
