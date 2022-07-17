package org.zerock.ex2.entity;

import lombok.*;
import org.zerock.ex2.dto.GuestBookDTO;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;

    public void modifyTitle(String title){
        this.title=title;
    }

    public void modifyContent(String content){
        this.content=content;
    }

    public GuestBookDTO toDTO() {
        return GuestBookDTO.builder()
                .gno(this.gno)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .regDate(this.getRegDate())
                .modDate(this.getModDate())
                .build();
    }

}
