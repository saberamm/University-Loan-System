package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import entity.HouseInfo;
import jakarta.persistence.EntityManager;
import repository.HouseInfoRepository;

public class HouseInfoRepositoryImpl extends BaseRepositoryImpl<HouseInfo, Long> implements HouseInfoRepository {
    public HouseInfoRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<HouseInfo> getEntityClass() {
        return HouseInfo.class;
    }
}
