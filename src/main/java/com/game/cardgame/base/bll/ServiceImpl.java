package com.game.cardgame.base.bll;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;

import com.game.cardgame.base.bll.interfaces.Mapper;
import com.game.cardgame.base.bll.interfaces.Service;
import com.game.cardgame.base.dto.Dto;

public abstract class ServiceImpl<TEntity, TDto extends Dto> implements Service<TDto> {
    private Mapper<TEntity, TDto> mapper;
    private JpaRepository<TEntity, Long> dal;
    
    protected Mapper<TEntity, TDto> getMapper() { return mapper; }
    protected JpaRepository<TEntity, Long> getDal() { return dal; }

    public ServiceImpl(Mapper<TEntity, TDto> mapper, JpaRepository<TEntity, Long> dal) {
        this.mapper = mapper;
        this.dal = dal;
    }

    @Override
    public TDto create(TDto dto) {
        TEntity entity = mapper.mapToEntity(dto);
        return mapper.mapToDto(dal.save(entity));
    }

    @Override
    public void delete(Long id) {
        TEntity entity = dal.getOne(id);
        if (entity != null) {
            dal.delete(entity);
        }
    }

    @Override
    public TDto get(Long id) {
        TEntity entity = dal.getOne(id);
        if (entity != null) {
            return mapper.mapToDto(entity);
        } else {
            return null;
        }
    }

    @Override
    public List<TDto> getAll() {
        return dal.findAll()
            .stream()
            .map(entity -> mapper.mapToDto(entity))
            .collect(Collectors.toList());
    }

    @Override
    public void update(TDto dto) {
        TEntity entity = dal.getOne(dto.getId());
        if (entity != null) {
            updateEntityFromDto(entity, dto);
            dal.save(entity);
        }
    }

    protected abstract void updateEntityFromDto(TEntity entity, TDto dto);
}
