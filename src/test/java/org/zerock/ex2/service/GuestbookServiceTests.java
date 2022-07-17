package org.zerock.ex2.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.dto.GuestBookDTO;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.PageResponseDTO;
import org.zerock.ex2.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTests {
    @Autowired
    private GuestbookService guestbookService;

    @Test
    public void testRegister() throws Exception{
        //give
        GuestBookDTO dto = GuestBookDTO.builder()
                .title("sample")
                .content("sampleContent")
                .writer("sampleWriter")
                .build();
        //when

        //then
        System.out.println(guestbookService.register(dto));
    }

    @Test
    public void testList() throws Exception{
        //give
        PageRequestDTO request = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();
        //when
        PageResponseDTO<GuestBookDTO, Guestbook> resultDTO = guestbookService.getList(request);
        //then
        assertEquals(false,resultDTO.isPrev());
        assertEquals(true, resultDTO.isNext());
        assertEquals(31,resultDTO.getTotalPage());
        assertEquals(1,resultDTO.getStart());
        assertEquals(10, resultDTO.getEnd());

        for(int i=0;i<resultDTO.getPageList().size();i++){
            assertEquals(i+1,resultDTO.getPageList().get(i));
        }
    }
}
