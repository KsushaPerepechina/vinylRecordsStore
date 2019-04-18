package com.vironit.vinylRecordsStore.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.vironit.vinylRecordsStore.dao.OrderDAO;
import com.vironit.vinylRecordsStore.dto.CreditCardDTO;
import com.vironit.vinylRecordsStore.dto.OrderDTO;
import com.vironit.vinylRecordsStore.dto.assembler.OrderDtoAssembler;
import com.vironit.vinylRecordsStore.entity.*;
import com.vironit.vinylRecordsStore.service.CartService;
import com.vironit.vinylRecordsStore.service.OrderService;
import com.vironit.vinylRecordsStore.dao.UserAccountDAO;
import com.vironit.vinylRecordsStore.entity.Account;
import com.vironit.vinylRecordsStore.exception.EmptyCartException;
import com.vironit.vinylRecordsStore.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса заказа.
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    private final OrderDAO orderDAO;
    private final UserAccountDAO userAccountDAO;
    private final CartService cartService;
    private final OrderDtoAssembler orderDtoAssembler;
    
    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, UserAccountDAO userAccountDAO, CartService cartService, OrderDtoAssembler orderDtoAssembler) {
        this.orderDAO = orderDAO;
        this.userAccountDAO = userAccountDAO;
        this.cartService = cartService;
        this.orderDtoAssembler = orderDtoAssembler;
    }

    @Transactional
    @Override
    public void save(Order order) {
        orderDAO.save(order);
    }

    @Transactional
    @Override
    public void delete(Order order) {
        orderDAO.delete(order);
    }

    @Transactional(readOnly = true)
    @Override
    public Order findOne(long orderId) {
        return orderDAO.findOne(orderId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> findAll(PageRequest request) {
        return orderDAO.findAll(request);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> findByUserAccount(Account account) {
        return orderDAO.findByUserAccountOrderByDateCreatedDesc(account);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> findByExecuted(boolean stored, Pageable pageable) {
        return orderDAO.findByExecuted(stored, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> findByExecutedAndDateCreatedGreaterThan(boolean executed, Date created, Pageable pageable) {
        return orderDAO.findByExecutedAndDateCreatedGreaterThan(executed, created, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> findByDateCreatedGreaterThan(Date created, Pageable pageable) {
        return orderDAO.findByDateCreatedGreaterThan(created, pageable);
    }
    
    //---------------------------------------- Операции с заказами пользователя
    
    @Transactional(readOnly = true)
    @Override
    public Page<Order> fetchFilteredAndPaged(String executed, String created, PageRequest request) {
        Page<Order> pagedList;
        Date dateCreated = new Date();
        if (!created.equals("all")) {
            int days = Integer.valueOf(created);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.HOUR_OF_DAY, -(days * 24));
            dateCreated = c.getTime();
        }
        if (!executed.equals("all") && !created.equals("all")) {
            boolean executedState = Boolean.valueOf(executed);
            pagedList = findByExecutedAndDateCreatedGreaterThan(executedState, dateCreated, request);
        } else if (!executed.equals("all")) {
            boolean executedState = Boolean.valueOf(executed);
            pagedList = findByExecuted(executedState, request);
        } else if (!created.equals("all")) {
            pagedList = findByDateCreatedGreaterThan(dateCreated, request);
        } else {
            pagedList = findAll(request);
        }
        return pagedList;
    }
    
    @Transactional
    @Override
    public Order createUserOrder(CreditCardDTO card, String userLogin, int deliveryCost) throws EmptyCartException {
        Cart cart = cartService.getUserCart(userLogin);
        if (cart.isEmpty()) {
            throw new EmptyCartException();
        }
        
        Order order = createNewOrder(userLogin, cart, deliveryCost);
        Bill bill = createBill(order, card);
        order.setBill(bill);
        orderDAO.saveAndFlush(order);
        
        fillOrderItems(cart, order);
        save(order);
        cartService.clearUserCart(userLogin);
        
        return order;
    }

    private Order createNewOrder(String userLogin, Cart cart, int deliveryCost) {
        Order order = new Order();
        if (cart.isDeliveryIncluded()) {
            order.setDeliveryIncluded(true);
            order.setDeliveryСost(deliveryCost);
        } else {
            order.setDeliveryIncluded(false);
            order.setDeliveryСost(0);
        }
        order.setAccount(userAccountDAO.findByEmail(userLogin));
        order.setProductsCost(cart.getProductsCost());
        order.setDateCreated(new Date());
        order.setExecuted(false);
        return order;
    }

    private Bill createBill(Order order, CreditCardDTO card) {
        Bill bill = new Bill();
        bill.setOrder(order);
        bill.setNumber(new Random().nextInt(999999999));
        bill.setTotalCost(order.getProductsCost() + order.getDeliveryСost());
        bill.setPayed(true);
        bill.setDateCreated(new Date());
        bill.setCcNumber(card.getNumber());
        return bill;
    }

    private void fillOrderItems(Cart cart, Order order) {
        Set<OrderItem> ordered = new HashSet<>();
        for (CartItem item : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            Product product = item.getProduct();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(item.getQuantity());
            ordered.add(orderItem);
        }
        order.setOrderItems(ordered);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderDTO> getUserOrders(String userLogin) {
        Account account = userAccountDAO.findByEmail(userLogin);
        List<Order> orders = findByUserAccount(account);
        return orderDtoAssembler.toResources(orders);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDTO getUserOrder(String userLogin, long id) throws OrderNotFoundException {
        Order order = findOne(id);
        if ((order == null) || !order.getAccount().getEmail().equals(userLogin)) {
            throw new OrderNotFoundException("У пользователя " + userLogin + " не существует заказа с id " + id);
        }
        return orderDtoAssembler.toResource(order);
    }
}
