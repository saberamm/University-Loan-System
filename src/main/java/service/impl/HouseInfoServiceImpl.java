package service.impl;

import base.service.impl.BaseServiceImpl;
import entity.HouseInfo;
import repository.HouseInfoRepository;
import service.HouseInfoService;

public class HouseInfoServiceImpl extends BaseServiceImpl<HouseInfo, Long, HouseInfoRepository> implements HouseInfoService {
    public HouseInfoServiceImpl(HouseInfoRepository repository) {
        super(repository);
    }
}
