package com.needle.oneline.src.diary;

import com.needle.oneline.src.common.BaseTimeEntity;
import com.needle.oneline.src.diary.dto.request.UpdateContentsRequestDto;
import com.needle.oneline.src.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "DIARY_TABLE")
public class Diary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public void update(UpdateContentsRequestDto requestDto){
        this.contents = requestDto.getContents();
    }
}
