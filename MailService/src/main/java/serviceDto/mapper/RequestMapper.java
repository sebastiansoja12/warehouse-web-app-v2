package serviceDto.mapper;

import com.warehouse.mail.domain.vo.Notification;
import serviceDto.NotificationDto;
import org.mapstruct.Mapper;

@Mapper
public interface RequestMapper {
    Notification map(NotificationDto notification);

}
