package com.mine.controller;

import com.mine.domain.User;
import com.mine.model.UserModel;

import java.sql.SQLException;

/**
 * Created by yuanxing on 2017/5/9.
 */
@javax.servlet.annotation.WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class loginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        request.setCharacterEncoding("utf8");
        String username = request.getParameter("username");
        String psd = request.getParameter("psd");
//        response.getWriter().print(username + " \t" + psd);
        User user = new User();
        user.setUsername(username);
        user.setPassword(psd);
//        校验逻辑；
        UserModel userModel = new UserModel();
//        若返回非空，则代表存在该账户。
        try {
            User existUser = userModel.login(user);
            if (existUser != null) {
//                登陆成功！
                response.sendRedirect("success.jsp");
            } else {
//                不成功则转发请求，重新开始登陆。
                request.setAttribute("msg", "用户名或密码错误！");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
