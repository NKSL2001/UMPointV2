package my.edu.um.umpoint.modules.booking.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import java.math.BigDecimal;

/**
 * Space Booking
 *
 * @author Tan Chun Hong tanchunhong717@gmail.com
 * @since 1.0.0 2024-09-08
 */
@Data
@Schema(name = "Space Booking")
public class SvcBookingDTO implements Serializable {
	@Serial
    private static final long serialVersionUID = 1L;

	@SchemaProperty(name = "ID")
	private Long id;

	@SchemaProperty(name = "Status: 0:Pending, 1:Reject, 2:Approve(Pending Payment), 3:Approve(Payment Complete), 4:Cancel")
	private Integer status;

	@SchemaProperty(name = "Space ID")
	private Long serviceId;

	@SchemaProperty(name = "Admin that approve/reject, user will contact this admin rather manager if umpoint.service.booking.find-approve-admin-first=true")
	private Long adminId;

	@SchemaProperty(name = "User ID")
	private Long userId;

	@SchemaProperty(name = "Payment ID")
	private Long paymentId;

	@SchemaProperty(name = "Amount need to be pay")
	private BigDecimal paymentAmount;

	@SchemaProperty(name = "Create date")
	private Date createDate;

	@SchemaProperty(name = "Update date")
	private Date updateDate;
}
