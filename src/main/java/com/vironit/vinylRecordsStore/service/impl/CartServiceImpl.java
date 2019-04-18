package com.vironit.vinylRecordsStore.service.impl;

import java.util.List;

import com.vironit.vinylRecordsStore.entity.Account;
import com.vironit.vinylRecordsStore.entity.Cart;
import com.vironit.vinylRecordsStore.entity.CartItem;
import com.vironit.vinylRecordsStore.entity.Product;
import com.vironit.vinylRecordsStore.dto.CartItemDTO;
import com.vironit.vinylRecordsStore.service.CartService;
import com.vironit.vinylRecordsStore.dao.CartDAO;
import com.vironit.vinylRecordsStore.dao.ProductDAO;
import com.vironit.vinylRecordsStore.dao.UserAccountDAO;
import com.vironit.vinylRecordsStore.exception.UnknownProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса корзины.
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartDAO cartDAO;
    private final UserAccountDAO userAccountDAO;
    private final ProductDAO productDAO;
    
    @Autowired
    public CartServiceImpl(CartDAO cartDAO, UserAccountDAO userAccountDAO, ProductDAO productDAO) {
        this.cartDAO = cartDAO;
        this.userAccountDAO = userAccountDAO;
        this.productDAO = productDAO;
    }

    @Transactional
    @Override
    public Cart save(Cart cart) {
        return cartDAO.save(cart);
    }

    @Transactional
    @Override
    public void delete(Cart cart) {
        cartDAO.delete(cart);
    }

    @Transactional
    @Override
    public Cart findOne(long cartId) {
        return cartDAO.findOne(cartId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cart> findAll() {
        return cartDAO.findAll();
    }
    
    @Transactional
    @Override
    public Cart updateCartObject(Cart cart, CartItemDTO item) throws UnknownProductException {
        Product product = productDAO.findOne(item.getProductId());
        if (product == null) {
            throw new UnknownProductException();
        }
        if (product.getStorage().isAvailable()) {
            cart.update(product, item.getQuantity());
        }
        return cart;
    }

    //---------------------------------------- Операции с корзиной пользователя
    
    @Transactional
    @Override
    public Cart getUserCart(String userLogin) {
        Account account = userAccountDAO.findByEmail(userLogin);
        return findOne(account.getId());
    }
    
    @Transactional
    @Override
    public Cart clearUserCart(String userLogin) {
        Cart cart = getUserCart(userLogin);
        cart.clear();
        return save(cart);
    }
    
    @Transactional(rollbackFor = {UnknownProductException.class})
    @Override
    public Cart updateUserCart(String userLogin, CartItemDTO item) throws UnknownProductException {
        Cart cart = getUserCart(userLogin);
        cart = updateCartObject(cart, item);
        return save(cart);
    }

    @Transactional
    @Override
    public Cart setUserCartDelivery(String userLogin, boolean deliveryIncluded) {
        Cart cart = getUserCart(userLogin);
        cart.setDeliveryIncluded(deliveryIncluded);
        return save(cart);
    }
    
    @Transactional
    @Override
    public Cart fillUserCart(String userLogin, List<CartItem> itemsToCopy) {
        Cart cart = getUserCart(userLogin);
        for (CartItem item : itemsToCopy) {
            cart.update(item.getProduct(), item.getQuantity());
        }
        return save(cart);
    }
}
