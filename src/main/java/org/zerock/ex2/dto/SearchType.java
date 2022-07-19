package org.zerock.ex2.dto;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.function.Function;

import static org.zerock.ex2.entity.QGuestbook.guestbook;

public enum SearchType {
    Title(s->guestbook.title.contains(s)),
    Content(s->guestbook.content.contains(s)),
    Writer(s->guestbook.writer.contains(s));

    private Function<String, BooleanExpression> expresssion;

    SearchType(Function<String, BooleanExpression> expresssion) {
        this.expresssion = expresssion;
    }

    public BooleanExpression getEq(String keyword) {
        return expresssion.apply(keyword);
    }
}
