package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Brand;

import java.util.List;
public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    @Insert("insert into tb_brand values(null,#{brandName}," +
            "#{companyName},#{ordered},#{description},#{status})")
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
    @Delete("delete from tb_brand where id = #{id}")
    void deleteSingle(int id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from tb_brand ")
    int selectTotalCount();

    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,
                                         @Param("size") int size,
                                         @Param("brand") Brand brand);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Brand brand);

}
