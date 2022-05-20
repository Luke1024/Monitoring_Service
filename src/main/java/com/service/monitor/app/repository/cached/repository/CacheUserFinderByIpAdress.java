package com.service.monitor.app.repository.cached.repository;

import com.service.monitor.app.domain.AppUser;
import com.service.monitor.app.domain.IPAdress;
import com.service.monitor.app.repository.IPAdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CacheUserFinderByIpAdress {

    @Autowired
    private IPAdressRepository adressRepository;

    @Autowired
    private Cache cache;


    //this script assuming that there is only one user with unique ip adress without cookies
    public List<AppUser> findUserByIpAdressWithoutCookie(String ipAdress){

        List<AppUser> appUsersFromCache = removeUsersWithCookies(searchUsersInCacheByIpAdress(ipAdress));

        if(appUsersFromCache.size()>0){
            return appUsersFromCache;
        } else {
            return removeUsersWithCookies(searchUsersInDatabaseByIpAdress(ipAdress));
        }
    }

    private List<AppUser> searchUsersInCacheByIpAdress(String ipAdress){
        List<AppUser> appUsers = new ArrayList<>();
        for(AppUser user : cache.users){
            if(userUsedIpAdress(user,ipAdress)) {
                appUsers.add(user);
            }
        }
        return appUsers;
    }

    private boolean userUsedIpAdress(AppUser user, String ipAdress){
        for(IPAdress userAdress : user.getIpAdresses()){
            if(userAdress.equals(ipAdress)){
                return true;
            }
        }
        return false;
    }

    private List<AppUser> searchUsersInDatabaseByIpAdress(String ipAdress){
        List<AppUser> appUsers = new ArrayList<>();
        List<IPAdress> ipAdresses = adressRepository.findByIpAdress(ipAdress);
        for(IPAdress adress : ipAdresses){
            appUsers.add(adress.getAppUser());
        }
        return appUsers;
    }
}
