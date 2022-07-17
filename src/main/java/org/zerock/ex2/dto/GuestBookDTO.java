package org.zerock.ex2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.ex2.entity.Guestbook;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuestBookDTO {
    private Long gno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;

    public Guestbook toEntity() {
        return Guestbook.builder()
                .gno(this.gno)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .build();
    }
}
