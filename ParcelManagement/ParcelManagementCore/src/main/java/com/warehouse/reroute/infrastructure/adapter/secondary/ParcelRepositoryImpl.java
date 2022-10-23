package com.warehouse.reroute.infrastructure.adapter.secondary;

import com.warehouse.reroute.domain.exception.ParcelNotFoundException;
import com.warehouse.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.reroute.domain.vo.ParcelResponse;
import com.warehouse.reroute.infrastructure.adapter.entity.ParcelEntity;
import com.warehouse.reroute.infrastructure.adapter.secondary.mapper.ParcelMapper;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ParcelRepositoryImpl implements ParcelRepository {

    private final ParcelMapper mapper;

    private final ParcelReadRepository parcelReadRepository;

    @Override
    public Optional<ParcelResponse> update(UpdateParcelRequest parcelRequest) {

        final Optional<ParcelEntity> parcelEntity = Optional.ofNullable(mapper.mapToParcelEntity(parcelRequest));

        parcelReadRepository.save(parcelEntity.get());

        return parcelEntity.map(mapper::mapFromParcelEntityToParcelResponse);
    }

    @Override
    public ParcelResponse loadByParcelId(Long parcelId) {

        final Optional<ParcelEntity> parcelEntity = parcelReadRepository.loadByParcelId(parcelId);

        return parcelEntity.map(mapper::mapFromParcelEntityToParcelResponse).orElseThrow(
                () -> new ParcelNotFoundException("Parcel was not found")
        );

    }

}
