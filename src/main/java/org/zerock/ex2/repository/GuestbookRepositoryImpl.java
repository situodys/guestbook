package org.zerock.ex2.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.SearchType;
import org.zerock.ex2.entity.Guestbook;

import java.util.List;

import static org.zerock.ex2.entity.QGuestbook.guestbook;

@RequiredArgsConstructor
public class GuestbookRepositoryImpl implements GuestbookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Guestbook> findByWriter(String writer) {
        return jpaQueryFactory
                .selectFrom(guestbook)
                .where(guestbook.writer.eq(writer))
                .fetch();
    }

    /*
    pageRequestDTO의 type,keyword에 따라
    동적인 쿼리를 처리하는게 목표
    type 종류 3가지: title,content,writer
    keyword 종류 2가지 : 있거나 null이거나.
     */
    @Override
    public Page<Guestbook> searchAll(PageRequestDTO pageRequestDTO, Pageable pageable) {
        /*
        구현하고자하는 기능
        pageRequestDto에 전달된 searchType(혹은 list)에서
        해당 column(들)에 대해 keyword를 포함한 데이터 가져오기
         */

        List<Guestbook> guestbooks = jpaQueryFactory
                .selectFrom(guestbook)
                .where(
                        makeWhereCondition(pageRequestDTO, pageRequestDTO.getKeyword())
                )
                .fetch();
        return new PageImpl<>(guestbooks,pageable,guestbooks.size());
    }

    private BooleanBuilder makeWhereCondition(PageRequestDTO pageRequestDTO,String keyWord){
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        List<SearchType> types = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        for (SearchType type : types) {
            booleanBuilder.or(type.getEq(keyword));
        }

        return booleanBuilder;
    }
}
