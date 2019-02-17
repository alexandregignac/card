package com.game.cardgame.base.bll;

import java.util.function.Supplier;

import com.game.cardgame.base.bll.interfaces.Mapper;
import com.game.cardgame.base.dto.Dto;

import org.springframework.beans.BeanUtils;

public abstract class MapperImpl<TEntity, TDto extends Dto> implements Mapper<TEntity, TDto> {

	private Supplier<TDto> dtoSupplier;
	private Supplier<TEntity> entitySupplier;
	
	public MapperImpl(Supplier<TEntity> entitySupplier, Supplier<TDto> dtoSupplier) {
		this.dtoSupplier = dtoSupplier;
		this.entitySupplier = entitySupplier;
	}
	
	@Override
	public TDto mapToDto(TEntity entity) {
        TDto dto = createDto();
        BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public TEntity mapToEntity(TDto dto) {
        TEntity entity = createEntity();
        BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	protected TDto createDto() {
		return dtoSupplier.get();
	}
    
	protected TEntity createEntity() {
		return entitySupplier.get();
	}
}
