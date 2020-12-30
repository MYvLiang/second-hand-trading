package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.AddressModel;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-21  8:52
 */
public interface AddressService {
    /**
     * 获取一个用户的所有地址信息
     * @param userId
     * @return
     */
    List<AddressModel> getAddressByUser(Long userId);

    /**
     * 获取单个地址的信息
     * @param id
     * @param userId
     * @return
     */
    AddressModel getAddressById(Long id,Long userId);

    /**
     * 新增地址信息
     * @param addressModel
     * @return
     */
    boolean addAddress(AddressModel addressModel);

    /**
     * 修改地址信息
     * @param addressModel
     * @return
     */
    boolean updateAddress(AddressModel addressModel);

    /**
     * 删除地址信息
     * @param addressModel
     * @return
     */
    boolean deleteAddress(AddressModel addressModel);
}
