package my.edu.um.umpoint.modules.space.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Space Occupied Event
 *
 * @author Tan Chun Hong tanchunhong717@gmail.com
 * @since 1.0.0 2024-09-16
 */
@Data
@Schema(title = "Space Occupied Event")
public class SpcEventDTO implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;

	@Schema(title = "ID")
	private Long id;

	@Schema(title = "Space ID")
	private Long spaceId;

	@Schema(title = "Booking ID")
	private Long bookingId;

	@Schema(title = "Closure ID")
	private Long closureId;

	@Schema(title = "Type: 0:Booking, 1:Closure period")
	private Integer type;

	@Schema(title = "Event start time")
	private Date startTime;

	@Schema(title = "Event end time")
	private Date endTime;
}
