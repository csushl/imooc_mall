package com.imooc.mall.controller;

import com.imooc.mall.common.ApiRestResponse;
import com.imooc.mall.filter.UserFilter;
import com.imooc.mall.model.vo.CartVO;
import com.imooc.mall.service.CartService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：     购物车Controller
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/list")
    @ApiOperation("购物车列表")
    public ApiRestResponse list() {
        //内部获取用户ID，防止横向越权
        List<CartVO> cartList = cartService.list(UserFilter.userThreadLocal.get().getId());
        return ApiRestResponse.success(cartList);
    }

    @PostMapping("/add")
    @ApiOperation("添加商品到购物车")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService.add(UserFilter.userThreadLocal.get().getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/update")
    @ApiOperation("更新购物车")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        List<CartVO> cartVOList = cartService
                .update(UserFilter.userThreadLocal.get().getId(), productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/delete")
    @ApiOperation("删除购物车")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        //不能传入userID，cartID，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService.delete(UserFilter.userThreadLocal.get().getId(), productId);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/select")
    @ApiOperation("选择/不选择购物车的某商品")
    public ApiRestResponse select(@RequestParam Integer productId, @RequestParam Integer selected) {
        //不能传入userID，cartID，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService
                .selectOrNot(UserFilter.userThreadLocal.get().getId(), productId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    @PostMapping("/selectAll")
    @ApiOperation("全选择/全不选择购物车的某商品")
    public ApiRestResponse selectAll(@RequestParam Integer selected) {
        //不能传入userID，cartID，否则可以删除别人的购物车
        List<CartVO> cartVOList = cartService
                .selectAllOrNot(UserFilter.userThreadLocal.get().getId(), selected);
        return ApiRestResponse.success(cartVOList);
    }
}
