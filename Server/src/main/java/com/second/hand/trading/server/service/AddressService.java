package com.second.hand.trading.server.service;

import com.second.hand.trading.server.model.AddressModel;

import java.util.List;

/**
 * @author myl
 * @create 2020-12-21  8:52
 */
public interface AddressService {

    List<AddressModel> getAddressByUser(Long userId);

    AddressModel getAddressById(Long id,Long userId);

    boolean addAddress(AddressModel addressModel);

    boolean updateAddress(AddressModel addressModel);

    boolean deleteAddress(AddressModel addressModel);
}
