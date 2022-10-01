package serviceDto;

import lombok.Data;

@Data
public class NotificationDto {
    String subject;
    String recipient;
    String body;
}
