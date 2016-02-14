package com.tsystems.javaschool.services.enums;

import com.tsystems.javaschool.dao.entity.Order;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */

public enum OrderStatus {
   WAITING_FOR_PAYMENT,
   WAITING_FOR_SHIPMENT,
   SHIPPED,
   DELIVERED,
}
