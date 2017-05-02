package com.ezeon.capp.service;

import com.ezeon.capp.service.jdbc.ContactServiceImpl;
import com.ezeon.capp.service.jdbc.UserServiceImpl;

/**
 * Factory of Services; create an service class's instance
 */
public class ServiceFactory {
    public static UserService createUserService(){
        return  new UserServiceImpl();
    }
    public static ContactService createContactService(){        
        return  new ContactServiceImpl();
    }
    public static ExportImportService createExportImportService(){
        //TODO
        return  null;
    }    
}
