package com.game.cardgame.base.bll.interfaces;

import java.util.List;

import com.game.cardgame.base.dto.Dto;

public interface Service<TDto extends Dto> {
    TDto create(TDto dto);
    void delete(Long id);
    TDto get(Long id);
    List<TDto> getAll();
    void update(TDto dto);
}
