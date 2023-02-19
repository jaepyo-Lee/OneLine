package com.needle.oneline.src.user;

import com.needle.oneline.src.common.BaseTimeEntity;
import com.needle.oneline.src.diary.Diary;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "USER_TABLE")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String profile;

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries = new ArrayList<>();

    @Builder
    public User(String email,String name,String profile) {
        this.email = email;
        this.name = name;
        this.profile = profile;
    }
}
