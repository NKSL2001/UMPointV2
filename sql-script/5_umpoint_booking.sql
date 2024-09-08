CREATE TABLE spc_payment (
    id bigint NOT NULL COMMENT 'ID',
    status tinyint NOT NULL COMMENT 'Status: 0:Pending, 1:Success, 2:Failed, 3:Refund',
    user_id bigint NOT NULL COMMENT 'User ID',
    amount decimal(10,2) NOT NULL COMMENT 'Payment Amount',
    method varchar(50) NOT NULL COMMENT 'Payment Method',
    date datetime NOT NULL COMMENT 'Payment date',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Payment';

CREATE TABLE spc_booking (
    id bigint NOT NULL COMMENT 'ID',
    status tinyint NOT NULL COMMENT 'Status: 0:Pending, 1:Reject, 2:Approve(Pending Payment), 3:Approve(Payment Complete), 4:Cancel',
    space_id bigint NOT NULL COMMENT 'Space ID',
    admin_id bigint NULL COMMENT 'Admin that approve/reject, user will contact this admin rather manager if umpoint.booking.space.find-approve-admin-first=true',
    user_id bigint NOT NULL COMMENT 'User ID',
    worker_id bigint NULL COMMENT 'Worker responsible if booking is not in working day',
    payment_id bigint NOT NULL COMMENT 'Payment ID',
    payment_amount decimal(10,2) NOT NULL COMMENT 'Amount need to be pay',
    start_day date NOT NULL COMMENT 'Start day of booking',
    end_day date NOT NULL COMMENT 'End day of booking',
    start_time time NOT NULL COMMENT 'Start time of booking in a day',
    end_time time NOT NULL COMMENT 'End time of booking in a day',
    create_date datetime NOT NULL COMMENT 'Create date',
    update_date datetime NOT NULL COMMENT 'Update date',
    PRIMARY KEY (id),
    FOREIGN KEY (space_id) REFERENCES spc_space(id),
    FOREIGN KEY (admin_id) REFERENCES sys_user(id),
    FOREIGN KEY (user_id) REFERENCES cli_user(id),
    FOREIGN KEY (worker_id) REFERENCES sys_user(id),
    FOREIGN KEY (payment_id) REFERENCES spc_payment(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Space Booking';

CREATE TABLE svc_payment (
    id bigint NOT NULL COMMENT 'ID',
    status tinyint NOT NULL COMMENT 'Status: 0:Pending, 1:Success, 2:Failed, 3:Refund',
    user_id bigint NOT NULL COMMENT 'User ID',
    amount decimal(10,2) NOT NULL COMMENT 'Payment Amount',
    method varchar(50) NOT NULL COMMENT 'Payment Method',
    date datetime NOT NULL COMMENT 'Payment date',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Payment';

CREATE TABLE svc_booking (
    id bigint NOT NULL COMMENT 'ID',
    status tinyint NOT NULL COMMENT 'Status: 0:Pending, 1:Reject, 2:Approve(Pending Payment), 3:Approve(Payment Complete), 4:Cancel',
    service_id bigint NOT NULL COMMENT 'Space ID',
    admin_id bigint NULL COMMENT 'Admin that approve/reject, user will contact this admin rather manager if umpoint.service.booking.find-approve-admin-first=true',
    user_id bigint NOT NULL COMMENT 'User ID',
    payment_id bigint NOT NULL COMMENT 'Payment ID',
    payment_amount decimal(10,2) NOT NULL COMMENT 'Amount need to be pay',
    create_date datetime NOT NULL COMMENT 'Create date',
    update_date datetime NOT NULL COMMENT 'Update date',
    PRIMARY KEY (id),
    FOREIGN KEY (service_id) REFERENCES svc_service(id),
    FOREIGN KEY (admin_id) REFERENCES sys_user(id),
    FOREIGN KEY (user_id) REFERENCES cli_user(id),
    FOREIGN KEY (payment_id) REFERENCES svc_payment(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Space Booking';


CREATE TABLE acc_payment (
    id bigint NOT NULL COMMENT 'ID',
    status tinyint NOT NULL COMMENT 'Status: 0:Pending, 1:Success, 2:Failed, 3:Refund',
    user_id bigint NOT NULL COMMENT 'User ID',
    amount decimal(10,2) NOT NULL COMMENT 'Payment Amount',
    method varchar(50) NOT NULL COMMENT 'Payment Method',
    date datetime NOT NULL COMMENT 'Payment date',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Payment';

CREATE TABLE acc_booking (
    id bigint NOT NULL COMMENT 'ID',
    status tinyint NOT NULL COMMENT 'Status: 0:Pending, 1:Reject, 2:Approve(Pending Payment), 3:Approve(Payment Complete), 4:Cancel',
    accommodation_id bigint NOT NULL COMMENT 'Accommodation ID',
    admin_id bigint NULL COMMENT 'Admin that approve/reject, user will contact this admin rather manager if umpoint.booking.accommodation.find-approve-admin-first=true',
    user_id bigint NOT NULL COMMENT 'User ID',
    worker_id bigint NULL COMMENT 'Worker responsible if booking is not in working day',
    payment_id bigint NOT NULL COMMENT 'Payment ID',
    payment_amount decimal(10,2) NOT NULL COMMENT 'Amount need to be pay',
    start_day date NOT NULL COMMENT 'Start day of booking',
    end_day date NOT NULL COMMENT 'End day of booking',
    create_date datetime NOT NULL COMMENT 'Create date',
    update_date datetime NOT NULL COMMENT 'Update date',
    PRIMARY KEY (id),
    FOREIGN KEY (accommodation_id) REFERENCES acc_accommodation(id),
    FOREIGN KEY (admin_id) REFERENCES sys_user(id),
    FOREIGN KEY (user_id) REFERENCES cli_user(id),
    FOREIGN KEY (worker_id) REFERENCES sys_user(id),
    FOREIGN KEY (payment_id) REFERENCES acc_payment(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Accommodation Booking';