package org.example.service;

import org.example.pojo.Brand;
import org.example.pojo.PageBean;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    void add(Brand brand);

    /**
     * 修改数据
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除单个数据
     * @param id
     */
    void deleteSingle(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(int[] ids);

    /**
     * 分页条件查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);

}
