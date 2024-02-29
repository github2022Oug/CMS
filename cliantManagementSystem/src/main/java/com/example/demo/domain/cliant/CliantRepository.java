package com.example.demo.domain.cliant;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CliantRepository {
	
	@Select("SELECT * from cliants")
	List<CliantEntity> findAll();
	
	@Insert("INSERT into cliants (sei, mei, mailaddress, tellnumber, region, industry) values (#{sei}, #{mei}, #{mailaddress}, #{tellnumber}, #{region}, #{industry})" )
	void insert(String sei, String mei, String mailaddress, String tellnumber, String region, String industry);
	
	@Delete("DELETE from cliants WHERE id = #{id}")
	void deleteById(long id);
	
	@Select("SELECT * from cliants WHERE id = #{id}")
	CliantEntity findById(long id);
	
	@Update("UPDATE cliants "
			+ "SET sei = #{sei}, mei = #{mei}, mailaddress = #{mailaddress}, "
			+ "tellnumber = #{tellnumber}, region = #{region}, industry = #{industry} "
			+ "WHERE id = #{id}")
	void updateById(long id, String sei, String mei, String mailaddress, String tellnumber, String region, String industry);
	
	
//	検索条件
//	MyBatisの<if>タグは、動的SQLの一部として条件付きでSQLクエリのセグメントを含めるかどうかを制御します。
//	<if>タグ内のtest属性は、その条件を評価するための式を指定する場所です。
//	この式がtrueを返す場合、<if>タグ内のSQLセグメントがクエリに含まれます。
//	そうでない場合は、そのセグメントは無視されます。
	@Select("<script>"
			+ "SELECT * FROM cliants"
			+ "<where>"
			+ "<if test='sei != null and sei != \"\"'>"
			+ "AND sei LIKE CONCAT('%', #{sei}, '%')"
			+ "</if>"
			+ "<if test='mei != null and mei != \"\"'>"
	        + "AND mei LIKE CONCAT('%', #{mei}, '%')"
	        + "</if>"
	        + "<if test='mailaddress != null and mailaddress != \"\"'>"
	        + "AND mailaddress LIKE CONCAT('%', #{mailaddress}, '%')"
	        + "</if>"
	        + "<if test='tellnumber != null and tellnumber != \"\"'>"
	        + "AND tellnumber LIKE CONCAT('%', #{tellnumber}, '%')"
	        + "</if>"
	        + "<if test='region != null and region != \"\"'>"
	        + "AND region = #{region}"
	        + "</if>"
	        + "<if test='industry != null and industry != \"\"'>"
	        + "AND industry = #{industry}"
	        + "</if>"
			+ "</where>"
			+ "</script>")
	List<CliantEntity> searchAndFilter(
			@Param("sei") String sei,
			@Param("mei") String mei,
			@Param("mailaddress") String mailaddress,
			@Param("tellnumber") String tellnumber,
			@Param("region") String region,
			@Param("industry") String industry
			);
	
//	TODO このリクエストが通らない。要修正。
	@Select("SELECT * from cliants WHERE region LIKE %H%")
	List<CliantEntity> findByRegion(@Param("region") String region);
	
	
	@Select("<script>"
	        + "SELECT * FROM customers"
	        + "<where>"
	        + "<if test='sei != null and sei != \"\"'>"
	        + "AND sei LIKE CONCAT('%', #{sei}, '%')"
	        + "</if>"
	        + "<if test='mei != null and mei != \"\"'>"
	        + "AND mei LIKE CONCAT('%', #{mei}, '%')"
	        + "</if>"
	        + "<if test='mailaddress != null and mailaddress != \"\"'>"
	        + "AND mailaddress LIKE CONCAT('%', #{mailaddress}, '%')"
	        + "</if>"
	        + "<if test='tellnumber != null and tellnumber != \"\"'>"
	        + "AND tellnumber LIKE CONCAT('%', #{tellnumber}, '%')"
	        + "</if>"
	        + "</where>"
	        + "</script>")
	List<CliantEntity> searchCustomers(@Param("sei") String sei, @Param("mei") String mei, @Param("mailaddress") String mailaddress, @Param("tellnumber") String tellnumber);

}