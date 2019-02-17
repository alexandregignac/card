package com.game.cardgame.base.bll.interfaces;

import com.game.cardgame.base.dto.Dto;

public interface Mapper<TEntity, TDto extends Dto> {
    TDto mapToDto(TEntity entity);
    TEntity mapToEntity(TDto dto);
}
