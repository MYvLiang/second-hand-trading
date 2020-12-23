package com.second.hand.trading.server.service.impl;

import com.second.hand.trading.server.dao.AddressDao;
import com.second.hand.trading.server.model.AddressModel;
import com.second.hand.trading.server.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author myl
 * @create 2020-12-21  8:52
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    public List<AddressModel> getAddressByUser(Long userId){
        return addressDao.getAddressByUser(userId);
    }

    public AddressModel getAddressById(Long id,Long userId){
        AddressModel addressModel=addressDao.selectByPrimaryKey(id);
        if(userId.equals(addressModel.getUserId())){
            return addressModel;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addAddress(AddressModel addressModel){
        if(addressModel.getDefaultFlag()){
            AddressModel a=new AddressModel();
            a.setDefaultFlag(false);
            a.setUserId(addressModel.getUserId());
            addressDao.updateByUserIdSelective(a);
        }else {
            List<AddressModel> list=addressDao.getDefaultAddress(addressModel.getUserId());
            if(null==list||0==list.size()){
                addressModel.setDefaultFlag(true);
            }
        }
        return addressDao.insert(addressModel)==1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateAddress(AddressModel addressModel){
        if(addressModel.getDefaultFlag()){
            AddressModel a=new AddressModel();
            a.setDefaultFlag(false);
            a.setUserId(addressModel.getUserId());
            addressDao.updateByUserIdSelective(a);
        }else{
            List<AddressModel> list=addressDao.getAddressByUser(addressModel.getUserId());
            for(AddressModel a:list){
                if(a.getDefaultFlag()&& a.getId().equals(addressModel.getId())){
                    AddressModel a1=new AddressModel();
                    a1.setId(list.get(0).getId());
                    a1.setDefaultFlag(true);
                    return addressDao.updateByPrimaryKeySelective(addressModel)==1&&
                            addressDao.updateByPrimaryKeySelective(a1)==1;
                }
            }
        }
        return addressDao.updateByPrimaryKeySelective(addressModel)==1;
    }

    public boolean deleteAddress(AddressModel addressModel){
        return addressDao.deleteByPrimaryKeyAndUser(addressModel.getId(),addressModel.getUserId())==1;
    }
}
