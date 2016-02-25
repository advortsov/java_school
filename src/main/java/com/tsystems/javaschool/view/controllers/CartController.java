package com.tsystems.javaschool.view.controllers;

import com.tsystems.javaschool.services.interfaces.ShoppingCartManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 22.02.2016
 */
public class CartController {

    public static void writeBooksIntoCookie(HttpServletRequest req, HttpServletResponse resp,
                                            long bookId, int previousQuantity, ShoppingCartManager shoppingCartManager) {
        shoppingCartManager.deleteExistingCookies(bookId, req);
        Cookie cookie = new Cookie(String.valueOf(bookId),
                req.getSession().getAttribute("name_for_greeting") + "dlm" + bookId + "dlm" + ++previousQuantity);
        cookie.setMaxAge(60 * 60 * 24 * 30); // for working with browser closing
        resp.addCookie(cookie);
    }


    public static void deleteCartsBooksCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        String cookieOwner = (String) req.getSession().getAttribute("name_for_greeting");
        for (Cookie cookie : cookies) {
            String value = cookie.getValue();
            if (value.contains("dlm")) {
                String[] cookieContent = value.split("dlm");
                if (cookieContent[0].equals(cookieOwner)) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }
    }

    public static void deleteOrderLineCookie(long id,
                                             HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        String cookieOwner = (String) req.getSession().getAttribute("name_for_greeting");
        for (Cookie cookie : cookies) {
            String value = cookie.getValue();
            if (value.contains("dlm")) {
                String[] cookieContent = value.split("dlm");
                if (cookieContent[0].equals(cookieOwner) && id == Long.parseLong(cookieContent[1])) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }
            }
        }
    }


}
