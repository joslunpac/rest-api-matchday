package com.matchday.api.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.matchday.api.model.enumerate.RoleEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Role extends BaseEntity {

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 10)
    private RoleEnum name;

}
