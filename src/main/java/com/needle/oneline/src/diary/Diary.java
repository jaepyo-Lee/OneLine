package com.needle.oneline.src.diary;

import com.needle.oneline.src.common.BaseTimeEntity;
import com.needle.oneline.src.diary.dto.request.SaveContentsRequestDto;
import com.needle.oneline.src.user.User;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DIARY_TABLE")
public class Diary extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String contents;

    private String lengthFlag;

    
    private LocalDate diaryDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Diary(User userById,String contents,String lengthFlag,LocalDate localDate){
        this.contents = contents;
        this.user = userById;
        this.lengthFlag = lengthFlag;
        this.diaryDate = localDate;
    }

    public void update(String contents,String lengthFlag){
        this.contents = contents;
        this.lengthFlag = lengthFlag;
    }

    public Diary toEntity(User user, SaveContentsRequestDto requestDto){
        return Diary.builder()
                .contents(requestDto.getContents())
                .user(user)
                .build();
    }
}
