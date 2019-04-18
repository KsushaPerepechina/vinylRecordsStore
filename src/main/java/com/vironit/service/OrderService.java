package com.vironit.vinylRecordsStore.service;

import java.util.Date;
import java.util.List;

import com.vironit.vinylRecordsStore.entity.Account;
import com.vironit.vinylRecordsStore.entity.Order;
import com.vironit.vinylRecordsStore.dto.CreditCardDTO;
import com.vironit.vinylRecordsStore.dto.OrderDTO;
import com.vironit.vinylRecordsStore.exception.EmptyCartException;
import com.vironit.vinylRecordsStore.exception.OrderNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Сервис заказа.
 */
public interface OrderService {

    void save(Order order);
    
    void delete(Order order);

    Order findOne(long orderId);

    List<Order> findAll();

    Page<Order> findAll(PageRequest request);

    List<Order> findByUserAccount(Account account);

    Page<Order> findByExecuted(boolean stored, Pageable pageable);

    Page<Order> findByExecutedAndDateCreatedGreaterThan(boolean executed, Date created, Pageable pageable);

    Page<Order> findByDateCreatedGreaterThan(Date created, Pageable pageable);

    Page<Order> fetchFilteredAndPaged(String executed, String created, PageRequest request);
    
    //---------------------------------------- Операции с заказами пользователя
    
    /**
     * Оформление нового заказа.
     *
     * @param card данные банковской карты для оплаты заказа
     * @param userLogin логин покупателя
     * @param deliveryCost цена доставки
     * @return вновь созданный заказ
     * @throws EmptyCartException если оплачивается пустая корзина
     */
    Order createUserOrder(CreditCardDTO card, String userLogin, int deliveryCost) throws EmptyCartException;
    
    /**
     * Получение всех заказов покупателя.
     *
     * @param userLogin логин покупателя
     * @return список заказов
     */
    List<OrderDTO> getUserOrders(String userLogin);
    
    /**
     * Получение одного заказа покупателя.
     *
     * @param userLogin логин покупателя
     * @param id идентификатор заказа
     * @return заказ с запрошенным id
     * @throws OrderNotFoundException если у пользователя не существует запрошенного заказа
     */
    OrderDTO getUserOrder(String userLogin, long id) throws OrderNotFoundException;
}
