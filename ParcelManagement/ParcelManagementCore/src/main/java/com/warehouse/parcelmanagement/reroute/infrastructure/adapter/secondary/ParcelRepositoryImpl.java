package com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary;

import com.warehouse.parcelmanagement.reroute.domain.model.UpdateParcelRequest;
import com.warehouse.parcelmanagement.reroute.domain.port.secondary.ParcelRepository;
import com.warehouse.parcelmanagement.reroute.domain.vo.ParcelResponse;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.entity.ParcelEntity;
import com.warehouse.parcelmanagement.reroute.infrastructure.adapter.secondary.mapper.ParcelMapper;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class ParcelRepositoryImpl implements ParcelRepository {

    private final ParcelMapper parcelMapper;

    private final ParcelReadRepository parcelReadRepository;

    @Override
    public ParcelResponse update(UpdateParcelRequest parcelRequest) {
        final ParcelEntity parcelEntity = parcelEntity(parcelRequest);
        return parcelMapper
                .mapFromParcelEntityToParcelResponse(
                        parcelReadRepository.save(parcelEntity));
    }

    @Override
    public Optional<ParcelResponse> loadByParcelId(Long parcelId) {
        return Optional.ofNullable(parcelMapper
                .mapFromParcelEntityToParcelResponse(
                        parcelReadRepository.loadByParcelId(parcelId)));
    }


    public ParcelEntity parcelEntity(UpdateParcelRequest updateParcelRequest) {
        return parcelMapper.mapToParcelEntity(updateParcelRequest);
    }
}
