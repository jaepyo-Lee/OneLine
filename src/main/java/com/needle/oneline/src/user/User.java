package com.needle.oneline.src.user;

import com.needle.oneline.src.common.BaseTimeEntity;
import com.needle.oneline.src.common.enumerate.RoleType;
import com.needle.oneline.src.common.enumerate.SnsType;
import com.needle.oneline.src.diary.Diary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialId;
    private String email;
    private String name;
    private String picture;

    @Enumerated(EnumType.STRING)
    private SnsType snsType;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries = new ArrayList<>();
}
