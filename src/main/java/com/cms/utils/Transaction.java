package com.cms.utils;

import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 方法描述:事务回滚
 * <p>
 * author 小刘
 * version v1.0
 * date 2016/3/25 16:53
 */
public class Transaction {

    //定义手动事务
    public static TransactionStatus transcation(DataSourceTransactionManager txManager){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        return status;
    }

    //事务回滚
    public static void rollBack(){
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }
}
