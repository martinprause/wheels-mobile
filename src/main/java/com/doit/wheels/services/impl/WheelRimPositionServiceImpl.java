package com.doit.wheels.services.impl;

import com.doit.wheels.dao.entities.WheelRimPosition;
import com.doit.wheels.dao.repositories.GenericRepository;
import com.doit.wheels.services.WheelRimPositionService;
import com.doit.wheels.utils.enums.StatusTypeEnum;
import org.springframework.stereotype.Service;

@Service
public class WheelRimPositionServiceImpl extends GenericServiceImpl<WheelRimPosition> implements WheelRimPositionService {
    public WheelRimPositionServiceImpl(GenericRepository<WheelRimPosition> genericRepository) {
        super(genericRepository);
    }

    @Override
    public WheelRimPosition updateStatus(Long id, String status) {
        WheelRimPosition wheelRimPosition = this.findById(id);
        wheelRimPosition.setStatus(StatusTypeEnum.valueOf(status));
        return this.update(wheelRimPosition);
    }
}
