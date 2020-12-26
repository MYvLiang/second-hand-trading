package com.second.hand.trading.server.model;

import java.io.Serializable;

/**
 * sh_order_address
 * @author 
 */
public class OrderAddressModel implements Serializable {
    private Long id;

    private Long orderId;

    private String consigneeName;

    private String consigneePhone;

    private String detailAddress;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderAddressModel other = (OrderAddressModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getConsigneeName() == null ? other.getConsigneeName() == null : this.getConsigneeName().equals(other.getConsigneeName()))
            && (this.getConsigneePhone() == null ? other.getConsigneePhone() == null : this.getConsigneePhone().equals(other.getConsigneePhone()))
            && (this.getDetailAddress() == null ? other.getDetailAddress() == null : this.getDetailAddress().equals(other.getDetailAddress()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getConsigneeName() == null) ? 0 : getConsigneeName().hashCode());
        result = prime * result + ((getConsigneePhone() == null) ? 0 : getConsigneePhone().hashCode());
        result = prime * result + ((getDetailAddress() == null) ? 0 : getDetailAddress().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", consigneeName=").append(consigneeName);
        sb.append(", consigneePhone=").append(consigneePhone);
        sb.append(", detailAddress=").append(detailAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}